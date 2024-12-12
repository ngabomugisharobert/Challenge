package com.robert.challenge.data.model


import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("description")
    val description: String,
    @SerializedName("generator")
    val generator: String,
    @SerializedName("items")
    val items: List<PhotoModel>,
    @SerializedName("link")
    val link: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("title")
    val title: String
)