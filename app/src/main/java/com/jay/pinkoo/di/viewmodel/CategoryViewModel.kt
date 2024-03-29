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
class CategoryViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    val _userResponseLiveData = MutableLiveData<Category?>()
    val _errorMessageLiveData = MutableLiveData<String?>()


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun userList() {
        viewModelScope.launch(Dispatchers.IO) {
            val userResponse = PinkooApp.categoryRepository.getUserList()

            if (userResponse is Category) {
                _userResponseLiveData.postValue(userResponse)
            } else if (userResponse is String) {
                _errorMessageLiveData.postValue(userResponse)
            }else{
                //Do nothing
            }
        }
    }
}