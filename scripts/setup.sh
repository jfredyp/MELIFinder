#!/bin/bash
set -euo pipefail


if [[ "${DEBUG_SETUP:-false}" == "true" ]]; then
    set -x
fi

FUNCTION_NAME=""
echo "🧰 Iniciando configuración del entorno..."

homebrew() {
    echo "Verifying Homebrew installation..."
    if ! command -v brew >/dev/null 2>&1; then
      echo "🍺 Homebrew no está instalado. Instalándolo ahora..."
      /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
      
      # Agregar Homebrew al PATH para el resto del script
      echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> ~/.zprofile
      eval "$(/opt/homebrew/bin/brew shellenv)"
    else
      echo "🍺 Homebrew ya está instalado."
    fi
}

rbenv() {
    echo "Verifying rbenv installation..."
    
    RUBY_VERSION="3.1.2"
    
    if ! command -v rbenv >/dev/null 2>&1; then
      echo "🔧 Instalando rbenv con Homebrew..."
      brew install rbenv ruby-build
      
      export PATH="$HOME/.rbenv/bin:$PATH"
      eval "$(rbenv init -)"
    
        if ! rbenv versions | grep -q "$RUBY_VERSION"; then
          echo "📦 Instalando Ruby $RUBY_VERSION..."
          rbenv install "$RUBY_VERSION"
        fi

        rbenv global "$RUBY_VERSION"
        
        if [ ! -f "$(ruby -rrbconfig -e 'puts RbConfig::CONFIG["rubyhdrdir"]')/ruby.h" ]; then
          echo "❌ No se encuentra ruby.h. Verifica tu instalación."
          exit 1
        fi

    else
      echo "🍺 Rbenv ya está instalado."
    fi
}

validations() {
    if ! ruby -e "require 'mkmf'; exit(0) if find_header('ruby.h')" > /dev/null 2>&1; then
        echo "❌ No se encuentran los headers de desarrollo para Ruby (ruby.h)"
        echo "💡 Solución: Ejecuta 'xcode-select --install' o 'brew install ruby'"
        exit 1
    fi

    # 2. Validar Gemfile
    if [ ! -f "Gemfile" ]; then
        echo "❌ No se encontró el archivo Gemfile en el directorio actual."
        exit 1
    fi

    
}

ruby_gems() {
    echo "Verifying Ruby gems..."
    ruby -v
    
    if [ ! -f "Gemfile.lock" ]; then
        echo "⚠️  No se encontró Gemfile.lock, se generará uno nuevo al instalar las dependencias."
        
        gem install bundler
    else
        expected_bundler_version=$(tail -n 1 Gemfile.lock | xargs)

        if $(gem list --no-installed --version "$expected_bundler_version" bundler); then
            gem install bundler -v "$expected_bundler_version" --verbose
        fi
    fi
        bundle config set --local path "gems"
        bundle install
}

if [ -z "$FUNCTION_NAME" ]; then
    homebrew
    rbenv
    validations
    ruby_gems
    echo "✅ Everything appears to be setup correctly!"
else
    if declare -f "$FUNCTION_NAME" > /dev/null; then
        "$FUNCTION_NAME"
    else
        echo "Error: Function '$FUNCTION_NAME' does not exist."
        exit 1
    fi
fi
