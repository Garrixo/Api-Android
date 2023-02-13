package com.example.integrador

import android.text.Editable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("news")
    fun getNews(
        @Header("Authorization") apikey: String): Call<NewsResponse>

    @POST("auth/local")
    fun doLogin(
        @Body request: ApiRest.LoginRequest
    ): Call<UsersResponse>

    @POST("auth/local/register")
    fun doRegister(
        @Body request: ApiRest.RegisterRequest
    ): Call<RegisterResponse>
}