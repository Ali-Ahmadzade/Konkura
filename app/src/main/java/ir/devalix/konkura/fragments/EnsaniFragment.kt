package ir.devalix.konkura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}