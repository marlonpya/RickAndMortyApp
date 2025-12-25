package com.example.rickandmortyapp.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Tests unitarios para el modelo Location del dominio
 */
class LocationTest {
    
    @Test
    fun `location creation with properties`() {
        // When
        val location = Location(
            name = "Earth (C-137)",
            url = "https://rickandmortyapi.com/api/location/1"
        )
        
        // Then
        assertEquals("Earth (C-137)", location.name)
        assertEquals("https://rickandmortyapi.com/api/location/1", location.url)
    }
    
    @Test
    fun `two locations with same data are equal`() {
        // Given
        val location1 = Location("Earth", "url1")
        val location2 = Location("Earth", "url1")
        
        // Then
        assertEquals(location1, location2)
    }
    
    @Test
    fun `two locations with different names are not equal`() {
        // Given
        val location1 = Location("Earth", "url1")
        val location2 = Location("Mars", "url1")
        
        // Then
        assertNotEquals(location1, location2)
    }
}

