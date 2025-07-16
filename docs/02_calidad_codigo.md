
# Calidad de Código

Este documento detalla las herramientas y configuraciones utilizadas para asegurar que el código fuente de la aplicación MELIFinder sea consistente, mantenible y de alta calidad.

---

## Herramientas configuradas

### ✅ ktlint

`ktlint` es una herramienta de formateo de código que impone las reglas de estilo oficial de Kotlin. Fue integrada para asegurar consistencia visual en todo el código base.

- **Versión sugerida:** 0.49.0
- **Modo de uso:**
  - `./gradlew ktlintCheck` → Verifica problemas de estilo
  - `./gradlew ktlintFormat` → Corrige problemas automáticamente
- **Integración recomendada:** GitHub Actions en cada Pull Request

---

### ✅ detekt

`detekt` es una herramienta de análisis estático que permite identificar problemas estructurales, malas prácticas y complejidad innecesaria en el código Kotlin.

- **Versión configurada:** 1.23.1
- **Archivo de configuración:** `detekt-config.yml`, ubicado en la raíz del proyecto
- **Reglas clave activadas:**
  - `MagicNumber`
  - `MaxLineLength: 120`
  - `WildcardImport`
  - `ReturnCount`
  - `TooGenericExceptionCaught`
  - `UseDataClass`
  - `ForbiddenComment`
- **Modos de uso local:**
  ```bash
  ./gradlew detekt
  ```

#### 🔍 Primera ejecución

En la primera ejecución se detectaron **9 issues reales**, confirmando el funcionamiento correcto de la herramienta. Algunos errores detectados:

- Uso de nombres de función que no cumplen con la convención camelCase.
- Archivos `.kt` sin salto de línea final.
- Uso de `import *` en archivos de test.
- Comentarios tipo `TODO:` o `FIXME:` marcados como `ForbiddenComment`.

> Esto demuestra que el análisis estático ya está activo y ayudando a elevar la calidad del código fuente.

---

## 🤖 ¿Qué se corrige automáticamente?

| Tipo de error                     | ¿ktlintFormat lo corrige? | ¿detekt lo corrige? | Explicación |
|----------------------------------|----------------------------|----------------------|-------------|
| `import *` (wildcard)            | ❌                         | ❌                   | Requiere saber qué métodos específicos usar |
| Nombres de funciones incorrectos | ❌                         | ❌                   | Afecta referencias internas, debe ser manual |
| Espacios, indentación, saltos    | ✅                         | –                   | Se corrige automáticamente |
| Comentarios `TODO:`              | ❌                         | ❌                   | Se debe justificar o eliminar manualmente |

> `ktlintFormat` corrige formato superficial, pero las estructuras lógicas y violaciones de convenciones profundas deben ser corregidas manualmente por el desarrollador.

---

## Automatización local en compilación (`preBuild`)

Para asegurar que toda compilación se haga solo si el código está limpio y validado, se integraron los siguientes pasos en la task `preBuild` de Gradle:

```kotlin
tasks.named("preBuild") {
    dependsOn("ktlintCheck", "detekt")
}
```

---

## Automatización CI

Se integrará un workflow de GitHub Actions en `.github/workflows/lint.yml` para ejecutar `ktlint`, `detekt` y `assembleDebug` en cada push o pull request.

Esto garantiza que ningún código sin revisión de estilo o análisis estático llegue a la rama principal.

---

## Justificación técnica

Estas herramientas permiten detectar errores comunes antes del runtime, mejorar la mantenibilidad, y asegurar un código homogéneo sin importar qué desarrollador lo haya escrito.

Además, alinean el proyecto con buenas prácticas recomendadas por Google y Kotlin para proyectos Android modernos.
