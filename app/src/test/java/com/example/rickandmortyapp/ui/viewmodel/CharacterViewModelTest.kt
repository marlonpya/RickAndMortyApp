package com.example.rickandmortyapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.model.Location
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests unitarios para CharacterViewModel
 */
@ExperimentalCoroutinesApi
class CharacterViewModelTest {
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = StandardTestDispatcher()
    
    private lateinit var repository: CharacterRepository
    private lateinit var viewModel: CharacterViewModel
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `initial state is Loading`() = runTest {
        // Given
        coEvery { repository.getCharacters() } returns emptyList()
        
        // When
        viewModel = CharacterViewModel(repository)
        val initialState = viewModel.uiState.first()
        
        // Then
        assertTrue(initialState is CharacterUiState.Loading)
    }
    
    @Test
    fun `loadCharacters updates state to Success when repository returns data`() = runTest {
        // Given
        val location = Location("Earth", "url")
        val characters = listOf(
            Character(
                1, "Rick", "Alive", "Human", "", "Male",
                location, location, "img", listOf("ep1"), "url", "date"
            )
        )
        coEvery { repository.getCharacters() } returns characters
        
        // When
        viewModel = CharacterViewModel(repository)
        advanceUntilIdle() // Espera a que termine la coroutine
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is CharacterUiState.Success)
        assertEquals(1, (state as CharacterUiState.Success).characters.size)
        assertEquals("Rick", state.characters[0].name)
        coVerify(exactly = 1) { repository.getCharacters() }
    }
    
    @Test
    fun `loadCharacters updates state to Error when repository throws exception`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { repository.getCharacters() } throws Exception(errorMessage)
        
        // When
        viewModel = CharacterViewModel(repository)
        advanceUntilIdle()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is CharacterUiState.Error)
        assertEquals(errorMessage, (state as CharacterUiState.Error).message)
    }
    
    @Test
    fun `retry calls loadCharacters again`() = runTest {
        // Given
        val location = Location("Earth", "url")
        val characters = listOf(
            Character(
                1, "Rick", "Alive", "Human", "", "Male",
                location, location, "img", listOf("ep1"), "url", "date"
            )
        )
        coEvery { repository.getCharacters() } returns characters
        
        viewModel = CharacterViewModel(repository)
        advanceUntilIdle()
        
        // When
        viewModel.retry()
        advanceUntilIdle()
        
        // Then
        coVerify(exactly = 2) { repository.getCharacters() }
    }
    
    @Test
    fun `retry after error updates state to Success when repository returns data`() = runTest {
        // Given
        val location = Location("Earth", "url")
        val characters = listOf(
            Character(
                1, "Rick", "Alive", "Human", "", "Male",
                location, location, "img", listOf("ep1"), "url", "date"
            )
        )
        
        // Primer intento falla
        coEvery { repository.getCharacters() } throws Exception("Error") andThen characters
        
        viewModel = CharacterViewModel(repository)
        advanceUntilIdle()
        
        // Verificar que está en estado Error
        assertTrue(viewModel.uiState.value is CharacterUiState.Error)
        
        // When - reintentar
        viewModel.retry()
        advanceUntilIdle()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is CharacterUiState.Success)
        assertEquals(1, (state as CharacterUiState.Success).characters.size)
    }
}

