package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.network.api.RickAndMortyApiService
import com.example.rickandmortyapp.data.network.dto.CharacterDto
import com.example.rickandmortyapp.data.network.dto.CharacterResponseDto
import com.example.rickandmortyapp.data.network.dto.InfoDto
import com.example.rickandmortyapp.data.network.dto.LocationDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Tests unitarios para CharacterRepositoryImpl usando MockK
 */
class CharacterRepositoryImplTest {
    
    private lateinit var apiService: RickAndMortyApiService
    private lateinit var repository: CharacterRepositoryImpl
    
    @Before
    fun setup() {
        apiService = mockk()
        repository = CharacterRepositoryImpl(apiService)
    }
    
    @Test
    fun `getCharacters returns list of domain characters on success`() = runTest {
        // Given
        val locationDto = LocationDto("Earth", "url")
        val characterDto = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = locationDto,
            location = locationDto,
            image = "image.jpg",
            episode = listOf("ep1"),
            url = "url",
            created = "date"
        )
        val infoDto = InfoDto(count = 1, pages = 1, next = null, prev = null)
        val responseDto = CharacterResponseDto(info = infoDto, results = listOf(characterDto))
        
        coEvery { apiService.getCharacters() } returns responseDto
        
        // When
        val result = repository.getCharacters()
        
        // Then
        assertEquals(1, result.size)
        assertEquals("Rick Sanchez", result[0].name)
        assertEquals("Alive", result[0].status)
        coVerify(exactly = 1) { apiService.getCharacters() }
    }
    
    @Test
    fun `getCharacters returns empty list when API returns empty results`() = runTest {
        // Given
        val infoDto = InfoDto(count = 0, pages = 0, next = null, prev = null)
        val responseDto = CharacterResponseDto(info = infoDto, results = emptyList())
        
        coEvery { apiService.getCharacters() } returns responseDto
        
        // When
        val result = repository.getCharacters()
        
        // Then
        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { apiService.getCharacters() }
    }
    
    @Test(expected = Exception::class)
    fun `getCharacters throws exception when API call fails`() = runTest {
        // Given
        coEvery { apiService.getCharacters() } throws Exception("Network error")
        
        // When
        repository.getCharacters()
        
        // Then - exception is thrown
    }
}

