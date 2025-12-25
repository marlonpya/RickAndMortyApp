package com.example.rickandmortyapp.domain.model

/**
 * Modelo de dominio para un personaje de Rick and Morty
 * Este modelo es puro, sin dependencias de librerías externas
 */
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

