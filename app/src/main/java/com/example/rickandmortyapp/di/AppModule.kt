package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.data.network.api.ApiClient
import com.example.rickandmortyapp.data.network.api.RickAndMortyApiService
import com.example.rickandmortyapp.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import com.example.rickandmortyapp.ui.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Módulo de Koin para la inyección de dependencias
 */
val appModule = module {
    
    // Network layer
    single<RickAndMortyApiService> { ApiClient.apiService }
    
    // Repository layer
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    
    // ViewModel layer
    viewModel { CharacterViewModel(get()) }
}

