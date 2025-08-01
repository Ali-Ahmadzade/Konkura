package ir.devalix.konkura

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import ir.devalix.konkura.databinding.ActivityPdfBinding
import java.io.File
import java.io.IOException

class PdfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPdfBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val uniqueID = intent.getStringExtra("UNIQUE_ID")
        val konkurname = intent.getStringExtra( "konkurName" )!!

        val file = File(filesDir, "$uniqueID.pdf")
        if (file.exists()) {
            binding.pdfView.visibility = View.VISIBLE
            binding.pdfView.initWithFile(file)
        }
        binding.fabIconPdf.setOnClickListener {
            saveKonkurToDL( this, "$uniqueID.pdf" , konkurname ,  )
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }


    private fun saveKonkurToDL(
        context: Context,
        sourceFileName: String,
        desiredFileName: String,
        mimeType: String = "application/pdf"
    ) {
        val sourceFile = File(context.filesDir, sourceFileName)
        if (!sourceFile.exists()) {
            Toast.makeText(context, "فایل پیدا نشد", Toast.LENGTH_SHORT).show()
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME, desiredFileName)
                put(MediaStore.Downloads.MIME_TYPE, mimeType)
                put(MediaStore.Downloads.IS_PENDING, 1)
            }

            val downloadsUri = MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val targetUri = resolver.insert(downloadsUri, contentValues)

            if (targetUri != null) {
                resolver.openOutputStream(targetUri).use { outputStream ->
                    sourceFile.inputStream().use { inputStream ->
                        inputStream.copyTo(outputStream!!)
                    }
                }

                contentValues.clear()
                contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
                resolver.update(targetUri, contentValues, null, null)

                Toast.makeText(context, "در پوشه دانلودها ذخیره شد", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "خطا در ذخیره فایل", Toast.LENGTH_SHORT).show()
            }
        } else {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val targetFile = File(downloadsDir, desiredFileName)

            try {
                sourceFile.copyTo(targetFile, overwrite = true)
                Toast.makeText(context, "در پوشه دانلودها ذخیره شد", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "خطا در کپی فایل: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
