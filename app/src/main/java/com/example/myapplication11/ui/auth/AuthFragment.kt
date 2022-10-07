package com.example.myapplication11.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.myapplication11.R
import com.example.myapplication11.databinding.FragmentAuthBinding
import com.example.myapplication11.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit
import kotlin.math.log


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private var verificationId: String? = null
    private val auth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSendPhone.setOnClickListener {
            if (binding.etPhoneNumber.text?.isNotEmpty() == true) {
                sendPhoneNumber()
            } else {
                binding.etPhoneNumber.error == getString(R.string.input_phone_number)
            }
        }
        binding.btnAccept.setOnClickListener {
            if (binding.etCode.text?.isNotEmpty() == true) {
                sendCode()
            } else {
                binding.etCode.error == getString(R.string.input_send_code)
            }
        }

    }


    private fun sendPhoneNumber() {

        val auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("ru")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhoneNumber.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())
            .setCallbacks(object : OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    //navigate to home fragment


                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    showToast(p0.message.toString())
                }

                override fun onCodeSent(
                    verifId: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    binding.btnSendPhone.isVisible = false
                    binding.inputPhone.isVisible = false
                    binding.inputCode.isVisible = true
                    binding.btnAccept.isVisible = true
                    verificationId = verifId
                }
            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun sendCode() {
        val credential = verificationId?.let {
            PhoneAuthProvider.getCredential(
                it,
                binding.etCode.text.toString()
            )
        }
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    showToast("Регистрация прошла упешно ")
                    findNavController().navigate(R.id.navigation_home)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        showToast((task.exception as FirebaseAuthInvalidCredentialsException).message.toString())
                    }
                    // Update UI
                }
            }
    }
}