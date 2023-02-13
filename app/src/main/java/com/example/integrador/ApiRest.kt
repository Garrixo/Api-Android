package com.example.integrador

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    val URL = "https://strapi-3vi4.onrender.com/api/"

    val apiKey = "b6091c6a0d6c233fd9f5372d51e76f68b603ab43255a9232189dc6de78cfe18ed083baf6d7e97707d71787f5fe360d7e009a5603a6f142f037047a4441f83cde1ed47ae0477234d9193d3572a4003ec3053b2582e57f7b97b8c10ac5a453960aad5fdf10a5c4b627708e48ca60da650c83d0f943c7cf456118cfe5e759b75dc1"


    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }

    data class LoginRequest(
        val identifier: String,
        val password: String
    )
}