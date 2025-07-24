package ir.devalix.konkura

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.devalix.konkura.databinding.ActivityPdfBinding
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class PdfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPdfBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val uniqueID = intent.getStringExtra("UNIQUE_ID")
        if (uniqueID != null) {
            val jsonString = assets.open("konkur_links.json").bufferedReader().use { it.readText() }
            val map = JSONObject(jsonString)
            val value = map.get(uniqueID)
            downloadAndShowPdf(value.toString())
        }

    }

    private fun downloadAndShowPdf(url: String) {
        binding.progressBar.visibility = View.VISIBLE

        Thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.connect()

                val file = File(cacheDir, "temp.pdf")
                val output = FileOutputStream(file)
                val input = connection.inputStream

                val buffer = ByteArray(1024)
                var length: Int

                while (input.read(buffer).also { length = it } > 0) {
                    output.write(buffer, 0, length)
                }

                output.flush()
                output.close()
                input.close()

                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    binding.pdfView.visibility = View.VISIBLE
                    binding.pdfView.initWithFile(file)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "خطا در بارگیری PDF", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

}
