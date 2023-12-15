package com.mcr.uberita.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class clientAPI {
    var serverURL = "http://192.168.137.1:3000/"

    fun getClientAPI():Retrofit{
        return Retrofit.Builder().baseUrl(serverURL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}