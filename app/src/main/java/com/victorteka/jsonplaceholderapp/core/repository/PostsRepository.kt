package com.victorteka.jsonplaceholderapp.core.repository

import com.victorteka.jsonplaceholderapp.core.model.Post
import com.victorteka.jsonplaceholderapp.util.Result
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    fun getPosts(): Flow<Result<List<Post>>>
}
