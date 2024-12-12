package com.robert.challenge.data.model


import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("author")
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("date_taken")
    val dateTaken: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("media")
    val media: MediaModel,
    @SerializedName("published")
    val published: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("title")
    val title: String
)