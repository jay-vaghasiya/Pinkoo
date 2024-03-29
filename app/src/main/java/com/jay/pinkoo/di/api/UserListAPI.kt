package com.jay.pinkoo.di.api

import com.jay.pinkoo.model.response.Category
import com.jay.pinkoo.model.response.userlist.UserProductList
import retrofit2.Response
import retrofit2.http.GET

interface UserListAPI {
    @GET("/api/users?page=1")
    suspend fun getUserList(): Response<UserProductList>
}