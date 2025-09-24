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

# Compile if needed
if [ ! -f "ProPreTP.class" ]; then
    echo "Compiling theorem prover..."
    javac -encoding UTF-8 -d . -cp . src/inputoutput/*.java src/predicate/common/*.java src/predicate/scanner/*.java src/predicate/parser/*.java src/predicate/resolution/*.java src/propositional/scanner/*.java src/propositional/common/*.java src/propositional/parser/*.java src/propositional/resolution/*.java src/propositional/sequent/*.java src/ProPreTP.java
    
    if [ $? -ne 0 ]; then
        echo "ERROR: Compilation failed"
        exit 1
    fi
    echo "Compilation successful!"
    echo ""
fi

# Run the theorem prover
echo "Running Theorem Prover..."
echo ""
java ProPreTP

echo ""
echo "Theorem Prover has ended."
