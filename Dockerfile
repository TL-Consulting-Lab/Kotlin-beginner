# Use OpenJDK as base image
FROM openjdk:11-jre-slim

# Install Kotlin compiler and necessary tools
RUN apt-get update && \
    apt-get install -y wget unzip curl && \
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

# Compile Kotlin application
RUN kotlinc kotlin/*.kt -include-runtime -d ProductManagementSystem.jar

# Copy frontend files
COPY frontend/ ./frontend/

# Create startup script
RUN echo '#!/bin/bash\n\
echo "Starting Product Management System..."\n\
java -jar ProductManagementSystem.jar &\n\
\n\
# Serve frontend files (optional - for demo purposes)\n\
if [ -d "frontend" ]; then\n\
    echo "Starting frontend server on port 8080..."\n\
    cd frontend\n\
    python3 -m http.server 8080 &\n\
fi\n\
\n\
wait' > startup.sh && chmod +x startup.sh

# Install Python for serving frontend (optional)
RUN apt-get update && apt-get install -y python3 && apt-get clean

# Expose ports
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Start the application
CMD ["./startup.sh"]
