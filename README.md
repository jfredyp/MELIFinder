# 🚀 Bienvenido a MELIFinder

¡Gracias por clonar este repositorio! Este proyecto es una prueba técnica para Mercado Libre y contiene una aplicación Android avanzada con herramientas profesionales como CI/CD, análisis estático, automatización con Fastlane, y más.

---

## 🪂 Estás en la rama `landing`

Esta rama ha sido diseñada exclusivamente como punto de entrada inicial. Aquí encontrarás únicamente los archivos esenciales para comenzar, incluyendo:

- `README.md` (este archivo)
- `scripts/setup.sh`
- Hooks de Git opcionales (si están configurados)

---

## 📦 ¿Cómo continuar?

Para empezar a trabajar con la app, debes cambiar a la rama principal del proyecto:

```bash
git checkout main
```

Esto descargará todo el código fuente y la configuración completa del proyecto Android.

---

## ⚙️ ¿Qué hace `./scripts/setup.sh`?

Este script configura automáticamente las herramientas necesarias para entornos macOS como:

- Homebrew
- rbenv + Ruby 3.1.2
- Bundler + Fastlane

También valida y prepara todo para que puedas ejecutar firmas, builds automáticos, validaciones de código y despliegue.

---

## ⚠️ ¿Qué pasa si `setup.sh` falla?

👉 El script **no bloquea** el uso del repositorio ni tu `checkout`.

- Puedes abrir el proyecto en Android Studio y compilarlo manualmente.
- Solo perderás las automatizaciones avanzadas (Fastlane, Git hooks, validadores, etc.).

Puedes ejecutarlo manualmente más adelante si lo deseas:

```bash
./scripts/setup.sh
```

---

## ✅ Siguientes pasos

```bash
git checkout main
```

> Una vez en `main`, ejecuta `./scripts/setup.sh` y estarás listo para comenzar 🚀

---

## 📚 Documentación completa de la aplicación

Aquí termina la sección de preparación del entorno.

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
> ## ✅ Requisitos del ento..

**¿Quieres seguir leyendo? Para ver la documentación completa, conocer la arquitectura, herramientas, convenciones y estructura completa de MELIFinder, visita la siguiente documentación:**

📄 [Ver README completo de la app en la rama `develop` →](https://github.com/jfredyp/MELIFinder/blob/develop/README.md)
