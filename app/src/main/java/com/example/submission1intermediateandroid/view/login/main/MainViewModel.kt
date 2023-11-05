package com.example.submission1intermediateandroid.view.login.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.UserRepository
import com.example.submission1intermediateandroid.pref.UserModel
import com.example.submission1intermediateandroid.response.ListStoryItem
import com.example.submission1intermediateandroid.response.StoryResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    val story: LiveData<PagingData<ListStoryItem>> = repository.getStories().cachedIn(viewModelScope)
}