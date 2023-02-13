package com.example.integrador


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterResponse(
    val jwt: String,
    val user: User
): Serializable {
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