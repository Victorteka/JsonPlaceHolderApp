package com.victorteka.jsonplaceholderapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.victorteka.jsonplaceholderapp.ui.components.AppBar
import com.victorteka.jsonplaceholderapp.ui.home.HomeScreen
import com.victorteka.jsonplaceholderapp.ui.home.PostsViewModel
import com.victorteka.jsonplaceholderapp.ui.theme.JsonPlaceHolderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PostsViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JsonPlaceHolderAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = {
                        AppBar()
                    }) {
                        Column(
                            Modifier.padding(it)
                        ) {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
