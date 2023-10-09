package com.victorteka.jsonplaceholderapp.data.remote

import com.victorteka.jsonplaceholderapp.data.remote.model.PostResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponseDto>>
}