#!/bin/bash

# Local Docker Testing Script for Kotlin Product Management System

echo "🐳 Building and testing Kotlin application locally..."

# Build Docker image
echo "📦 Building Docker image..."
docker build -t kotlin-product-management .

if [ $? -ne 0 ]; then
    echo "❌ Docker build failed!"
    exit 1
fi

echo "✅ Docker image built successfully!"

# Run container
echo "🚀 Starting container on port 8080..."
docker run -d -p 8080:8080 --name kotlin-app-test kotlin-product-management

if [ $? -ne 0 ]; then
    echo "❌ Failed to start container!"
    exit 1
fi

echo "⏳ Waiting for application to start..."
sleep 10

# Test application
echo "🧪 Testing application..."
curl -f http://localhost:8080 && echo "✅ Application is running!" || echo "❌ Application test failed!"

echo ""
echo "📋 Container information:"
docker ps | grep kotlin-app-test

echo ""
echo "📝 Container logs:"
docker logs kotlin-app-test

echo ""
echo "🌐 Access your application at: http://localhost:8080"
echo "🛑 To stop the container: docker stop kotlin-app-test"
echo "🗑️  To remove the container: docker rm kotlin-app-test"
