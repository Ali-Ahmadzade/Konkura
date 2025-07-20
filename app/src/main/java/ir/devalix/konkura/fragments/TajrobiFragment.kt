package ir.devalix.konkura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.devalix.konkura.databinding.TajrobiFragmentBinding

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
}