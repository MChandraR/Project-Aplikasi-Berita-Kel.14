package com.mcr.uberita.interfaces

import com.mcr.uberita.model.beritaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface beritaAPI {
    @GET("api/berita")
    fun getDataBerita():Call<ArrayList<beritaModel>>
}