package com.jay.pinkoo.di.api

import com.jay.pinkoo.model.body.UserDetails
import com.jay.pinkoo.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/api/login")
    suspend fun getUserDetails(@Body userDetails: UserDetails): Response<LoginResponse>
}