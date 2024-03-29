package com.jay.pinkoo.di.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.jay.pinkoo.di.api.CategoryInstance
import com.jay.pinkoo.di.api.LoginInstance
import com.jay.pinkoo.model.body.UserDetails
import com.jay.pinkoo.model.response.Category
import java.io.IOException
import javax.inject.Singleton


@Singleton
class CategoryRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getUserList(): Any? {

        val response = try {
            CategoryInstance.api.getUserList()
        } catch (e: IOException) {
            return e.message
        } catch (e: HttpException) {
            return e.message
        } catch (e: Exception) {
            return e.message
        }
        if (response.isSuccessful && response.body() != null) {
            val userResponse = response.body()

        }
        return response.body() ?: ""
    }

}
