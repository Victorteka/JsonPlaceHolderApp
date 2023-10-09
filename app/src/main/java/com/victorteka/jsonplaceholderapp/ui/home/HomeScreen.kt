package com.victorteka.jsonplaceholderapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorteka.jsonplaceholderapp.R
import com.victorteka.jsonplaceholderapp.ui.components.ErrorScreen
import com.victorteka.jsonplaceholderapp.ui.components.LoaderComponent
import com.victorteka.jsonplaceholderapp.ui.components.PostItem

@Composable
fun HomeScreen() {
    val viewModel: PostsViewModel = hiltViewModel()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenState by viewModel.state.collectAsState()

        if(screenState.isLoading){
            LoaderComponent()
        }
        if (screenState.error != null){
            ErrorScreen(errorId = screenState.error ?: R.string.error_generic) {
                viewModel.getPosts()
            }
        }
        if (screenState.posts != null){
            LazyColumn {
                screenState.posts?.let { posts ->
                    items(posts){ post ->
                        PostItem(
                            post = post
                        )
                    }
                }
            }
        }
    }
}