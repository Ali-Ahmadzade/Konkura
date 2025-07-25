package ir.devalix.konkura.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.graphics.alpha
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.adapter.KonkurListTajrobi
import ir.devalix.konkura.adapter.KonkurListTajrobiAdapter
import ir.devalix.konkura.adapter.SubButtonTajrobi
import ir.devalix.konkura.databinding.TajrobiFragmentBinding
import androidx.core.view.size
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TajrobiFragment : Fragment() , KonkurListTajrobiAdapter.OnDownloadProgressListener {
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

        val myAdapter = KonkurListTajrobiAdapter(arrayKonkur , this)
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

    override fun onProgress(percent: Int) {



    }



}