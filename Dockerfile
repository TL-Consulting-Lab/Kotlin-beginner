# Use OpenJDK as base image
FROM openjdk:11-jre-slim

# Install all dependencies in one layer
RUN apt-get update && \
    apt-get install -y wget unzip curl python3 && \
    wget -q https://github.com/JetBrains/kotlin/releases/download/v1.9.20/kotlin-compiler-1.9.20.zip && \
    unzip -q kotlin-compiler-1.9.20.zip && \
    mv kotlinc /opt/ && \
    ln -s /opt/kotlinc/bin/kotlin /usr/local/bin/kotlin && \
    ln -s /opt/kotlinc/bin/kotlinc /usr/local/bin/kotlinc && \
    rm kotlin-compiler-1.9.20.zip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy Kotlin source files
COPY kotlin/ ./kotlin/

# Copy frontend files
COPY frontend/ ./frontend/

# Compile Kotlin application with error handling
RUN echo "Compiling Kotlin application..." && \
    kotlinc kotlin/*.kt -include-runtime -d ProductManagementSystem.jar && \
    echo "Kotlin compilation successful!" && \
    ls -la ProductManagementSystem.jar

# Create startup script
RUN echo '#!/bin/bash\n\
echo "Starting Product Management System..."\n\
echo "JAR file location: $(pwd)/ProductManagementSystem.jar"\n\
if [ ! -f "ProductManagementSystem.jar" ]; then\n\
    echo "ERROR: ProductManagementSystem.jar not found!"\n\
    exit 1\n\
fi\n\
\n\
# Start the Kotlin application in background\n\
java -jar ProductManagementSystem.jar &\n\
KOTLIN_PID=$!\n\
\n\
# Serve frontend files on port 8080\n\
if [ -d "frontend" ]; then\n\
    echo "Starting frontend server on port 8080..."\n\
    cd frontend\n\
    python3 -m http.server 8080 &\n\
    FRONTEND_PID=$!\n\
    cd ..\n\
else\n\
    echo "Frontend directory not found"\n\
fi\n\
\n\
# Wait for both processes\n\
wait\n' > startup.sh && chmod +x startup.sh

# Expose ports
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Start the application
CMD ["./startup.sh"]
