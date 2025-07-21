fastlane documentation
----

# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```sh
xcode-select --install
```

For _fastlane_ installation instructions, see [Installing _fastlane_](https://docs.fastlane.tools/#installing-fastlane)

# Available Actions

## Android

### android format_code

```sh
[bundle exec] fastlane android format_code
```

Corrige el estilo de código usando ktlint

### android build_debug

```sh
[bundle exec] fastlane android build_debug
```

Compila la app en modo Debug

### android build_release

```sh
[bundle exec] fastlane android build_release
```

Compila la app en modo Release sin distribución

### android run_tests

```sh
[bundle exec] fastlane android run_tests
```

Ejecuta las pruebas unitarias del proyecto

### android distribute_firebase

```sh
[bundle exec] fastlane android distribute_firebase
```

Distribuye la app a Firebase App Distribution

### android qa_release

```sh
[bundle exec] fastlane android qa_release
```

Ejecuta tests, compila release y distribuye en Firebase (QA Release)

### android capture_screenshots

```sh
[bundle exec] fastlane android capture_screenshots
```

Captura screenshots con Screengrab (UI automation)

### android snapshot_docs

```sh
[bundle exec] fastlane android snapshot_docs
```

Captura screenshots y los guarda para documentación (docs/screenshots/...)

### android test_firebase

```sh
[bundle exec] fastlane android test_firebase
```

Ejecuta pruebas UI (instrumentation) en Firebase Test Lab (Espresso)

### android create_emulator

```sh
[bundle exec] fastlane android create_emulator
```



### android boot_emulator

```sh
[bundle exec] fastlane android boot_emulator
```



### android cleanup_emulator

```sh
[bundle exec] fastlane android cleanup_emulator
```



----

This README.md is auto-generated and will be re-generated every time [_fastlane_](https://fastlane.tools) is run.

More information about _fastlane_ can be found on [fastlane.tools](https://fastlane.tools).

The documentation of _fastlane_ can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
