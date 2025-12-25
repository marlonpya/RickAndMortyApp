package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmortyapp.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyapp.ui.screen.CharacterListScreen
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.example.rickandmortyapp.ui.viewmodel.CharacterViewModel

/**
 * MainActivity - Usa repositorio del módulo data
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Crear ViewModel con repositorio manualmente (sin DI todavía)
                    val repository = CharacterRepositoryImpl()
                    val viewModel: CharacterViewModel = viewModel(
                        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                    ) {
                        CharacterViewModel(repository)
                    }
                    CharacterListScreen(viewModel = viewModel)
                }
            }
        }
    }
}
