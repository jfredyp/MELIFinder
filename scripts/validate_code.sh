#!/bin/bash

echo "🧹 Ejecutando ktlintFormat (formato automático)..."
./gradlew ktlintFormat

echo "🔍 Ejecutando ktlintCheck (verificación de estilo)..."
./gradlew ktlintCheck || {
  echo "❌ ktlint encontró errores. Por favor corrige antes de continuar."
  exit 1
}

echo "🔬 Ejecutando detekt (análisis estático)..."
./gradlew detekt || {
  echo "❌ Detekt encontró problemas de calidad. Por favor corrige antes de continuar."
  exit 1
}

echo "✅ Validación de código completada con éxito."
