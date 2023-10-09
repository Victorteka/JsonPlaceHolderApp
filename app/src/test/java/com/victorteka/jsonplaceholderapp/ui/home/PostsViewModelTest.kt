package com.victorteka.jsonplaceholderapp.ui.home

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.victorteka.jsonplaceholderapp.core.repository.PostsRepository
import com.victorteka.jsonplaceholderapp.data.mappers.toCoreModel
import com.victorteka.jsonplaceholderapp.data.remote.PostsApiService
import com.victorteka.jsonplaceholderapp.util.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class PostsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @MockK
    private lateinit var repository: PostsRepository
    @MockK
    val postsApiService = mockk<PostsApiService>(relaxed = true)

    private lateinit var postsViewModel: PostsViewModel

    @Before
    fun setUp() {
       MockKAnnotations.init(this)
        postsViewModel = PostsViewModel(repository)
    }

    @Test
    fun `test getPosts() success scenario`() = runBlocking {
        coEvery {
            postsApiService.getPosts()
        } returns Response.success(listOf(postResponseDto))

        every {
            repository.getPosts()
        } returns flowOf(Result.Success(listOf(postResponseDto.toCoreModel())))

        val expectedState = HomeScreenViewState(
            isLoading = false,
            posts = listOf(postResponseDto.toCoreModel()),
            error = null
        )
        postsViewModel.getPosts()
        postsViewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }
}