package com.example.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para la pantalla de lista de personajes
 * Ahora usa la interface del dominio, no la implementación directa
 */
class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()
    
    init {
        loadCharacters()
    }
    
    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading
            
            try {
                val characters = repository.getCharacters()
                _uiState.value = CharacterUiState.Success(characters)
            } catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(
                    message = e.message ?: "Error desconocido al cargar personajes"
                )
            }
        }
    }
    
    fun retry() {
        loadCharacters()
    }
}
