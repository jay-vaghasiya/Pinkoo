package com.jay.pinkoo.di.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.pinkoo.di.application.PinkooApp
import com.jay.pinkoo.model.response.Category
import com.jay.pinkoo.model.response.userlist.UserProductList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    val _userResponseLiveData = MutableLiveData<UserProductList?>()
    val _errorMessageLiveData = MutableLiveData<String?>()


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun userList() {
        viewModelScope.launch(Dispatchers.IO) {
            val userResponse = PinkooApp.userListRepository.getUserList()

            if (userResponse is UserProductList) {
                _userResponseLiveData.postValue(userResponse)
            } else if (userResponse is String) {
                _errorMessageLiveData.postValue(userResponse)
            }else{
                //Do nothing
            }
        }
    }
}