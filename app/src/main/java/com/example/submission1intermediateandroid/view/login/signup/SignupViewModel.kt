package com.example.submission1intermediateandroid.view.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.UserRepository
import com.example.submission1intermediateandroid.response.RegisterResponse
import kotlinx.coroutines.launch

class SignupViewModel(private val storyRepository: UserRepository) : ViewModel() {

    private val _registrationResult = MutableLiveData<Result<RegisterResponse>>()
    val registrationResult: LiveData<Result<RegisterResponse>> get() = _registrationResult

    fun register(name: String, email: String, password: String) {
        _registrationResult.value = Result.Loading

        viewModelScope.launch {
            val result = storyRepository.register(name, email, password)
            _registrationResult.value = result
        }
    }
}