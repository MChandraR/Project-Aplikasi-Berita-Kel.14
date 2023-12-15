package com.mcr.uberita.interfaces

import com.mcr.uberita.model.bookMarkModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface bookMarkAPI {
    @POST("api/bookmarks")
    fun getUserBookmark(@Body body:bookMarkModel) : Call<bookMarkModel>

    @POST("api/bookmark")
    fun addBookMark(@Body body:bookMarkModel): Call<bookMarkModel>
}