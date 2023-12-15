package com.mcr.uberita.interfaces

import com.mcr.uberita.model.loginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface userAPI {
    @POST("api/login")
    fun signIn(@Body Body:loginModel ):Call<loginModel>

    @POST("api/signup")
    fun signUp(@Body Body:loginModel.loginData) : Call<loginModel>
}