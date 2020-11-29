package com.fernando.spacex.di

import com.fernando.spacex.repository.RocketRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRocketRepository(): RocketRepository = RocketRepository()
}