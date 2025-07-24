package ir.devalix.konkura

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ir.devalix.konkura.databinding.ActivityPdfBinding
import java.io.File

class PdfActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPdfBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val uniqueID = intent.getStringExtra("UNIQUE_ID")

        val file = File(filesDir , "$uniqueID.pdf")
        if (file.exists()){
            binding.pdfView.visibility = View.VISIBLE
            binding.pdfView.initWithFile( file )
        }

    }


}
