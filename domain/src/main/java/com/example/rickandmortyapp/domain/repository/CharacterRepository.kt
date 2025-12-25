package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.domain.model.Character

/**
 * Interface del repositorio de personajes
 * Define el contrato para el acceso a datos sin exponer detalles de implementación
 */
interface CharacterRepository {
    
    /**
     * Obtiene la lista de personajes
     * @return Lista de personajes del dominio
     * @throws Exception si hay un error al obtener los datos
     */
    suspend fun getCharacters(): List<Character>
}

