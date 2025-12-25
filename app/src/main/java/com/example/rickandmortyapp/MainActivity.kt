package com.example.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rickandmortyapp.ui.screen.CharacterListScreen
import com.example.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import com.example.rickandmortyapp.ui.viewmodel.CharacterViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * MainActivity - Ahora usa Koin para inyección de dependencias
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
                    // Usar Koin para obtener el ViewModel
                    val viewModel: CharacterViewModel = koinViewModel()
                    CharacterListScreen(viewModel = viewModel)
                }
            }
        }
    }
}