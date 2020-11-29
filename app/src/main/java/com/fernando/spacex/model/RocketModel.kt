package com.fernando.spacex.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RocketModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("active")
    val isActive: Boolean,
    @SerializedName("first_flight")
    val firstFlight: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("success_rate_pct")
    val successRate: Int,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("flickr_images")
    val imageList: List<String>?
) : Serializable