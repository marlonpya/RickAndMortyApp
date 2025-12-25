package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.data.network.dto.CharacterDto
import com.example.rickandmortyapp.data.network.dto.LocationDto
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests unitarios para CharacterMapper
 */
class CharacterMapperTest {
    
    @Test
    fun `toDomain converts CharacterDto to Character correctly`() {
        // Given
        val locationDto = LocationDto(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        )
        val characterDto = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = locationDto,
            location = locationDto,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )
        
        // When
        val character = CharacterMapper.toDomain(characterDto)
        
        // Then
        assertEquals(characterDto.id, character.id)
        assertEquals(characterDto.name, character.name)
        assertEquals(characterDto.status, character.status)
        assertEquals(characterDto.species, character.species)
        assertEquals(characterDto.type, character.type)
        assertEquals(characterDto.gender, character.gender)
        assertEquals(characterDto.image, character.image)
        assertEquals(characterDto.episode, character.episode)
        assertEquals(characterDto.url, character.url)
        assertEquals(characterDto.created, character.created)
        assertEquals(locationDto.name, character.origin.name)
        assertEquals(locationDto.url, character.origin.url)
    }
    
    @Test
    fun `toDomain converts LocationDto to Location correctly`() {
        // Given
        val locationDto = LocationDto(
            name = "Earth",
            url = "https://rickandmortyapi.com/api/location/1"
        )
        
        // When
        val location = CharacterMapper.toDomain(locationDto)
        
        // Then
        assertEquals(locationDto.name, location.name)
        assertEquals(locationDto.url, location.url)
    }
    
    @Test
    fun `toDomainList converts list of DTOs to list of domain models`() {
        // Given
        val locationDto = LocationDto("Earth", "url")
        val dtos = listOf(
            CharacterDto(
                1, "Rick", "Alive", "Human", "", "Male",
                locationDto, locationDto, "img1", listOf("ep1"), "url1", "date1"
            ),
            CharacterDto(
                2, "Morty", "Alive", "Human", "", "Male",
                locationDto, locationDto, "img2", listOf("ep2"), "url2", "date2"
            )
        )
        
        // When
        val characters = CharacterMapper.toDomainList(dtos)
        
        // Then
        assertEquals(2, characters.size)
        assertEquals("Rick", characters[0].name)
        assertEquals("Morty", characters[1].name)
    }
    
    @Test
    fun `toDomainList with empty list returns empty list`() {
        // Given
        val emptyList = emptyList<CharacterDto>()
        
        // When
        val result = CharacterMapper.toDomainList(emptyList)
        
        // Then
        assertEquals(0, result.size)
    }
}

