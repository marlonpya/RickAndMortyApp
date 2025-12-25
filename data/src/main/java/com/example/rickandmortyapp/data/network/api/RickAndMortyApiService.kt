package com.example.rickandmortyapp.data.network.api

import com.example.rickandmortyapp.data.network.dto.CharacterResponseDto
import retrofit2.http.GET

/**
 * Interfaz del servicio API para Rick and Morty
 */
interface RickAndMortyApiService {
    
    @GET("character")
    suspend fun getCharacters(): CharacterResponseDto
}

