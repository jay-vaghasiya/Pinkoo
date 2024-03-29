package com.jay.pinkoo.di.api

import com.jay.pinkoo.model.body.UserDetails
import com.jay.pinkoo.model.response.Category
import com.jay.pinkoo.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryAPI {

    @GET("/api/unknown")
    suspend fun getUserList(): Response<Category>
}