package com.example.rickandmortyapp

import android.app.Application
import com.example.rickandmortyapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Application class para inicializar Koin
 */
class RickAndMortyApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicializar Koin
        startKoin {
            androidLogger(Level.ERROR) // Solo mostrar errores en logs
            androidContext(this@RickAndMortyApplication)
            modules(appModule)
        }
    }
}

