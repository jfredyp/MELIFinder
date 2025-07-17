
# 🧰 Configuración del entorno de desarrollo y automatización

Este proyecto incluye un entorno automatizado para garantizar calidad, consistencia y facilidad de uso en entornos Android modernos, especialmente para tareas relacionadas con automatización CI/CD y control de calidad.

---

## ⚙️ ¿Qué es el script `setup.sh`?

Este script automatiza la configuración del entorno de desarrollo y automatización, asegurando que tu sistema tenga:

- Homebrew instalado (gestor de paquetes en macOS)
- `rbenv` con Ruby 3.1.2 instalado (gestionador de versiones de Ruby)
- Fastlane y otras herramientas Ruby instaladas correctamente mediante Bundler
- Validación de archivos como `Gemfile` y `Gemfile.lock`

> ✅ El script puede ejecutarse manualmente o se ejecuta automáticamente con los hooks de Git (post-checkout, post-merge, post-pull).

---

⚠️ **IMPORTANTE: `setup.sh` no es bloqueante**

Aunque este script prepara el entorno para automatización avanzada (`Fastlane`, `rbenv`, `Ruby`, etc.), su fallo **no impide que puedas abrir y correr el proyecto manualmente desde Android Studio**.

Sin embargo, si no se ejecuta correctamente, **no funcionarán las herramientas automáticas** (como firmas, builds automatizados, lanes, y validaciones con Git hooks).

Puedes correrlo manualmente en cualquier momento:

```bash
./scripts/setup.sh
```


## 💎 ¿Qué son las gemas de Ruby y por qué las usamos?

Las gemas de Ruby (`gems`) son paquetes reutilizables que extienden la funcionalidad de Ruby. Son similares a:

- `npm` en Node.js
- `pip` en Python
- `maven` o `gradle` en Java/Kotlin

En este proyecto, se usan para instalar y gestionar herramientas como **Fastlane**, que permite automatizar tareas de DevOps, testing y despliegue.

Las dependencias están definidas en el archivo `Gemfile` y se instalan con:

```bash
bundle install
```

Por defecto, las gemas se instalan localmente en la carpeta `gems/` para evitar conflictos globales.

---

## 🔁 ¿Cuándo se ejecuta automáticamente?

Algunos hooks de Git están configurados para ejecutar `setup.sh` cuando se hace:

- `git checkout` a una nueva rama
- `git merge`
- `git pull`

Esto asegura que, si hay cambios en las herramientas o dependencias, el entorno se mantenga actualizado.

---

## 🛠️ ¿Qué hace exactamente?

Al ejecutar el script `setup.sh`, se espera la siguiente secuencia de acciones:

1. **Instala Homebrew** (si no está instalado)
2. **Instala rbenv y Ruby 3.1.2**
3. **Verifica headers de desarrollo de Ruby** (`ruby.h`)
4. **Valida que existan los archivos `Gemfile` y `Gemfile.lock`**
5. **Instala Bundler y todas las gems declaradas** en el Gemfile

### 🧾 Resultado esperado en consola

```bash
🧰 Iniciando configuración del entorno...
🍺 Homebrew ya está instalado.
🍺 Rbenv ya está instalado.
📦 Ruby 3.1.2 ya está disponible.
✅ Everything appears to be setup correctly!
```

Si alguna dependencia crítica falta, el script mostrará una advertencia clara y detendrá su ejecución para que puedas corregirlo antes de continuar.

---

## 🧪 Modo debug

Puedes activar el modo verbose del script exportando:

```bash
export DEBUG_SETUP=true
```

Esto mostrará todos los comandos ejecutados paso a paso.

---

## 🧷 Hooks de Git utilizados

Los hooks se encuentran en `.git/hooks/` y ejecutan el setup de forma transparente. Se recomienda no modificarlos directamente.

---

## 📦 Siguientes pasos

Ya con el entorno listo, los siguientes pasos incluyen:

- Configurar lanes de Fastlane
- Configurar firma, publicación y entrega continua
- Integrar workflows de GitHub Actions

> Para más detalles, revisa el documento `04_ci_cd.md` o la carpeta `fastlane/`

---

## 📌 Notas adicionales

- Este enfoque es robusto y portable.
- Facilita el onboarding de nuevos developers.
- Previene errores de entorno relacionados con versiones de Ruby, Fastlane o dependencias rotas.

---

¡Tu entorno de desarrollo ya está preparado para prácticas DevOps modernas! 🎯
