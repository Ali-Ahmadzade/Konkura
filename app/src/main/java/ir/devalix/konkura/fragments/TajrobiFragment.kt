package ir.devalix.konkura.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.card.MaterialCardView
import ir.devalix.konkura.R
import ir.devalix.konkura.adapter.KonkurList
import ir.devalix.konkura.adapter.KonkurListAdapter
import ir.devalix.konkura.databinding.TajrobiFragmentBinding
import net.cachapa.expandablelayout.ExpandableLayout

class TajrobiFragment :Fragment() {
    lateinit var binding : TajrobiFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TajrobiFragmentBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val konkurList = arrayListOf<KonkurList>(

            KonkurList("سال تحصیلی 1402 - 1404") ,
            KonkurList("سال تحصیلی 1401 - 1402") ,
            KonkurList("سال تحصیلی 1400 - 1401") ,
            KonkurList("سال تحصیلی 1399 - 1400") ,
            KonkurList("سال تحصیلی 1398 - 1399") ,
            KonkurList("سال تحصیلی 1397 - 1398") ,
            KonkurList("سال تحصیلی 1396 - 1397") ,
            KonkurList("سال تحصیلی 1395 - 1396") ,
            KonkurList("سال تحصیلی 1394 - 1395") ,
            KonkurList("سال تحصیلی 1393 - 1394") ,
            KonkurList("سال تحصیلی 1392 - 1393")
        )

        val myAdapter = KonkurListAdapter( konkurList )
        binding.recyclerTajrobi.adapter = myAdapter
        binding.recyclerTajrobi.layoutManager = LinearLayoutManager( requireContext()  , RecyclerView.VERTICAL , false)




    }
}