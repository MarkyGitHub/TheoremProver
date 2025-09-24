#!/bin/bash

# Theorem Prover - Linux/macOS Version
# Author: Mark Schlichtmann
# Version: 2.0

echo ""
echo "========================================"
echo "   THEOREM PROVER - LINUX VERSION"
echo "========================================"
echo ""
echo "Starting Theorem Prover..."
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 8 or higher:"
    echo "  Ubuntu/Debian: sudo apt-get install openjdk-11-jdk"
    echo "  CentOS/RHEL: sudo yum install java-11-openjdk-devel"
    echo "  macOS: brew install openjdk@11"
    echo ""
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
if [ "$JAVA_VERSION" -lt 8 ]; then
    echo "ERROR: Java 8 or higher is required. Current version: $JAVA_VERSION"
    echo "Please upgrade Java"
    echo ""
    exit 1
fi

# Check if JAR file exists, create if needed
if [ ! -f "theorem-prover.jar" ]; then
    echo "Creating JAR file for distribution..."
    echo "Compiling distribution version..."
    javac -encoding Windows-1252 -d . -cp . ProPreTP_Distribution.java
    
    if [ $? -ne 0 ]; then
        echo "ERROR: Compilation failed"
        exit 1
    fi
    
    echo "Creating JAR file..."
    echo "Main-Class: ProPreTP_Distribution" > MANIFEST.MF
    jar cf theorem-prover.jar MANIFEST.MF *.class
    
    if [ $? -ne 0 ]; then
        echo "ERROR: JAR creation failed"
        exit 1
    fi
    echo "JAR file created successfully!"
    echo ""
fi

# Run the theorem prover using JAR file
echo "Running Theorem Prover from JAR file..."
echo ""
java -jar theorem-prover.jar

echo ""
echo "Theorem Prover has ended."
