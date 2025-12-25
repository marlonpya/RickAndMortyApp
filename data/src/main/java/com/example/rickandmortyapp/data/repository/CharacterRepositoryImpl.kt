package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.mapper.CharacterMapper
import com.example.rickandmortyapp.data.network.api.ApiClient
import com.example.rickandmortyapp.data.network.api.RickAndMortyApiService
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.CharacterRepository

/**
 * Implementación del repositorio de personajes
 * Usa la API como fuente de datos y convierte DTOs a modelos de dominio
 */
class CharacterRepositoryImpl(
    private val apiService: RickAndMortyApiService = ApiClient.apiService
) : CharacterRepository {
    
    override suspend fun getCharacters(): List<Character> {
        return try {
            val response = apiService.getCharacters()
            CharacterMapper.toDomainList(response.results)
        } catch (e: Exception) {
            throw e
        }
    }
}

