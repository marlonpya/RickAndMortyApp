# Rick and Morty App - Arquitectura Multi-modular

Aplicación Android que muestra personajes de la serie Rick and Morty, implementando una arquitectura limpia y escalable con separación en módulos independientes.

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.0.21-purple?style=for-the-badge&logo=kotlin" alt="Kotlin">
  <img src="https://img.shields.io/badge/Android-24%2B-green?style=for-the-badge&logo=android" alt="Android">
  <img src="https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge" alt="Architecture">
  <img src="https://img.shields.io/badge/DI-Koin-orange?style=for-the-badge" alt="Koin">
</p>

---

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Arquitectura](#-arquitectura)
- [Stack Tecnológico](#️-stack-tecnológico)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Instalación](#-instalación)
- [Testing](#-testing)
- [Uso de AI](#-uso-de-ai)

---

## ✨ Características

- ✅ **Arquitectura Multi-modular** - Separación en 3 módulos independientes
- ✅ **MVVM + Repository Pattern** - Arquitectura limpia y escalable
- ✅ **Inyección de Dependencias** - Koin para DI moderna
- ✅ **Jetpack Compose** - UI declarativa y moderna
- ✅ **Tests Unitarios** - Cobertura con MockK
- ✅ **Separación DTO/Domain** - Modelos puros de dominio
- ✅ **StateFlow** - Manejo reactivo de estados
- ✅ **Sealed Classes** - Type-safe state management

---

## 🏗️ Arquitectura

Este proyecto implementa una **arquitectura multi-modular** con separación clara de responsabilidades:

```
┌─────────────────────────────────────────────┐
│              app (Presentation)              │
│  • UI (Jetpack Compose)                     │
│  • ViewModels                               │
│  • Dependency Injection (Koin)              │
└─────────────────┬───────────────────────────┘
                  │
        ┌─────────┴─────────┐
        │                   │
┌───────▼────────┐  ┌──────▼──────┐
│     data       │  │   domain    │
│  • API Client  │  │  • Models   │
│  • DTOs        │  │  • Repos    │
│  • Mappers     │  │  (Pure)     │
│  • Repo Impl   │  │             │
└────────────────┘  └─────────────┘
```

### Módulos

#### 📦 `domain/`
- **Responsabilidad:** Lógica de negocio pura
- **Contenido:** 
  - Modelos de dominio (sin anotaciones)
  - Interfaces de repositorios
- **Dependencias:** ❌ Sin dependencias externas

#### 📦 `data/`
- **Responsabilidad:** Acceso a datos
- **Contenido:**
  - DTOs con anotaciones Gson
  - Retrofit API Service
  - Mappers (DTO → Domain)
  - Implementación de repositorios
- **Dependencias:** `domain`, Retrofit, OkHttp, Gson

#### 📦 `app/`
- **Responsabilidad:** Presentación
- **Contenido:**
  - UI con Jetpack Compose
  - ViewModels
  - Configuración de Koin
- **Dependencias:** `domain`, `data`, Compose, Koin

---

## 🛠️ Stack Tecnológico

### Core
- **Kotlin** `2.0.21` - Lenguaje de programación
- **Gradle Kotlin DSL** - Build system
- **Min SDK** `24` | **Target SDK** `36`

### UI
- **Jetpack Compose** `2024.09.00` - UI declarativa
- **Material Design 3** - Componentes de UI
- **Coil** `2.7.0` - Carga de imágenes

### Arquitectura
- **MVVM** - Patrón arquitectónico
- **Repository Pattern** - Abstracción de datos
- **StateFlow** - Manejo de estados reactivo
- **Coroutines** `1.9.0` - Programación asíncrona

### Networking
- **Retrofit** `2.11.0` - Cliente HTTP
- **OkHttp** `4.12.0` - Cliente HTTP subyacente
- **Gson** - Serialización JSON

### Inyección de Dependencias
- **Koin** `3.5.6` - DI ligero para Kotlin

### Testing
- **JUnit** `4.13.2` - Framework de testing
- **MockK** `1.13.8` - Mocking para Kotlin
- **Coroutines Test** `1.9.0` - Testing asíncrono
- **Arch Core Testing** `2.2.0` - Testing de componentes Architecture

---

## 📁 Estructura del Proyecto

```
RickAndMortyApp/
│
├── domain/                                 # Módulo de Dominio
│   └── src/main/java/.../domain/
│       ├── model/
│       │   ├── Character.kt               # Modelo puro
│       │   └── Location.kt
│       └── repository/
│           └── CharacterRepository.kt      # Interface
│
├── data/                                   # Módulo de Datos
│   └── src/main/java/.../data/
│       ├── network/
│       │   ├── api/
│       │   │   ├── ApiClient.kt
│       │   │   └── RickAndMortyApiService.kt
│       │   └── dto/
│       │       ├── CharacterDto.kt         # DTO con @SerializedName
│       │       ├── LocationDto.kt
│       │       ├── CharacterResponseDto.kt
│       │       └── InfoDto.kt
│       ├── mapper/
│       │   └── CharacterMapper.kt          # DTO → Domain
│       └── repository/
│           └── CharacterRepositoryImpl.kt  # Implementación
│
└── app/                                    # Módulo de Presentación
    └── src/main/java/.../
        ├── di/
        │   └── AppModule.kt                # Configuración Koin
        ├── ui/
        │   ├── screen/
        │   │   ├── CharacterListScreen.kt
        │   │   └── CharacterItem.kt
        │   ├── theme/
        │   │   ├── Color.kt
        │   │   ├── Theme.kt
        │   │   └── Type.kt
        │   └── viewmodel/
        │       ├── CharacterViewModel.kt
        │       └── CharacterUiState.kt
        ├── MainActivity.kt
        └── RickAndMortyApplication.kt      # Inicialización Koin
```

---

## 🚀 Instalación

### Prerequisitos

- **Android Studio** Hedgehog (2023.1.1) o superior
- **JDK** 11 o superior
- **Android SDK** API 24 o superior

### Pasos

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd RickAndMortyApp
   ```

2. **Abrir en Android Studio**
   ```
   File → Open → Seleccionar carpeta del proyecto
   ```

3. **Sincronizar Gradle**
   ```
   File → Sync Project with Gradle Files
   ```

4. **Ejecutar la aplicación**
   ```bash
   ./gradlew installDebug
   ```
   O usar el botón **Run** en Android Studio

---

## 🧪 Testing

El proyecto incluye tests unitarios en los 3 módulos:

### Ejecutar Tests

```bash
# Todos los tests
./gradlew testDebugUnitTest

# Tests por módulo
./gradlew :domain:testDebugUnitTest
./gradlew :data:testDebugUnitTest
./gradlew :app:testDebugUnitTest
```

### Cobertura de Tests

**Total:** 18 tests unitarios

| Módulo | Tests | Descripción |
|--------|-------|-------------|
| `domain` | 6 | Tests de modelos puros |
| `data` | 7 | Tests de mappers y repositorio (MockK) |
| `app` | 5 | Tests de ViewModel (Coroutines Test) |

**Ejemplos de tests:**
- ✅ Creación y validación de modelos
- ✅ Conversión DTO → Domain
- ✅ Mocking de API Service
- ✅ Estados del ViewModel (Loading, Success, Error)
- ✅ Manejo de errores y retry

---

## 🎯 Funcionalidades

### Implementadas

- ✅ **Lista de personajes** - LazyColumn con scroll
- ✅ **Carga de imágenes** - Coil con placeholder y error handling
- ✅ **Estados de UI** - Loading, Success, Error con Sealed Classes
- ✅ **Retry** - Botón para reintentar en caso de error
- ✅ **Indicador de estado** - Color según status (Alive/Dead/Unknown)

### Roadmap (Mejoras Futuras)

- 🔄 **Paginación** - Scroll infinito
- 📱 **Pantalla de Detalle** - Información completa del personaje
- 🔍 **Búsqueda y Filtros** - Por nombre, estado, especie
- 💾 **Cache Local** - Room Database para offline-first
- 🔄 **Pull to Refresh** - Swipe para actualizar
- 🌙 **Dark Mode** - Soporte de tema oscuro
- 🌐 **Internacionalización** - Múltiples idiomas

---

## 📚 API

Este proyecto consume la [Rick and Morty API](https://rickandmortyapi.com/):

- **Base URL:** `https://rickandmortyapi.com/api/`
- **Endpoint:** `GET /character`
- **Documentación:** [rickandmortyapi.com/documentation](https://rickandmortyapi.com/documentation)

---

## 🤖 Uso de AI

### Análisis de Arquitectura

Este proyecto fue desarrollado utilizando **AI (Claude/Cursor)** para:

- 🔍 **Análisis previo de la solución** - Evaluación de mejores prácticas y patrones arquitectónicos
- 🏗️ **Diseño de arquitectura multi-modular** - Separación óptima de responsabilidades
- 📝 **Generación de código base** - Estructura inicial y boilerplate
- ✅ **Implementación de tests** - Casos de prueba con MockK
- 📚 **Documentación** - Este README fue generado con asistencia de AI

### Contribución de AI

La AI contribuyó en:
- ✅ Análisis de trade-offs entre diferentes arquitecturas
- ✅ Recomendaciones sobre separación de módulos
- ✅ Implementación de patrones (Repository, Mapper, DI)
- ✅ Configuración de herramientas (Koin, MockK)
- ✅ Mejores prácticas de testing
- ✅ Estructura y contenido de documentación

El uso de AI permitió:
- ⚡ **Desarrollo más rápido** sin sacrificar calidad
- 🎯 **Foco en decisiones de diseño** en lugar de boilerplate
- 📚 **Documentación completa** desde el inicio
- ✅ **Tests desde el día 1** con cobertura adecuada

---

## 🏆 Decisiones Técnicas

### ¿Por qué Multi-modular?

- ✅ **Compilación incremental** - Solo recompila módulos modificados
- ✅ **Separación de responsabilidades** - Cada módulo tiene un propósito claro
- ✅ **Reutilización** - Módulos `domain` y `data` reutilizables
- ✅ **Escalabilidad** - Fácil agregar nuevas features
- ✅ **Testing** - Cada módulo se prueba independientemente

### ¿Por qué Koin sobre Hilt?

- ✅ **Simplicidad** - Sin generación de código
- ✅ **Curva de aprendizaje** - Más fácil de entender
- ✅ **Flexibilidad** - Menos opinado que Hilt
- ✅ **Performance** - Inyección en runtime es suficiente para este proyecto

### ¿Por qué StateFlow sobre LiveData?

- ✅ **Compose-first** - Mejor integración con Compose
- ✅ **Type-safe** - Mayor seguridad de tipos
- ✅ **Coroutines nativo** - Diseñado para coroutines
- ✅ **Menos boilerplate** - API más simple

---

## 👤 Autor

**Marlon Arteaga**
- Email: marlon.arteaga.m@hotmail.com

---

## 📄 Licencia

Este proyecto fue desarrollado como ejercicio de arquitectura Android.

---

## 🙏 Agradecimientos

- [Rick and Morty API](https://rickandmortyapi.com/) - API pública gratuita
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Toolkit de UI
- [Koin](https://insert-koin.io/) - Framework de DI
- [MockK](https://mockk.io/) - Mocking library para Kotlin

---

<p align="center">
  Desarrollado con ❤️ usando Kotlin y Jetpack Compose
  <br>
  <em>Con asistencia de AI para análisis y documentación</em>
</p>

