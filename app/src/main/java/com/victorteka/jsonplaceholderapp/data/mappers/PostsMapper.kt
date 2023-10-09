package com.victorteka.jsonplaceholderapp.data.mappers

import com.victorteka.jsonplaceholderapp.core.model.Post
import com.victorteka.jsonplaceholderapp.data.remote.model.PostResponseDto


fun PostResponseDto.toCoreModel(): Post {
    return Post(
        body = this.body,
        id = this.id,
        title = this.title,
        userId = this.userId
    )
}