package com.example.integrador


import com.google.gson.annotations.SerializedName

data class UsersResponse(
    val jwt: String,
    val user: User
) {
    data class User(
        val admin: Boolean,
        val blocked: Boolean,
        val confirmed: Boolean,
        val createdAt: String,
        val email: String,
        val id: Int,
        val provider: String,
        val updatedAt: String,
        val username: String
    )
}