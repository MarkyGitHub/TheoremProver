@echo off
title Creating Theorem Prover JAR File
color 0B

echo.
echo ========================================
echo   CREATING THEOREM PROVER JAR FILE
echo ========================================
echo.

echo Step 1: Compiling core classes...
javac -encoding Windows-1252 -d . -cp . src/inputoutput/*.java src/predicate/common/*.java src/predicate/scanner/*.java src/predicate/parser/*.java src/predicate/resolution/*.java src/propositional/scanner/*.java src/propositional/common/*.java src/propositional/parser/*.java src/propositional/resolution/*.java src/propositional/sequent/*.java

if %errorlevel% neq 0 (
    echo ERROR: Core compilation failed
    pause
    exit /b 1
)

echo Step 2: Compiling distribution version...
javac -encoding Windows-1252 -d . -cp . ProPreTP_Distribution.java

if %errorlevel% neq 0 (
    echo ERROR: Distribution compilation failed
    pause
    exit /b 1
)

echo Step 3: Creating manifest file...
echo Main-Class: ProPreTP_Distribution > MANIFEST.MF
echo Class-Path: . >> MANIFEST.MF

echo Step 4: Creating JAR file...
jar cfm theorem-prover.jar MANIFEST.MF *.class src/ inputoutput/ predicate/ propositional/

if %errorlevel% neq 0 (
    echo ERROR: JAR creation failed
    pause
    exit /b 1
)

echo.
echo ========================================
echo   JAR FILE CREATED SUCCESSFULLY!
echo ========================================
echo.
echo JAR file: theorem-prover.jar
echo.
echo To run the JAR file:
echo   java -jar theorem-prover.jar
echo.
echo To test the JAR file:
echo   java -jar theorem-prover.jar
echo.
echo Ready for distribution!
echo.
pause
