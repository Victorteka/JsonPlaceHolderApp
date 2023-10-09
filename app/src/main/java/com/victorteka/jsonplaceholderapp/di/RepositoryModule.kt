package com.victorteka.jsonplaceholderapp.di

import com.victorteka.jsonplaceholderapp.core.repository.PostsRepository
import com.victorteka.jsonplaceholderapp.data.remote.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun providePostsRepository(postRepositoryImpl: PostRepositoryImpl): PostsRepository
}