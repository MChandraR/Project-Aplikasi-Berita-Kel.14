package com.mcr.uberita.model

import com.google.gson.annotations.SerializedName

class loginModel(username:String , password:String) {
    @SerializedName("status")
    var status:String = ""

    @SerializedName("message")
    var message:String = ""

    @SerializedName("username")
    var username:String = username

    @SerializedName("password")
    var password:String = password

    @SerializedName("data")
    var data:loginData = loginData()

    class loginData{
        @SerializedName("username")
        var username:String = ""

        @SerializedName("password")
        var password:String = ""

        @SerializedName("user_id")
        var user_id:String = ""

        @SerializedName("email")
        var email:String = ""

        @SerializedName("role")
        var role:String = ""


    }
}