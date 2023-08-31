package com.example.bestpractices.di

import com.example.bestpractices.navigator.AppFeatureNavigator
import com.example.core_common.navigation.FeatureNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface FeatureNavigatorModule {
    @Binds
    fun bindsFeatureNavigator(featureNavigator: AppFeatureNavigator): FeatureNavigator
}
