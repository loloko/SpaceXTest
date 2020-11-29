package com.fernando.spacex.di

import com.fernando.spacex.helpers.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    // Retrofit
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.SPACE_X_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}
