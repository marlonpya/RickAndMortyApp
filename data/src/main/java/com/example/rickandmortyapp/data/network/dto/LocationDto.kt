package com.example.rickandmortyapp.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para ubicaciones (origin y location)
 */
data class LocationDto(
    @SerializedName("name")
    val name: String,
    
    @SerializedName("url")
    val url: String
)

