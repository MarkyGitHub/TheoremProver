# 🚀 Theorem Prover Deployment Guide

A comprehensive guide for creating and distributing runnable versions of the theorem prover across different platforms.

## 📋 Table of Contents

- [Quick Deployment](#-quick-deployment)
- [Windows Deployment](#-windows-deployment)
- [Linux Deployment](#-linux-deployment)
- [macOS Deployment](#-macos-deployment)
- [Cross-Platform JAR](#-cross-platform-jar)
- [Docker Deployment](#-docker-deployment)
- [Distribution Packages](#-distribution-packages)
- [User Instructions](#-user-instructions)

## 🚀 Quick Deployment

### Prerequisites

- Java 8 or higher installed
- All source files present
- Compilation environment ready

### Fastest Deployment

```bash
# Windows
theorem-prover.bat

# Linux/macOS
./theorem-prover.sh

# Cross-platform
java -jar theorem-prover.jar
```

## 🪟 Windows Deployment

### Option 1: Batch File (Recommended)

```batch
@echo off
title Theorem Prover
echo Starting Theorem Prover...
java -cp . ProPreTP
pause
```

### Option 2: PowerShell Script

```powershell
Write-Host "Starting Theorem Prover..." -ForegroundColor Green
java -cp . ProPreTP
Read-Host "Press Enter to exit"
```

### Option 3: Windows Executable (.exe)

Use tools like:

- **Launch4j** - Create Windows .exe from JAR
- **jpackage** (Java 14+) - Native application packaging
- **GraalVM** - Native image compilation

### Windows Installation Package

```batch
REM Create installer with NSIS or Inno Setup
REM Include:
REM - Java Runtime Environment check
REM - Theorem prover files
REM - Desktop shortcut
REM - Start menu entry
```

## 🐧 Linux Deployment

### Option 1: Shell Script (Recommended)

```bash
#!/bin/bash
echo "Starting Theorem Prover..."
java -cp . ProPreTP
```

### Option 2: System Package

```bash
# Create .deb package for Ubuntu/Debian
# Create .rpm package for CentOS/RHEL/Fedora
# Create AppImage for universal Linux distribution
```

### Option 3: Snap Package

```yaml
# snapcraft.yaml
name: theorem-prover
version: "2.0"
summary: First-order logic theorem prover
description: |
  A comprehensive theorem prover for propositional and predicate logic.
grade: stable
confinement: strict

parts:
  theorem-prover:
    plugin: dump
    source: .
    build-packages:
      - openjdk-11-jdk
```

### Linux Installation Commands

```bash
# Ubuntu/Debian
sudo apt-get install openjdk-11-jdk
chmod +x theorem-prover.sh
./theorem-prover.sh

# CentOS/RHEL
sudo yum install java-11-openjdk-devel
chmod +x theorem-prover.sh
./theorem-prover.sh

# Arch Linux
sudo pacman -S jdk-openjdk
chmod +x theorem-prover.sh
./theorem-prover.sh
```

## 🍎 macOS Deployment

### Option 1: Shell Script

```bash
#!/bin/bash
echo "Starting Theorem Prover..."
java -cp . ProPreTP
```

### Option 2: macOS Application Bundle

```bash
# Create .app bundle with:
# - Info.plist configuration
# - Java runtime embedding
# - Icon and metadata
```

### Option 3: Homebrew Package

```ruby
# theorem-prover.rb
class TheoremProver < Formula
  desc "First-order logic theorem prover"
  homepage "https://github.com/MarkyGitHub/TheoremProver"
  url "https://github.com/MarkyGitHub/TheoremProver/releases/download/v2.0/theorem-prover.jar"
  sha256 "..."

  depends_on "openjdk@11"

  def install
    libexec.install "theorem-prover.jar"
    bin.write_jar_script libexec/"theorem-prover.jar", "theorem-prover"
  end

  test do
    system "#{bin}/theorem-prover", "--help"
  end
end
```

### macOS Installation

```bash
# Install Java
brew install openjdk@11

# Run theorem prover
chmod +x theorem-prover.sh
./theorem-prover.sh
```

## ☕ Cross-Platform JAR

### Create Executable JAR

```bash
# Compile all classes
javac -encoding UTF-8 -d . -cp . src/**/*.java

# Create JAR with manifest
jar cfm theorem-prover.jar MANIFEST.MF *.class src/

# MANIFEST.MF content:
# Main-Class: ProPreTP
# Class-Path: .
```

### Run Cross-Platform JAR

```bash
# Windows
java -jar theorem-prover.jar

# Linux/macOS
java -jar theorem-prover.jar

# With custom memory settings
java -Xmx1g -jar theorem-prover.jar
```

## 🐳 Docker Deployment

### Dockerfile

```dockerfile
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy compiled classes
COPY . .

# Create non-root user
RUN useradd -m theoremuser && chown -R theoremuser:theoremuser /app
USER theoremuser

# Expose port if needed
EXPOSE 8080

# Run theorem prover
CMD ["java", "-cp", ".", "ProPreTP"]
```

### Docker Commands

```bash
# Build image
docker build -t theorem-prover .

# Run container
docker run -it theorem-prover

# Run with volume mount
docker run -it -v $(pwd):/data theorem-prover
```

## 📦 Distribution Packages

### GitHub Releases

```
theorem-prover-v2.0/
├── windows/
│   ├── theorem-prover.exe
│   ├── theorem-prover.bat
│   └── README.txt
├── linux/
│   ├── theorem-prover.sh
│   ├── theorem-prover.deb
│   └── theorem-prover.rpm
├── macos/
│   ├── theorem-prover.sh
│   ├── theorem-prover.app
│   └── theorem-prover.pkg
├── cross-platform/
│   ├── theorem-prover.jar
│   └── run.sh
└── docker/
    ├── Dockerfile
    └── docker-compose.yml
```

### Package Contents

```
theorem-prover-package/
├── bin/
│   ├── theorem-prover.bat (Windows)
│   ├── theorem-prover.sh (Linux/macOS)
│   └── theorem-prover.jar (Cross-platform)
├── lib/
│   └── [Java runtime if bundled]
├── docs/
│   ├── README.md
│   ├── USER_GUIDE.md
│   └── TESTING_GUIDE.md
├── examples/
│   ├── propositional_examples.txt
│   └── predicate_examples.txt
└── src/ (optional, for developers)
    └── [source code]
```

## 👥 User Instructions

### Windows Users

1. **Download** `theorem-prover-windows.zip`
2. **Extract** to desired folder
3. **Install Java** if not already installed
4. **Run** `theorem-prover.bat`
5. **Follow** on-screen instructions

### Linux Users

1. **Download** `theorem-prover-linux.tar.gz`
2. **Extract** with `tar -xzf theorem-prover-linux.tar.gz`
3. **Install Java**: `sudo apt-get install openjdk-11-jdk`
4. **Make executable**: `chmod +x theorem-prover.sh`
5. **Run**: `./theorem-prover.sh`

### macOS Users

1. **Download** `theorem-prover-macos.dmg`
2. **Install** by dragging to Applications
3. **Install Java**: `brew install openjdk@11`
4. **Run** from Applications or terminal

### Cross-Platform Users

1. **Download** `theorem-prover.jar`
2. **Ensure Java 8+** is installed
3. **Run**: `java -jar theorem-prover.jar`

## 🔧 Advanced Deployment Options

### Native Image (GraalVM)

```bash
# Install GraalVM
# Compile to native executable
native-image -cp . ProPreTP theorem-prover-native

# Results in single executable file
./theorem-prover-native
```

### jpackage (Java 14+)

```bash
# Create native package
jpackage --input . --main-jar theorem-prover.jar --main-class ProPreTP --name theorem-prover --type exe --dest dist

# Results in native installer
dist/theorem-prover.exe
```

### Web Deployment

```bash
# Create web interface with Spring Boot
# Deploy to cloud platforms:
# - Heroku
# - AWS
# - Google Cloud
# - Azure
```

## 📱 Mobile Deployment

### Android

```bash
# Use Android Studio with Java compatibility
# Create APK with theorem prover functionality
# Deploy to Google Play Store
```

### iOS

```bash
# Use Xcode with Java bridge
# Create iOS app with theorem prover
# Deploy to Apple App Store
```

## 🌐 Web Deployment

### Spring Boot Web Application

```java
@RestController
public class TheoremProverController {

    @PostMapping("/prove")
    public ResponseEntity<ProofResult> proveTheorem(@RequestBody FormulaRequest request) {
        // Implement theorem proving logic
        return ResponseEntity.ok(proofResult);
    }
}
```

### Deployment Platforms

- **Heroku**: `git push heroku main`
- **AWS**: Elastic Beanstalk deployment
- **Google Cloud**: App Engine deployment
- **Azure**: Web App deployment

## 📊 Distribution Statistics

### Target Platforms

- ✅ **Windows 10/11** - Batch files and executables
- ✅ **Ubuntu/Debian** - Shell scripts and packages
- ✅ **CentOS/RHEL** - RPM packages
- ✅ **macOS** - Shell scripts and app bundles
- ✅ **Cross-platform** - JAR files
- ✅ **Docker** - Containerized deployment
- 🔄 **Mobile** - Android/iOS (future)
- 🔄 **Web** - Browser-based interface (future)

### Distribution Methods

- ✅ **GitHub Releases** - Direct downloads
- ✅ **Package Managers** - Homebrew, apt, yum
- ✅ **Docker Hub** - Container registry
- ✅ **App Stores** - Future mobile deployment
- ✅ **Web Hosting** - Cloud deployment

---

## 🎉 Deployment Summary

The theorem prover can be deployed across **multiple platforms** with:

- ✅ **Windows** - Batch files and native executables
- ✅ **Linux** - Shell scripts and system packages
- ✅ **macOS** - Shell scripts and app bundles
- ✅ **Cross-platform** - Universal JAR files
- ✅ **Docker** - Containerized deployment
- ✅ **Cloud** - Web-based deployment
- 🔄 **Mobile** - Future Android/iOS support

**Choose the deployment method** that best fits your target users and distribution needs! 🚀
