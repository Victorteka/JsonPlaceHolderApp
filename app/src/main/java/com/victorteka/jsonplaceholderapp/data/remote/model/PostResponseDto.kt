package com.victorteka.jsonplaceholderapp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponseDto(
    @SerialName("body")
    val body: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int
)
