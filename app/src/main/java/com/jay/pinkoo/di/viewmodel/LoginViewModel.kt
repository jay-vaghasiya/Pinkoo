package com.jay.pinkoo.di.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.pinkoo.di.application.PinkooApp
import com.jay.pinkoo.model.body.UserDetails
import com.jay.pinkoo.model.response.Category
import com.jay.pinkoo.model.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    val _userResponseLiveData = MutableLiveData<LoginResponse?>()
    val _errorMessageLiveData = MutableLiveData<String?>()


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun login(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            val userResponse = PinkooApp.loginRepository.getUserInfo(userDetails)

            if (userResponse is LoginResponse) {
                _userResponseLiveData.postValue(userResponse)
            } else if (userResponse is String) {
                _errorMessageLiveData.postValue(userResponse)
            }else{
                //Do nothing
            }
        }
    }
}