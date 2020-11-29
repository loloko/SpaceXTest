package com.fernando.spacex.network

import com.fernando.spacex.model.RocketModel
import retrofit2.http.GET

interface SpaceXApi {

    @GET("rockets")
    suspend fun getRockets(): List<RocketModel>

}