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

REM Compile if needed
if not exist "ProPreTP.class" (
    echo Compiling theorem prover...
    javac -encoding Windows-1252 -d . -cp . src/inputoutput/*.java src/predicate/common/*.java src/predicate/scanner/*.java src/predicate/parser/*.java src/predicate/resolution/*.java src/propositional/scanner/*.java src/propositional/common/*.java src/propositional/parser/*.java src/propositional/resolution/*.java src/propositional/sequent/*.java src/ProPreTP.java
    if %errorlevel% neq 0 (
        echo ERROR: Compilation failed
        pause
        exit /b 1
    )
    echo Compilation successful!
    echo.
)

REM Run the theorem prover
echo Running Theorem Prover...
echo.
java ProPreTP

echo.
echo Theorem Prover has ended.
pause
