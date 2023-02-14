package com.example.integrador


import com.google.gson.annotations.SerializedName

data class EditNewResponse(
    val `data`: Data,
    val meta: Meta
) {
    data class Data(
        val attributes: Attributes,
        val id: Int
    ) {
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

    class Meta
}