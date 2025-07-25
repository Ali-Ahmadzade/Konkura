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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.adapter.KonkurListTajrobi
import ir.devalix.konkura.adapter.KonkurListTajrobiAdapter
import ir.devalix.konkura.databinding.TajrobiFragmentBinding
import androidx.core.view.size
import com.cazaea.sweetalert.SweetAlertDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.devalix.konkura.PdfActivity
import kotlinx.coroutines.delay


class TajrobiFragment : Fragment(), KonkurListTajrobiAdapter.OnDownloadProgressListener {
    private lateinit var binding: TajrobiFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TajrobiFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val jsonString = loadJSONFromAsset(requireContext(), "konkur_list_tajrobi.json")
        val konkurListType = object : TypeToken<List<KonkurListTajrobi>>() {}.type
        val konkurList = Gson().fromJson<List<KonkurListTajrobi>>(jsonString, konkurListType)
        val arrayKonkur = ArrayList(konkurList)

        val myAdapter = KonkurListTajrobiAdapter(arrayKonkur, this)
        binding.recyclerTajrobi.adapter = myAdapter
        binding.recyclerTajrobi.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.recyclerTajrobi.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.recyclerTajrobi.viewTreeObserver.removeOnPreDrawListener(this)

                    for (i in 0..<binding.recyclerTajrobi.size) {
                        val v: View = binding.recyclerTajrobi.getChildAt(i)
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