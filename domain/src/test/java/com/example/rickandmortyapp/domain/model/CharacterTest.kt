package com.example.rickandmortyapp.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Tests unitarios para el modelo Character del dominio
 */
class CharacterTest {
    
    @Test
    fun `character creation with all properties`() {
        // Given
        val location = Location("Earth", "https://rickandmortyapi.com/api/location/1")
        
        // When
        val character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = location,
            location = location,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )
        
        // Then
        assertEquals(1, character.id)
        assertEquals("Rick Sanchez", character.name)
        assertEquals("Alive", character.status)
        assertEquals("Human", character.species)
        assertEquals("Male", character.gender)
        assertEquals(location, character.origin)
    }
    
    @Test
    fun `two characters with same data are equal`() {
        // Given
        val location = Location("Earth", "https://rickandmortyapi.com/api/location/1")
        val character1 = Character(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = location,
            location = location,
            image = "image.jpg",
            episode = listOf("ep1"),
            url = "url",
            created = "date"
        )
        val character2 = Character(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = location,
            location = location,
            image = "image.jpg",
            episode = listOf("ep1"),
            url = "url",
            created = "date"
        )
        
        // Then
        assertEquals(character1, character2)
    }
    
    @Test
    fun `two characters with different ids are not equal`() {
        // Given
        val location = Location("Earth", "url")
        val character1 = Character(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = location,
            location = location,
            image = "image.jpg",
            episode = listOf("ep1"),
            url = "url",
            created = "date"
        )
        val character2 = character1.copy(id = 2)
        
        // Then
        assertNotEquals(character1, character2)
    }
}

