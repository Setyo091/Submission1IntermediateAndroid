package com.example.submission1intermediateandroid.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission1intermediateandroid.UserRepository
import com.example.submission1intermediateandroid.di.Injection
import com.example.submission1intermediateandroid.view.login.addstory.AddStoryViewModel
import com.example.submission1intermediateandroid.view.login.login.LoginViewModel
import com.example.submission1intermediateandroid.view.login.main.MainViewModel
import com.example.submission1intermediateandroid.view.login.maps.MapsViewModel
import com.example.submission1intermediateandroid.view.login.signup.SignupViewModel

class ViewModelFactory(private val repository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                AddStoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(Injection.provideRepository(context))
        }
    }
}