package com.example.submission1intermediateandroid.view.login.addstory

import androidx.lifecycle.ViewModel
import com.example.submission1intermediateandroid.UserRepository
import java.io.File

class AddStoryViewModel(private val repository: UserRepository) : ViewModel() {

    fun uploadStories(file: File, description: String, lat: Double? = null, lon: Double? = null) =
        repository.uploadStories(file, description, lat, lon)
}