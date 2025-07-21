#!/bin/bash

set -euo pipefail
IFS=$'\n\t'
trap 'echo "❌ Error en línea $LINENO. Abortando." >&2' ERR

echo "🔍 Buscando modelo compatible en Firebase Test Lab..."


PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
APP_APK="$PROJECT_ROOT/app/build/outputs/apk/debug/app-debug.apk"
TEST_APK="$PROJECT_ROOT/app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk"

# Lista de modelos preferidos
preferred_models=("redfin" "shiba" "panther")
required_version=30

# Obtener lista de modelos y versiones
devices=$(gcloud firebase test android models list --format="csv[no-heading](MODEL_ID,OS_VERSION_IDS)")

selected_model=""
selected_version=""

# Buscar el primer modelo compatible
for model in "${preferred_models[@]}"; do
  while IFS=',' read -r model_id versions; do
    if [[ "$model_id" == "$model" ]]; then
      IFS=';' read -ra version_array <<< "$versions"
      for version in "${version_array[@]}"; do
        if [[ "$version" =~ ^[0-9]+$ ]] && [[ "$version" -ge "$required_version" ]]; then
          selected_model=$model_id
          selected_version=$version
          break 2
        fi
      done
    fi
  done <<< "$devices"
done

if [[ -z "$selected_model" ]]; then
  echo "❌ No se encontró ningún modelo compatible con API $required_version+ entre: ${preferred_models[*]}"
  exit 1
fi

echo "✅ Modelo seleccionado: $selected_model con API $selected_version"
echo "🧪 Ejecutando pruebas con modelo=$selected_model versión=$selected_version"

# Ejecutar pruebas
gcloud firebase test android run \
  --type instrumentation \
  --app "$APP_APK" \
  --test "$TEST_APK" \
  --device model="$selected_model",version="$selected_version",locale=en,orientation=portrait \
  --timeout 3m \
  --verbosity debug

echo "🎉 ¡Todo listo! Las pruebas se ejecutaron correctamente en Firebase Test Lab."
