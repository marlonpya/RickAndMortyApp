package com.example.rickandmortyapp.ui.viewmodel

import com.example.rickandmortyapp.domain.model.Character

/**
 * Sealed class para representar los diferentes estados de la UI
 * Ahora usa el modelo del dominio
 */
sealed class CharacterUiState {
    
    object Loading : CharacterUiState()
    
    data class Success(val characters: List<Character>) : CharacterUiState()
    
    data class Error(val message: String) : CharacterUiState()
}
