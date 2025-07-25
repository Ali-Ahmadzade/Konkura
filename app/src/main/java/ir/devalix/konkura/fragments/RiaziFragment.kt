package ir.devalix.konkura.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.devalix.konkura.PdfActivity
import ir.devalix.konkura.adapter.KonkurListRiazi
import ir.devalix.konkura.adapter.KonkurListRiaziAdapter
import ir.devalix.konkura.adapter.KonkurListTajrobi
import ir.devalix.konkura.adapter.KonkurListTajrobiAdapter
import ir.devalix.konkura.adapter.SubButtonRiazi
import ir.devalix.konkura.databinding.RiaziFragmentBinding


class RiaziFragment : Fragment(), KonkurListRiaziAdapter.OnDownloadProgressListener {
    private lateinit var binding: RiaziFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RiaziFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = loadJSONFromAsset(requireContext(), "konkur_list_riazi.json")
        val konkurListType = object : TypeToken<List<KonkurListRiazi>>() {}.type
        val konkurList = Gson().fromJson<List<KonkurListRiazi>>(jsonString, konkurListType)
        val arrayKonkur = ArrayList(konkurList)

        val myAdapter = KonkurListRiaziAdapter(arrayKonkur, this)
        binding.recyclerRiazi.adapter = myAdapter
        binding.recyclerRiazi.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.recyclerRiazi.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.recyclerRiazi.viewTreeObserver.removeOnPreDrawListener(this)

                    for (i in 0..<binding.recyclerRiazi.size) {
                        val v: View = binding.recyclerRiazi.getChildAt(i)
                        v.alpha = 0.0f
                        v.animate().alpha(1.0f)
                            .setDuration(350)
                            .setStartDelay((i * 50).toLong())
                            .start()
                    }

                    return true
                }
            })


    }

    private fun loadJSONFromAsset(context: Context, filename: String): String {
        return context.assets.open(filename).bufferedReader().use { it.readText() }
    }

    override fun onProgress(percent: Int, uniqueID: String) {

        if (percent < 100) {
            uiAppear()
            binding.progressBar.progress = percent.toFloat()
            binding.txtProgress.text = "$percent%"
        } else if (percent == 100) {
            binding.progressBar.progress = percent.toFloat()
            binding.txtProgress.text = "$percent%"
            Handler(Looper.getMainLooper()).postDelayed({
                uiDisappear()
            }, 300)
            val intent = Intent(context, PdfActivity::class.java)
            intent.putExtra("UNIQUE_ID", uniqueID)
            context?.startActivity(intent)

        }


    }

    private fun uiAppear() {
        binding.alertBgColor.visibility = View.VISIBLE
        binding.alertBg.visibility = View.VISIBLE
    }

    private fun uiDisappear() {
        binding.alertBgColor.visibility = View.GONE
        binding.alertBg.visibility = View.GONE
    }
}