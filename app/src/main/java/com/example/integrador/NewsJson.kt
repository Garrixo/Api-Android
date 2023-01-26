package com.example.integrador


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsJson(
    val response: String,
    val results: List<Result>,
    @SerializedName("results-for")
    val resultsFor: String
): Serializable {
    data class Result(
        val content: String,
        val date: String,
        val description: Description,
        val title: String
    ):Serializable {
        data class Description(
            val content: String,
            val imageofContent: String,
            val subtitle: String
        )
    }
}