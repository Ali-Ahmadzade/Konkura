package ir.devalix.konkura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.devalix.konkura.adapter.KonkurListTajrobi
import ir.devalix.konkura.adapter.KonkurListTajrobiAdapter
import ir.devalix.konkura.adapter.SubButtonTajrobi
import ir.devalix.konkura.databinding.TajrobiFragmentBinding
import androidx.core.view.size


class TajrobiFragment : Fragment() {
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


        val sampleData = arrayListOf(
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1403 - 1404",
                subButtons = listOf(
                    SubButtonTajrobi("math_1403", "کنکور داخل 1403"),
                    SubButtonTajrobi("phys_1403", "کنکور خارج 1403")
                )
            ),
            KonkurListTajrobi(
                year = "سال تحصیلی 1402 - 1403",
                subButtons = listOf(
                    SubButtonTajrobi("chem_1402", "کنکور خارج 1403"),
                    SubButtonTajrobi("bio_1402", "کنکور داخل 1403")
                )
            )
        )

        val myAdapter = KonkurListTajrobiAdapter( sampleData )
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
}