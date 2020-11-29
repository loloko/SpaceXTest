package com.fernando.spacex.repository


import com.fernando.spacex.model.RocketModel
import com.fernando.spacex.network.SpaceXApi
import javax.inject.Inject


class RocketRepository @Inject constructor() {

    @Inject
    lateinit var spaceXApi: SpaceXApi

    suspend fun getRockets(): List<RocketModel> {
        return spaceXApi.getRockets()
    }

}