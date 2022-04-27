package com.example.kotlinmvvm2.network.model


import com.google.gson.annotations.SerializedName

data class BooksListModel(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int
)