package com.example.submission1intermediateandroid.view.login.signup


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.data.ViewModelFactory
import com.example.submission1intermediateandroid.databinding.ActivitySignupBinding
import com.example.submission1intermediateandroid.response.RegisterResponse


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val viewModel by viewModels<SignupViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        setupAction()

        supportActionBar?.hide()
    }

    fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 2500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val register =
            ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(100)
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val name = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
        val etlName =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val email = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val etlEmail =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val password =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val etlPassword =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(title, name, etlName, email, etlEmail, password, etlPassword, register)
            start()
        }
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            viewModel.register(name, email, password)
            viewModel.registrationResult.observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Success -> {
                        showLoading(false)
                        // Lakukan sesuatu dengan hasil sukses
                        val response: RegisterResponse = result.data
                        // Tampilkan response atau lakukan sesuatu dengan data response
                        AlertDialog.Builder(this).apply {
                            setTitle("Yeah!")
                            setMessage(response.message)
                            setPositiveButton("Lanjut") { _, _ ->
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
                            setTitle("OOPS")
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
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}