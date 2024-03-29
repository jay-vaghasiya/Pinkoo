package com.jay.pinkoo.di.api

import com.jay.pinkoo.util.NetworkModule
import okhttp3.OkHttpClient

object LoginInstance {
    val api: LoginAPI by lazy {
        NetworkModule.provideRetrofit(OkHttpClient())
            .create(LoginAPI::class.java)
    }
}