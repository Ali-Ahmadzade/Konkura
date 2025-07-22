package ir.devalix.konkura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.adapter.KonkurListEnsani
import ir.devalix.konkura.adapter.KonkurListEnsaniAdapter
import ir.devalix.konkura.adapter.KonkurListTajrobiAdapter
import ir.devalix.konkura.adapter.SubButtonEnsani
import ir.devalix.konkura.databinding.EnsaniFragmentBinding

class EnsaniFragment:Fragment() {
    lateinit var binding: EnsaniFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EnsaniFragmentBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sampleData = arrayListOf(
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonEnsani("math_1403", "کنکور داخل 1403"),
                    SubButtonEnsani("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListEnsani(
                year = "سال تحصیلی 1402 - 1403",
                subButtons = listOf(
                    SubButtonEnsani("chem_1402", "کنکور خارج 1403"),
                    SubButtonEnsani("bio_1402", "کنکور داخل 1403")
                )
            )
        )

        val myAdapter = KonkurListEnsaniAdapter(sampleData)
        binding.recyclerEnsani.adapter = myAdapter
        binding.recyclerEnsani.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.recyclerEnsani.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    binding.recyclerEnsani.viewTreeObserver.removeOnPreDrawListener(this)

                    for (i in 0..<binding.recyclerEnsani.size) {
                        val v: View = binding.recyclerEnsani.getChildAt(i)
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

}