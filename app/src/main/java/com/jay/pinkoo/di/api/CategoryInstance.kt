package com.jay.pinkoo.di.api

import com.jay.pinkoo.util.NetworkModule
import okhttp3.OkHttpClient

object CategoryInstance {

    val api: CategoryAPI by lazy {
        NetworkModule.provideRetrofit(OkHttpClient())
            .create(CategoryAPI::class.java)
    }
}