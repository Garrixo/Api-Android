package com.example.integrador


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    val `data`: List<Data>,
    val meta: Meta
): Serializable {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ): Serializable {
        data class Attributes(
            @SerializedName("Content")
            val content: String,
            val createdAt: String,
            val game: String,
            val idNew: String,
            val publishedAt: String,
            @SerializedName("Subtitle")
            val subtitle: String,
            @SerializedName("Title")
            val title: String,
            val updatedAt: String,
            val urlImages: String
        )
    }

    data class Meta(
        val pagination: Pagination
    ): Serializable {
        data class Pagination(
            val page: Int,
            val pageCount: Int,
            val pageSize: Int,
            val total: Int
        )
    }
}