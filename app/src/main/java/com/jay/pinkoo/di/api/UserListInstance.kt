package com.jay.pinkoo.di.api

import com.jay.pinkoo.util.NetworkModule
import okhttp3.OkHttpClient

object UserListInstance {
    val api: UserListAPI by lazy {
        NetworkModule.provideRetrofit(OkHttpClient())
            .create(UserListAPI::class.java)
    }
}