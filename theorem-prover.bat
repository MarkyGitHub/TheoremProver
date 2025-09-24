@echo off
title Theorem Prover - First-Order Logic Theorem Prover
color 0A

echo.
echo ========================================
echo   THEOREM PROVER - WINDOWS VERSION
echo ========================================
echo.
echo Starting Theorem Prover...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit /b 1
)

REM Check if JAR file exists, create if needed
if not exist "theorem-prover.jar" (
    echo Creating JAR file for distribution...
    echo Compiling distribution version...
    javac -encoding Windows-1252 -d . -cp . ProPreTP_Distribution.java
    if %errorlevel% neq 0 (
        echo ERROR: Compilation failed
        pause
        exit /b 1
    )
    
    echo Creating JAR file...
    echo Main-Class: ProPreTP_Distribution > MANIFEST.MF
    jar cf theorem-prover.jar MANIFEST.MF *.class
    if %errorlevel% neq 0 (
        echo ERROR: JAR creation failed
        pause
        exit /b 1
    )
    echo JAR file created successfully!
    echo.
)

REM Run the theorem prover using JAR file
echo Running Theorem Prover from JAR file...
echo.
java -jar theorem-prover.jar

echo.
echo Theorem Prover has ended.
pause
