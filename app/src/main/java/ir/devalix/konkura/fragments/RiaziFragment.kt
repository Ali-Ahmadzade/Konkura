package ir.devalix.konkura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.adapter.KonkurListRiazi
import ir.devalix.konkura.adapter.KonkurListRiaziAdapter
import ir.devalix.konkura.adapter.SubButtonRiazi
import ir.devalix.konkura.databinding.RiaziFragmentBinding

class RiaziFragment :Fragment() {
    private lateinit var binding: RiaziFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RiaziFragmentBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sampleData = arrayListOf(
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonRiazi("math_1403", "کنکور داخل 1403"),
                    SubButtonRiazi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListRiazi(
                year = "سال تحصیلی 1402 - 1403",
                subButtons = listOf(
                    SubButtonRiazi("chem_1402", "کنکور خارج 1403"),
                    SubButtonRiazi("bio_1402", "کنکور داخل 1403")
                )
            )
        )

        val myAdapter = KonkurListRiaziAdapter( sampleData )
        binding.recyclerRiazi.adapter = myAdapter
        binding.recyclerRiazi.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)


    }
}