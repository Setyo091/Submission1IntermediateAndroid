package com.example.submission1intermediateandroid.view.login.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.databinding.ActivityLoginBinding
import com.example.submission1intermediateandroid.data.ViewModelFactory
import com.example.submission1intermediateandroid.response.LoginResponse
import com.example.submission1intermediateandroid.view.login.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        setupAction()

        supportActionBar?.hide()

    }

    fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val email = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val etlEmail =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val password =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val etlPassword =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(title, message, email, etlEmail, password, etlPassword, login)
            start()
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.login(email, password)
            viewModel.loginResult.observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Success -> {
                        showLoading(false)
                        // Lakukan sesuatu dengan hasil sukses
                        val response: LoginResponse = result.data
                        // Tampilkan response atau lakukan sesuatu dengan data response
                        AlertDialog.Builder(this).apply {
                            setTitle("Yeah!")
                            setMessage(response.message)
                            setPositiveButton("OKE") { _, _ ->
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                                finish()
                            }
                            create()
                            show()
                        }
                    }

                    is Result.Error -> {
                        showLoading(false)
                        // Lakukan sesuatu dengan hasil error
                        val errorMessage: String = result.error.toString()
                        // Tampilkan pesan error atau lakukan sesuatu dengan pesan error
                        AlertDialog.Builder(this).apply {
                            setTitle("OOPS!")
                            setMessage(errorMessage)
                            setPositiveButton("OKE") { _, _ ->
                            }
                            create()
                            show()
                        }
                    }
                }
            }
        }
    }
    fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBarLogin.visibility = View.VISIBLE
            } else {
                progressBarLogin.visibility = View.GONE
            }
        }
    }
}