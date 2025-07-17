
# 📘 MELIFinder – Documentación General

Bienvenido al repositorio oficial de **MELIFinder**, una aplicación Android desarrollada como prueba técnica para Mercado Libre. Este proyecto busca demostrar buenas prácticas de arquitectura, calidad de código, automatización y despliegue continuo desde una perspectiva profesional y moderna.

---
## 🚀 ¿De qué trata MELIFinder?

**MELIFinder** es una aplicación que simula la búsqueda inteligente de productos o publicaciones dentro del ecosistema de Mercado Libre. Implementa un flujo robusto que incluye:

- Exploración de ítems por búsqueda textual
- Navegación fluida entre categorías y detalles
- Gestión eficiente del ciclo de vida y consumo de APIs
- Pruebas unitarias y de UI
- Integración con herramientas modernas de calidad y automatización

> La app ha sido estructurada desde cero utilizando herramientas de análisis estático, validación automática de estilo y despliegue automatizado.


Este README sirve como punto de entrada para entender la arquitectura del proyecto, herramientas utilizadas, flujos automatizados y convenciones de calidad.

---
## ✅ Requisitos del entorno

- macOS con Homebrew
- Ruby 3.1.2 administrado por rbenv
- Android Studio + Gradle 8.10.2

> ⚠️ Recomendado ejecutar `./scripts/setup.sh` tras clonar el repo.

---
## 🎨 Tecnologías utilizadas

Este proyecto combina herramientas modernas para asegurar un ciclo de desarrollo robusto y profesional:

- **Kotlin** como lenguaje principal
- **Jetpack Compose** para la UI declarativa
- **AndroidX + Material3** para compatibilidad moderna
- **Detekt + ktlint** para análisis estático y estilo
- **Fastlane** para automatización de tareas (firma, build, tests, despliegue)
- **GitHub Actions** para CI/CD
- **Firebase TestLab** para pruebas en dispositivos reales (en progreso)
- **Gradle 8.10.2** y **Kotlin 1.9.24**

---

## 🧪 Estrategia de testing

- **Unit Tests** con JUnit4
- **Instrumented Tests** sobre `androidTest`
- **Cobertura** planificada con herramientas como `jacoco`
- **Integración futura** con Firebase TestLab para dispositivos físicos/emulados

---

## 🛠️ Automatización CI/CD

- **Validaciones de código** en cada PR con GitHub Actions (`ktlint`, `detekt`, `./gradlew test`)
- **Fastlane** utilizado para generar builds firmados, ejecutar tests, y publicar versiones.
- `./scripts/setup.sh` automatiza la instalación de herramientas clave como Ruby, rbenv, gems, etc.

---

## 🧭 Convenciones y estilo

- **Estilo Kotlin** definido por `ktlint` (v0.49+) y reglas adicionales vía `detekt`
- **Bloqueo de PRs con errores de estilo o análisis estático**
- Git hooks locales refuerzan la validación previa a cada `checkout`, `pull` o `merge`

---

## 🪪 Licencia

Este proyecto está licenciado bajo los términos de la licencia **Apache 2.0**.

Puedes consultar el archivo [LICENSE](LICENSE) incluido en este repositorio para más información.

---



# 📘 Documentación Técnica

Bienvenido a la documentación técnica del proyecto **MELIFinder**, una aplicación Android diseñada para cumplir con los más altos estándares de desarrollo, calidad y automatización.



---

## 📑 Tabla de Contenido

| Sección | Descripción |
|--------|-------------|
| [01 - Configuración del entorno](docs/01_entorno_local.md) | Configuración del entorno local: Script de setup, uso de Ruby, gemas, Fastlane, y Git hooks |
| [02 - Calidad de código](docs/02_calidad_codigo.md) | Reglas, configuración y automatización de `ktlint` y `detekt` |
| [03 - Git Hooks](03_hooks.md) | Hooks automáticos locales para validar el entorno y estilo de código |
| [04 - CI/CD con Fastlane](04_ci_cd.md) | Automatización, firma, despliegue, lanes y GitHub Actions |
| [05 - Seguridad](05_seguridad.md) *(opcional)* | Validación de secretos, firmas, cifrado y mejores prácticas |
| [06 - Testing](06_testing.md) *(en desarrollo)* | Unit/UI tests, integración con Firebase TestLab y mocks |

---

## 🎯 Propósito del Proyecto

MELIFinder busca demostrar un flujo completo de desarrollo profesional, incluyendo:

- Alta calidad de código con herramientas de análisis estático
- Automatización local y remota de tareas críticas
- Integración con CI/CD profesional (Fastlane + GitHub Actions)
- Modularidad, mantenibilidad y escalabilidad

---

## 🛠️ ¿Cómo contribuir o revisar?

1. Revisa `01_entorno_local.md` para asegurar tu entorno.
2. Crea una nueva rama desde `develop`
3. Todo PR será evaluado con GitHub Actions (`lint.yml`)

---

## ✍️ Autor

Jhon Prieto – [LinkedIn](https://www.linkedin.com/in/jhon-prieto-cifuentes1/)

---

¡Gracias por revisar este proyecto! 💛

> “La calidad no es un acto, es un hábito.” – Aristóteles
