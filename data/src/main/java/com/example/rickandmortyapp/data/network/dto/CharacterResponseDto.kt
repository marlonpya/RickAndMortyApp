package com.example.rickandmortyapp.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * DTO para la respuesta de la API con información de paginación
 */
data class CharacterResponseDto(
    @SerializedName("info")
    val info: InfoDto,
    
    @SerializedName("results")
    val results: List<CharacterDto>
)

