package com.example.authy.view.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.example.authy.R
import com.example.authy.network.data.requests.RegisterRequest
import com.example.authy.view.homescreen.HomeActivity
import com.example.authy.util.showToast
import com.example.authy.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val registerButton = view.findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener{
            val firstname = view.findViewById<TextInputEditText>(R.id.te_firstname).text.toString()
            val lastname = view.findViewById<TextInputEditText>(R.id.te_lastname).text.toString()
            val email = view.findViewById<TextInputEditText>(R.id.te_email).text.toString()
            val phoneNumber = view.findViewById<TextInputEditText>(R.id.te_phone_number).text.toString()
            val password1 = view.findViewById<TextInputEditText>(R.id.te_password).text.toString()
            val password2 = view.findViewById<TextInputEditText>(R.id.te_confirm_password).text.toString()

            if(firstname.isBlank()){
                showToast(requireContext(), "Firstname required")
            }else if (lastname.isBlank()){
                showToast(requireContext(), "Lastname required")
            }else if (email.isBlank()){
                showToast(requireContext(), "Email Required")
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                showToast(requireContext(), "Enter a valid email address")
            }else if(phoneNumber.isBlank()){
                showToast(requireContext(), "Phone number required")
            }else if(password1.isBlank()){
                showToast(requireContext(), "Password Required")
            }else if (password2.isBlank()){
                showToast(requireContext(),"Confirmation required")
            }else if (password1 !=password2){
                showToast(requireContext(), "Passwords do not match")
            }else{
                val registerRequest = RegisterRequest(email, firstname, lastname, password1, phoneNumber)
                authViewModel.register(registerRequest)
                observerRegister()
            }
        }
        
        return  view
    }

    private fun observerRegister() {
        authViewModel._registerState.observe(requireActivity()) { result ->
            if (result.isLoading) {
                showToast(requireContext(), "Loading ...")
            }else if (result.data != null && !result.isLoading){
                showToast(requireContext(), "Registration successful $result")
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            } else if (result.error !="") {
                showToast(requireContext(), "Registration Failure $result")
            }

        }
    }
}