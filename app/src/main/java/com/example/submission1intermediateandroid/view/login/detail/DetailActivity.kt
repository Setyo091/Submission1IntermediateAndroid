package com.example.submission1intermediateandroid.view.login.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.example.submission1intermediateandroid.R
import com.example.submission1intermediateandroid.data.ViewModelFactory
import com.example.submission1intermediateandroid.databinding.ActivityDetailBinding
import com.example.submission1intermediateandroid.view.login.main.MainViewModel


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra(ID) ?: ""
        name = intent.getStringExtra(NAME) ?: ""
        description = intent.getStringExtra(DESCRIPTION) ?: ""
        picture = intent.getStringExtra(PICTURE) ?: ""

        binding.cvName.text = name
        binding.cvDescription.text = description

        Glide.with(this).load(picture).into(binding.cvPhoto)

        supportActionBar?.hide()
    }

    companion object {
        const val ID = "ID"
        const val NAME = "NAME"
        const val DESCRIPTION = "DESCRIPTION"
        const val PICTURE = "PICTURE"

        var id: String = ""
        var name: String = ""
        var description: String? = null
        var picture: String? = null
    }
}