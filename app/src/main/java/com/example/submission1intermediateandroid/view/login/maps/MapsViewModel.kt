package com.example.submission1intermediateandroid.view.login.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1intermediateandroid.UserRepository
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.response.ListStoryItem


class MapsViewModel(private val repository: UserRepository) : ViewModel() {

    fun getStoriesWithLocation(): LiveData<Result<List<ListStoryItem>>> {
        return repository.getStoriesWithLocation()
    }
}