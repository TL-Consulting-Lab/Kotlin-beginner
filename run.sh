#!/bin/bash

# Product Management System - Build and Run Script

echo "🚀 Building Product Management System..."

# Check if Kotlin is installed
if ! command -v kotlinc &> /dev/null; then
    echo "❌ Kotlin compiler not found. Please install Kotlin first."
    echo "📥 You can install Kotlin using:"
    echo "   brew install kotlin (on macOS with Homebrew)"
    echo "   or download from https://kotlinlang.org/"
    exit 1
fi

# Compile the Kotlin files
echo "🔨 Compiling Kotlin files..."
kotlinc kotlin/*.kt -include-runtime -d ProductManagementSystem.jar

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo "🎯 Running Product Management System..."
    echo ""
    java -jar ProductManagementSystem.jar
else
    echo "❌ Compilation failed!"
    exit 1
fi
