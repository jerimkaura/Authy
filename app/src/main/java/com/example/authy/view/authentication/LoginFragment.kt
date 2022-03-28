package com.example.authy.view.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.authy.R
import com.example.authy.network.data.requests.LoginRequest
import com.example.authy.util.showToast
import com.example.authy.view.homescreen.HomeActivity
import com.example.authy.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton = view.findViewById<Button>(R.id.login_button)
        view.findViewById<TextView>(R.id.login_fragment_register_text)

        loginButton.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.te_email).text.toString()
            val password = view.findViewById<EditText>(R.id.te_password).text.toString()
            if (email.isEmpty()) {
                showToast(requireContext(), "Email number required")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showToast(requireContext(), "Enter a valid email")
            } else if (password.isEmpty()) {
                showToast(requireContext(), "Password required")
            } else {
                val loginRequest = LoginRequest(email, password)
                authViewModel.login(loginRequest)
                observeLogin()
            }
        }
        return view
    }

    private fun observeLogin() {
        authViewModel._loginState.observe(requireActivity()) { data ->
            when {
                data.isLoading -> {
                    showToast(requireContext(), "Loading...")
                }
                data.data != null -> {
                    showToast(requireContext(), "Login successful $data")
                    startActivity(Intent(requireContext(), HomeActivity::class.java))
                }
                else -> {
                    showToast(requireContext(), "Login Failure $data")
                }
            }

        }
    }
}

