package com.example.submission1intermediateandroid.view.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.UserRepository
import com.example.submission1intermediateandroid.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val storyRepository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    fun login(email: String, password: String) {
        _loginResult.value = Result.Loading

        viewModelScope.launch {
            val result = storyRepository.login(email, password)
            _loginResult.value = result
        }
    }
}