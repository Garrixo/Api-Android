package com.example.integrador

import android.text.Editable
import retrofit2.Call
import retrofit2.http.*

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

    @PUT("news/{id}")
    fun updateNew(
        @Header("Authorization") apikey: String,
        @Path("id") newId: String,
        @Body request: ApiRest.upNew
    ): Call<EditNewResponse>
}