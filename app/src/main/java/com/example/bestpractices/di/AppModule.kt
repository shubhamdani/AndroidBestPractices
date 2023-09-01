package com.example.bestpractices.di

import com.example.core_common.di.DispatchersIO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @DispatchersIO
    fun provideDispatchersIO(): CoroutineDispatcher = Dispatchers.IO

}