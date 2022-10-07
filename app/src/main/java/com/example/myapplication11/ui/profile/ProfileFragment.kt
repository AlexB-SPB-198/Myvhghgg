package com.example.myapplication11.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication11.Preference
import com.example.myapplication11.R
import com.example.myapplication11.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var preference:Preference

    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent())
    { uri ->
        preference.setProfileImage(uri.toString())
        Glide.with(requireContext()).load(uri.toString()).into(binding.profileImage)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        binding = FragmentProfileBinding.inflate(inflater,container,false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference= Preference(requireContext())
        binding.etProfile.setText(preference.getName())



        binding.btnSingout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
findNavController().navigate(R.id.action_navigation_profile_to_authFragment)
        }






        Glide.with(requireContext()).load(preference.getProfileImage()).
        placeholder(R.drawable.ic_downward).into(binding.profileImage)
        binding.etProfile.setText(preference.getName())
        binding.etLastprofile.setText(preference.getLastName())
        binding.etAge.setText(preference.getAge())
        binding.profileImage.setOnClickListener{
            mGetContent.launch("image/*")
        }
        binding.etProfile.addTextChangedListener{
            preference.setName(binding.etProfile.text.toString())
        }
        binding.etLastprofile.addTextChangedListener{
            preference.setLastName(binding.etLastprofile.text.toString())
        }
        binding.etAge.addTextChangedListener{
            preference.setAge(binding.etAge.text.toString())
        }
    }

}