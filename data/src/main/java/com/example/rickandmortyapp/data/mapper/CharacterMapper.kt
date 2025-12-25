package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.data.network.dto.CharacterDto
import com.example.rickandmortyapp.data.network.dto.LocationDto
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.model.Location

/**
 * Mapper para convertir DTOs de la capa de datos a modelos del dominio
 */
object CharacterMapper {
    
    /**
     * Convierte un CharacterDto a un modelo Character del dominio
     */
    fun toDomain(dto: CharacterDto): Character {
        return Character(
            id = dto.id,
            name = dto.name,
            status = dto.status,
            species = dto.species,
            type = dto.type,
            gender = dto.gender,
            origin = toDomain(dto.origin),
            location = toDomain(dto.location),
            image = dto.image,
            episode = dto.episode,
            url = dto.url,
            created = dto.created
        )
    }
    
    /**
     * Convierte un LocationDto a un modelo Location del dominio
     */
    fun toDomain(dto: LocationDto): Location {
        return Location(
            name = dto.name,
            url = dto.url
        )
    }
    
    /**
     * Convierte una lista de CharacterDto a una lista de Character del dominio
     */
    fun toDomainList(dtoList: List<CharacterDto>): List<Character> {
        return dtoList.map { toDomain(it) }
    }
}

