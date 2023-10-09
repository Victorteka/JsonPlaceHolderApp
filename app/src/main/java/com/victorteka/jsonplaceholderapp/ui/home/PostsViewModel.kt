package com.victorteka.jsonplaceholderapp.ui.home

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorteka.jsonplaceholderapp.R
import com.victorteka.jsonplaceholderapp.core.model.Post
import com.victorteka.jsonplaceholderapp.core.repository.PostsRepository
import com.victorteka.jsonplaceholderapp.util.ErrorType
import com.victorteka.jsonplaceholderapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenViewState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            repository.getPosts().collect { result ->
                processResult(result)
            }
        }
    }

    private fun processResult(result: Result<List<Post>>) {
        when (result) {
            is Result.Success -> {
                val posts = result.data
                _state.value = _state.value.copy(
                    isLoading = false,
                    posts = posts
                )
            }
            is Result.Error -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = mapErrorTypeToResource(result.errorType)
                )
            }
        }
    }

    private fun mapErrorTypeToResource(errorType: ErrorType): Int = when (errorType) {
        ErrorType.GENERIC -> R.string.error_generic
        ErrorType.IO_CONNECTION -> R.string.error_client
        ErrorType.UNAUTHORIZED -> R.string.error_unauthorized
        ErrorType.CLIENT -> R.string.error_client
        ErrorType.SERVER -> R.string.error_server
    }
}

data class HomeScreenViewState(
    val isLoading: Boolean = false,
    val posts: List<Post>? = null,
    @StringRes val error: Int? = null
)
