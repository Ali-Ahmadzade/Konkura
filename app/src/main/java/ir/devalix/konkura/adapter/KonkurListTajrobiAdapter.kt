package ir.devalix.konkura.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.google.android.material.button.MaterialButton
import ir.devalix.konkura.PdfActivity
import ir.devalix.konkura.R
import ir.devalix.konkura.databinding.ItemCardviewFragmentsBinding
import org.json.JSONObject
import java.io.File

class KonkurListTajrobiAdapter(
    private val data: ArrayList<KonkurListTajrobi>,
    private val listener: OnDownloadProgressListener
) :
    RecyclerView.Adapter<KonkurListTajrobiAdapter.KonkurViewHolder>() {

    var progressPercent = 0

    inner class KonkurViewHolder(val binding: ItemCardviewFragmentsBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: KonkurListTajrobi, position: Int) {
            binding.txtYearMain.text = item.year
            binding.toggleIcon1.rotation = if (item.isExpanded) 180f else 0f

            val container = (binding.expandableMain.getChildAt(0) as? LinearLayout)

            container?.removeAllViews()

            item.subButtons.forEach { sub ->
                val btn = MaterialButton(binding.root.context).apply {
                    text = sub.text
                    setTextColor(ContextCompat.getColor(context, android.R.color.white))
                    background = ContextCompat.getDrawable(context, R.drawable.button_gradient)
                    ViewCompat.setBackgroundTintList(this, null)
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(16, 8, 16, 8)
                    }

                    setOnClickListener {
                        val uniqueID = sub.id
                        openSelectedPdf(uniqueID, context)
                    }
                }
                container?.addView(btn)
            }


            if (item.isExpanded) {
                binding.expandableMain.expand()

            } else {
                binding.expandableMain.collapse()

            }


            binding.cardHeaderMain.setOnClickListener {

                val targetRotation = if (!item.isExpanded) 180f else 0f
                binding.toggleIcon1.animate().rotation(targetRotation).setDuration(370).start()

                item.isExpanded = !item.isExpanded
                binding.expandableMain.toggle()

            }

        }

    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KonkurViewHolder {
        val binding =
            ItemCardviewFragmentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KonkurViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KonkurViewHolder, position: Int) {
        holder.binding.cardHeaderMain.startAnimation(
            android.view.animation.AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.recycler_anim
            )
        )
        holder.bindData(data[position], position)
    }

    private fun openSelectedPdf(uniqueID: String, context: Context) {
        val fileName = "$uniqueID.pdf"
        val file = File(context.filesDir, fileName)
        if (file.exists()) {
            val intent = Intent(context, PdfActivity::class.java)
            intent.putExtra("UNIQUE_ID", uniqueID)
            context.startActivity(intent)
        } else {
            downloadAndSavePdf(context, uniqueID)
        }

    }

    private fun downloadAndSavePdf(context: Context, uniqueID: String) {


        val url = generateLink(uniqueID, context)
        val fileName = "$uniqueID.pdf"
        val path = context.filesDir.absolutePath

        PRDownloader.download(url, path, fileName)
            .build()
            .setOnProgressListener { progress ->

                progressPercent = (progress.currentBytes * 100 / progress.totalBytes).toInt()
                listener.onProgress(progressPercent, uniqueID)

            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    Log.v("downloadCompleted" , "")
                }

                override fun onError(p0: Error?) {
                    Toast.makeText(context, "اینترنت خود را بررسی کنید و مجدد تلاش کنید", Toast.LENGTH_SHORT).show()
                }

            })

    }

    interface OnDownloadProgressListener {
        fun onProgress(percent: Int, uniqueID: String)
    }

    private fun generateLink(id: String, context: Context): String {

        val jsonString =
            context.assets.open("konkur_links.json").bufferedReader().use { it.readText() }
        val map = JSONObject(jsonString)
        return map.get(id).toString()
    }

}

