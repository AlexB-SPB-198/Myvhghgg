package com.example.myapplication11.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.myapplication11.Preference
import com.example.myapplication11.R
import com.example.myapplication11.databinding.FragmentOnBoardingBinding
import com.google.firebase.auth.FirebaseAuth

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val auth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OnBoardAdapter(){
            Preference(requireContext()).showBoarding()
            if(auth.currentUser!=null){
            findNavController().navigateUp()
            }else{
                findNavController().navigate(R.id.authFragment)
            }
        }

        binding.onBoarding.adapter = adapter

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.onBoarding)

        binding.indicator.attachToRecyclerView(binding.onBoarding,pagerSnapHelper)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
    }



}
