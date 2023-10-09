package com.victorteka.jsonplaceholderapp.data.remote

import com.victorteka.jsonplaceholderapp.core.model.Post
import com.victorteka.jsonplaceholderapp.core.repository.PostsRepository
import com.victorteka.jsonplaceholderapp.data.mappers.toCoreModel
import com.victorteka.jsonplaceholderapp.util.ErrorType
import com.victorteka.jsonplaceholderapp.util.Result
import java.io.IOException
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl @Inject constructor(
    private val postsApiService: PostsApiService
) : PostsRepository {

    override fun getPosts(): Flow<Result<List<Post>>> = flow {
        val response = postsApiService.getPosts()
        if (response.isSuccessful && response.body() != null) {
            emit(Result.Success(data = response.body()!!.map { it.toCoreModel() }))
        } else {
            val errorType = mapResponseCodeToErrorType(response.code())
            emit(Result.Error(errorType))
        }
    }.catch { throwable ->
        val errorType = when (throwable) {
            is IOException -> ErrorType.IO_CONNECTION
            else -> ErrorType.GENERIC
        }
        emit(Result.Error(errorType))
    }

    private fun mapResponseCodeToErrorType(responseCode: Int): ErrorType {
        val errorType = when (responseCode) {
            HTTP_UNAUTHORIZED -> ErrorType.UNAUTHORIZED
            in 400..499 -> ErrorType.CLIENT
            in 500..600 -> ErrorType.SERVER
            else -> ErrorType.GENERIC
        }
        return errorType
    }
}
