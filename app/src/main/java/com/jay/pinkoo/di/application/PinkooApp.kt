package com.jay.pinkoo.di.application

import android.app.Application
import com.jay.pinkoo.di.repository.CategoryRepository
import com.jay.pinkoo.di.repository.LoginRepository
import com.jay.pinkoo.di.repository.UserListRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PinkooApp : Application() {

    companion object {

        lateinit var loginRepository: LoginRepository
            private set

        lateinit var categoryRepository: CategoryRepository
            private set

        lateinit var userListRepository: UserListRepository
            private set

    }

    override fun onCreate() {
        super.onCreate()
        loginRepository = LoginRepository()
        categoryRepository = CategoryRepository()
        userListRepository = UserListRepository()

    }


}