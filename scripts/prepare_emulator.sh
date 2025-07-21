#!/bin/bash

set -e

AVD_NAME="screengrab_pixel"
SYSTEM_IMAGE="system-images;android-28;google_apis;arm64-v8a"
DEVICE_NAME="pixel"

echo "🧪 Verificando si el AVD '$AVD_NAME' ya existe..."

if [ -d "$HOME/.android/avd/${AVD_NAME}.avd" ]; then
  echo "✅ El emulador '$AVD_NAME' ya existe. No es necesario crearlo."
else
  echo "📦 Instalando system image: $SYSTEM_IMAGE"
  yes | "$ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager" "$SYSTEM_IMAGE"

  echo "🛠️  Creando AVD '$AVD_NAME'..."
  echo "no" | "$ANDROID_HOME/cmdline-tools/latest/bin/avdmanager" create avd -n "$AVD_NAME" -k "$SYSTEM_IMAGE" --device "$DEVICE_NAME"
fi

echo "🚀 Iniciando emulador..."
"$ANDROID_HOME/emulator/emulator" -avd "$AVD_NAME" -no-audio -no-window -no-snapshot &

echo "⏳ Esperando a que el emulador esté listo..."
"$ANDROID_HOME/platform-tools/adb" wait-for-device

echo "✅ Emulador listo para ejecutar tests"
