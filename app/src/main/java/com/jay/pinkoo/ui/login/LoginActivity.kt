package com.jay.pinkoo.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jay.pinkoo.R
import com.jay.pinkoo.databinding.ActivityLoginBinding
import com.jay.pinkoo.di.viewmodel.LoginViewModel
import com.jay.pinkoo.model.body.UserDetails
import com.jay.pinkoo.ui.dashboard.MainActivity
import com.jay.pinkoo.util.showConfirmationDialog

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onResume() {
        super.onResume()
        setupListeners()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun setupListeners() {
        binding.btLogin.setOnClickListener { loginUser() }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun loginUser() {
        val email = binding.etCrn.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isEmpty() && password.isEmpty()) {
            showEmptyDialog()
        } else {
            val userDetails = UserDetails(email, password)

            viewModel.login(userDetails)


            viewModel._userResponseLiveData.observe(this) { userResponse ->
                if (userResponse != null) {
                    Log.d("tocken", userResponse.token.toString())
                    startMainActivity()
                }
            }
            viewModel._errorMessageLiveData.observe(this) { errorMessage ->
                if (errorMessage != null) {
                    showConfirmationDialog(getString(R.string.error), errorMessage)
                }
            }
        }

    }

    private fun showEmptyDialog() {
        showConfirmationDialog(
            getString(R.string.empty_creds),
            getString(R.string.enter_creds)
        )
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}