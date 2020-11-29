package com.fernando.spacex.di.main


import com.fernando.spacex.adapter.RocketAdapter
import com.fernando.spacex.network.SpaceXApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    @MainScope
    @Provides
    fun provideSpaceXApi(retrofit: Retrofit): SpaceXApi {
        return retrofit.create(SpaceXApi::class.java)
    }

    @MainScope
    @Provides
    fun provideRocketAdapter(): RocketAdapter = RocketAdapter()


}