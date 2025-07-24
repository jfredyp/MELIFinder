
# 📘 MELIFinder – Documentación General

Bienvenido al repositorio oficial de **MELIFinder**, una aplicación Android desarrollada como prueba técnica para Mercado Libre. Este proyecto busca demostrar buenas prácticas de arquitectura, calidad de código, automatización y despliegue continuo desde una perspectiva profesional y moderna.

---
## 🚀 ¿De qué trata MELIFinder?


<div style="display: flex; align-items: center; gap: 24px;">
  <div style="flex: 4;">
    <b>MELIFinder</b> es una aplicación que simula la búsqueda inteligente de productos o publicaciones dentro del ecosistema de Mercado Libre. Implementa un flujo robusto que incluye:
    <ul>
      <li>Exploración de ítems por búsqueda textual</li>
      <li>Navegación fluida entre categorías y detalles</li>
      <li>Gestión eficiente del ciclo de vida y consumo de APIs</li>
      <li>Pruebas unitarias y de UI</li>
      <li>Integración con herramientas modernas de calidad y automatización</li>
    </ul>
  </div>
    <div style="flex: 1; text-align: left;">
    <img src="docs/demo.gif" width="180" alt="Demo">
  </div>
</div>

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


## 🚀 Automatización con Fastlane y Firebase

Se usa [`Fastlane`](https://fastlane.tools/) para automatizar la generación del APK y su distribución con [`Firebase App Distribution`](https://firebase.google.com/products/app-distribution).

Esto permite:

- Distribución rápida a testers o stakeholders sin necesidad de builds manuales.
- Flujo reproducible y documentado.
- Escalabilidad futura hacia Firebase Test Lab para testing automatizado en granjas de dispositivos.

### Comando principal de distribución:

```bash
bundle exec fastlane android distribute_firebase
```

### 🔐 Seguridad de credenciales

Este proyecto **no incluye credenciales sensibles en el repositorio**.

Para ejecutar el `lane` de distribución (`distribute_firebase`), es necesario tener un archivo de cuenta de servicio de Firebase, que **debe generarse manualmente** y colocarse localmente en:

```bash
fastlane/credentials/firebase-service-account.json
```


Este archivo está excluido del control de versiones (`.gitignore`) para mantener la seguridad del proyecto y cumplir con las buenas prácticas profesionales.

> 📌 Si deseas probar la distribución, asegúrate de crear tu propia cuenta de servicio con el rol `Firebase App Distribution Admin` y vincularla a tu app Firebase o contactame para compartir contigo y hacer una demostración en vivo.


---

## 🧭 Convenciones y estilo

- **Estilo Kotlin** definido por `ktlint` (v0.49+) y reglas adicionales vía `detekt`
- **Bloqueo de PRs con errores de estilo o análisis estático**
- Git hooks locales refuerzan la validación previa a cada `checkout`, `pull` o `merge`

---

## 🧹 Calidad del Código

Este proyecto implementa análisis estático y formateo automático con:

- [`detekt`](https://detekt.dev/) – para análisis estático según las [Kotlin Coding Conventions](https://developer.android.com/kotlin/style-guide).
- [`ktlint`](https://github.com/pinterest/ktlint) – para formateo de código y orden de imports.

Esto asegura:

- Consistencia con las guidelines oficiales de Android.
- Prevención de errores comunes.
- Código limpio, escalable y profesional.

---

## 🧪 Firebase Test Lab: Calidad en dispositivos reales

Como parte del enfoque para esta prueba técnica, se incorporó la integración con **Firebase Test Lab**, la plataforma de pruebas automatizadas de Google que permite ejecutar *instrumented tests* en una granja de dispositivos físicos y virtuales.

Aunque la automatización completa aún está en progreso, se implementó lo siguiente:

### 🔄 Script automatizado (`testlab_run.sh`) que:

- Construye el APK y el test APK con Gradle.
- Ejecuta pruebas en Firebase Test Lab seleccionando automáticamente un modelo compatible.
- Intenta capturar el `matrix_id` para análisis futuro.

### ✅ Validación de resultados

- Los *outcomes* se imprimen y validan.
- Se aborta la ejecución en caso de fallos.

### 🚀 Ejecución desde Fastlane

Este flujo es ejecutado directamente desde una lane de Fastlane (`test_firebase`), manteniendo el pipeline coherente y alineado con las buenas prácticas de CI/CD.

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
| [03 - CI/CD con Fastlane](docs/03_fastlane_ci_cd.md) | Automatización, firma, despliegue, lanes y GitHub Actions |
| [04 - Git Hooks](03_hooks.md) | Hooks automáticos locales para validar el entorno y estilo de código |
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
## 🎬 Demo

![Demo](docs/demo.gif)


---


## ✍️ Autor

Jhon Prieto – [LinkedIn](https://www.linkedin.com/in/jhon-prieto-cifuentes1/)

---

¡Gracias por revisar este proyecto! 💛

> “La calidad no es un acto, es un hábito.” – Aristóteles
