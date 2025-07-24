package ir.devalix.konkura

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.devalix.konkura.databinding.ActivityPdfBinding
import androidx.core.net.toUri
import java.net.URLEncoder

class PdfActivity : AppCompatActivity() {
    lateinit var binding :ActivityPdfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPdfBinding.inflate( layoutInflater )
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val url = "https://dl.heyvagroup.com/admin/Files/upload/968716957ekhtesasi%20riazi%201400.pdf"

        binding.pdfView.initWithUrl( url )

    }
}