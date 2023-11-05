package com.example.submission1intermediateandroid.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.submission1intermediateandroid.R
import com.example.submission1intermediateandroid.databinding.ActivitySplashBinding
import com.example.submission1intermediateandroid.view.login.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, timer)
    }

    companion object {
        const val timer = 2000L
    }
}