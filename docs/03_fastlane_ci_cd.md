# 🚀 CI/CD con Fastlane: Automatización y despliegue continuo

Fastlane es una herramienta de automatización open-source diseñada específicamente para desarrolladores móviles. Permite simplificar, automatizar y estandarizar tareas repetitivas como compilar, testear, firmar y distribuir apps.

## ¿Por qué Fastlane?

- ✅ **Automatización sin fricción:** reduce errores manuales.
- 🔐 **Manejo seguro de credenciales:** ideal para CI/CD.
- 🧪 **Facilidad de ejecución de pruebas:** tanto unitarias como instrumentadas.
- 🚀 **Distribución ágil:** publica builds a Firebase, Play Store, etc.
- 🤖 **Integración perfecta con GitHub Actions o Azure DevOps.**

## ¿Qué hace Fastlane en este proyecto?

Se ha configurado Fastlane para facilitar tareas clave en el ciclo de vida del desarrollo Android. Estas tareas están organizadas en "lanes", que encapsulan flujos de trabajo reutilizables.

### ✨ Lanes definidas

| Lane                 | Descripción                                                                 |
|----------------------|------------------------------------------------------------------------------|
| `format_code`        | Corrección automática de estilo con `ktlint`.                               |
| `build_debug`        | Compilación de la app en modo Debug.                                        |
| `build_release`      | Compilación de la app en modo Release (sin distribuir).                     |
| `run_tests`          | Ejecución de pruebas unitarias.                                             |
| `distribute_firebase`| Distribución del APK en Firebase App Distribution.                          |
| `qa_release`         | Pipeline completo: test → build → distribución para QA.                     |
| `capture_screenshots`| Toma screenshots automatizados con `screengrab`.                            |
| `snapshot_docs`      | Copia los screenshots para documentación técnica.                           |
| `test_firebase`      | Ejecuta pruebas instrumentadas en Firebase Test Lab (ver `06_firebase_testlab.md`). |
| `create_emulator`    | Prepara un AVD para pruebas locales o screenshots.                          |
| `boot_emulator`      | Inicia el emulador configurado.                                             |
| `cleanup_emulator`   | Finaliza y cierra el emulador.                                               |

## 🧪 Plugins utilizados

```text
✔️ Plugin: fastlane-plugin-firebase_app_distribution (v0.10.1)
```
## 📥 Distribución automatizada

Mediante la lane `distribute_firebase`, se distribuye automáticamente a testers internos:

```bash
bundle exec fastlane android distribute_firebase
```

## 🔐 Seguridad

- Uso de credenciales `.json` para acceso restringido a Firebase
- Exclusión en `.gitignore` asegurada


## 🧬 CI/CD Listo para GitHub Actions o Azure

Fastlane se integra fácilmente con pipelines de CI/CD. El archivo `Fastfile` puede ser invocado desde:
- GitHub Actions (`actions/checkout`, `actions/setup-java`, etc.)
- Azure Pipelines (con un `script` directo o integración con tareas YAML)
- Jenkins, Bitrise, CircleCI, entre otros.

## 📦 Distribución y calidad

Fastlane se apoya en:
- [`firebase_app_distribution`](https://github.com/fastlane/fastlane-plugin-firebase_app_distribution): para distribuir releases.
- [`ktlint`](https://ktlint.github.io): para mantener el estilo de código.
- [`detekt`](https://detekt.dev): para análisis estático.
- [`Test Lab`](06_firebase_testlab.md): para ejecutar pruebas en dispositivos reales.

> ⚠️ Las credenciales se almacenan en `fastlane/credentials/firebase-service-account.json` y no se deben compartir.

---

