# MELIFinder – Arquitectura, Patrones y Estructura

## Índice
1. [Resumen General](#1-resumen-general)  
2. [Arquitectura General](#2-arquitectura-general)  
3. [Estructura Modular](#3-estructura-modular)  
4. [Patrones de Diseño Aplicados](#4-patrones-de-diseño-aplicados)  
5. [Gestión de Estado y UI](#5-gestión-de-estado-y-ui)  
6. [Inyección de Dependencias](#6-inyección-de-dependencias)  
7. [Pruebas y Calidad](#7-pruebas-y-calidad)  
8. [Automatización y Estilo](#8-automatización-y-estilo)  
9. [Módulo network (API y configuración de red)](#9-módulo-network-api-y-configuración-de-red)  
10. [Resumen y Consideraciones Finales](#10-resumen-y-consideraciones-finales)

---

## 1. Resumen General

**MELIFinder** es una aplicación Android modular desarrollada desde cero con enfoque en escalabilidad, mantenibilidad y calidad de código. Todo el flujo está orientado a la búsqueda inteligente de productos y navegación jerárquica de categorías, soportando un robusto manejo de estados y arquitectura moderna.

---

## 2. Arquitectura General

La app implementa una combinación de **Clean Architecture + MVVM (Model-View-ViewModel)** junto con una modularización por capas. El objetivo es lograr:

- Separación clara de responsabilidades.  
- Bajo acoplamiento y alta cohesión.  
- Código testeable y fácilmente extensible.  

**Capas Principales:**

- `presentation`: UI declarativa (Jetpack Compose), ViewModels, manejo de navegación y estados de pantalla.  
- `domain`: Modelos puros, casos de uso y contratos de repositorios. Sin dependencias del Android SDK.  
- `data`: Implementaciones de repositorios, DTOs, mappers y fuentes de datos.  
- `network`: Configuración de red, definición de ApiService, interceptores, clientes y DTOs para comunicación remota.  
- `common`: Utilidades compartidas, constantes, helpers y configuraciones globales.  

**Ejemplo de flujo:**

```
Presentation (UI/Compose/ViewModel)
   ↓
Domain (UseCase/Repository Interface/Models)
   ↓
Data (Repository Impl/API/Mappers)
   ↓
Network (ApiService/DTOs/Retrofit)
```

---

## 3. Estructura Modular

El proyecto se divide en módulos para favorecer el aislamiento y la reusabilidad:

- `:app` – Entrada principal y dependiente de los otros módulos.  
- `:domain` – Modelos puros, interfaces de repositorios y use cases.  
- `:data` – Implementación concreta de repositorios, DTOs, mappers, fuentes remotas.  
- `:network` – Cliente HTTP, ApiService, interceptores, configuración de red.  
- `:common` – Utilidades, helpers, logging, providers.  

**Ventajas:**

- Compilación incremental más rápida.  
- Mejor gestión de dependencias.  
- Posibilidad de testeo y versionado por módulo.  
- Capa de red fácilmente reemplazable para mocks/tests.

---

## 4. Patrones de Diseño Aplicados

- **Repository Pattern**: Casos de uso interactúan con interfaces en `domain`, implementadas en `data`.  
- **Use Case Pattern**: Lógica de negocio encapsulada en clases de caso de uso.  
- **Sealed Class para estados de UI**: Manejo de estados como `Loading`, `Success`, `Error`, `Empty`.  
- **Factory Pattern (opcional)**: Instanciación dinámica según tipo de producto.  
- **Observer Pattern**: `StateFlow` para observar cambios entre ViewModel y UI.  
- **Single Source of Truth**: Solo el ViewModel mantiene el estado observable.  
- **Adapter Delegates (Compose)**: Composición por tipo de ítem como principio de delegación.  
- **Strategy Pattern (opcional)**: Diferentes estrategias para renderizar detalles según tipo.

---

## 5. Gestión de Estado y UI

- **UI Declarativa**: Jetpack Compose como base de toda la UI.  
- **State Management**: ViewModels exponen `StateFlow` con sealed class de estado.  
- **Estados UI**: `Loading`, `Error`, `Empty`, `Success` reflejados de forma reactiva.  
- **Componentes Reusables**: Tarjetas, shimmers, carouseles desacoplados y reutilizables.

---

## 6. Inyección de Dependencias

- **Koin**: Inyección de dependencias por módulo.

```kotlin
val appModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single { SearchProductsUseCase(get()) }
    viewModel { SearchViewModel(get()) }
}
```

---

## 7. Pruebas y Calidad

- **Unit Tests**: Casos de uso y ViewModels testeados.  
- **Análisis Estático**:
  - `ktlint`: Formato de código.
  - `detekt`: Reglas personalizadas.
  - `lint`: Validaciones específicas de Android.
- **CI/CD**:
  - `Fastlane`: Automatización de pruebas, despliegue y análisis.
  - `GitHub Actions`: Validaciones en cada push/pull request.

---

## 8. Automatización y Estilo

- `ktlint`: Hook pre-commit/pre-push.  
- `detekt`: Reglas personalizadas.  
- `Fastlane`: Automatiza builds y distribución.  
- `Lint checks`: Asegura estándares de Android.

---

## 9. Módulo network (API y configuración de red)

`network` encapsula TODO lo relacionado con comunicación HTTP:

- **ApiService**: Define endpoints REST con Retrofit.  
- **Interceptors**: Logging, autenticación, headers personalizados.  
- **DTOs**: Representación cruda de datos.  
- **Retrofit Client Provider**: Configuración de baseUrl, timeouts, etc.  
- **Serialization**: Moshi o Gson configurado para JSON.  
- **Testing**: Compatible con WireMock o MockWebServer.

```kotlin
interface ApiService {
    @GET("products/search")
    suspend fun searchByQuery(@Query("q") query: String): SearchResponseDto

    @GET("products/{product_id}")
    suspend fun getProductDetail(@Path("product_id") productId: String): ProductDetailDto
}

fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(provideOkHttpClient())
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
    .build()
```

---

## 10. Resumen y Consideraciones Finales

El diseño de MELIFinder sigue los más altos estándares actuales de arquitectura Android, aplicando Clean Architecture y buenas prácticas de ingeniería.

**Se optimizó para:**

- Mantenibilidad  
- Extensibilidad  
- Testabilidad  
- Entrega continua  

Todo nuevo desarrollador o evaluador puede comprender el flujo, navegar el código y extender funcionalidades fácilmente.