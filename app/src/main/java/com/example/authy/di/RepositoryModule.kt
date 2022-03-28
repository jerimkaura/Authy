package com.example.authy.di

import com.example.authy.domain.repository.AuthRepository
import com.example.authy.network.data.AuthyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthRepository(api: AuthyApi): AuthRepository = AuthRepository(api)
}