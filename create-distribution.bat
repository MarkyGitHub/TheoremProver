@echo off
title Creating Theorem Prover Distribution Package
color 0B

echo.
echo ========================================
echo   CREATING DISTRIBUTION PACKAGE
echo ========================================
echo.

REM Create distribution directory
if not exist "distribution" mkdir distribution
if not exist "distribution\windows" mkdir distribution\windows
if not exist "distribution\linux" mkdir distribution\linux
if not exist "distribution\cross-platform" mkdir distribution\cross-platform
if not exist "distribution\docs" mkdir distribution\docs

echo Step 1: Compiling core application...
javac -encoding Windows-1252 -d . -cp . src/inputoutput/*.java src/predicate/common/*.java src/predicate/scanner/*.java src/predicate/parser/*.java src/predicate/resolution/*.java src/propositional/scanner/*.java src/propositional/common/*.java src/propositional/parser/*.java src/propositional/resolution/*.java src/propositional/sequent/*.java src/ProPreTP.java

if %errorlevel% neq 0 (
    echo ERROR: Core compilation failed
    pause
    exit /b 1
)

echo Step 2: Compiling distribution version...
javac -encoding Windows-1252 -d . -cp . ProPreTP-Distribution.java

if %errorlevel% neq 0 (
    echo ERROR: Distribution compilation failed
    pause
    exit /b 1
)

echo Step 3: Creating Windows package...
copy theorem-prover.bat distribution\windows\
copy ProPreTP_Distribution.class distribution\windows\
copy src distribution\windows\ /E /I
copy *.class distribution\windows\ /Y
copy README.md distribution\windows\
copy TESTING_GUIDE.md distribution\windows\
copy DEPLOYMENT_GUIDE.md distribution\windows\

echo Step 4: Creating Linux package...
copy theorem-prover.sh distribution\linux\
copy ProPreTP_Distribution.class distribution\linux\
copy src distribution\linux\ /E /I
copy *.class distribution\linux\ /Y
copy README.md distribution\linux\
copy TESTING_GUIDE.md distribution\linux\
copy DEPLOYMENT_GUIDE.md distribution\linux\

echo Step 5: Creating cross-platform JAR...
cd distribution\cross-platform
copy ..\windows\* . /E /I /Y

REM Create manifest for JAR
echo Main-Class: ProPreTP_Distribution > MANIFEST.MF
echo Class-Path: . >> MANIFEST.MF

REM Create JAR file
jar cfm theorem-prover.jar MANIFEST.MF *.class src\* inputoutput\* predicate\* propositional\*

cd ..\..

echo Step 6: Copying documentation...
copy README.md distribution\docs\
copy TESTING_GUIDE.md distribution\docs\
copy DEPLOYMENT_GUIDE.md distribution\docs\

echo Step 7: Creating user instructions...
echo # Theorem Prover - User Instructions > distribution\USER_INSTRUCTIONS.md
echo. >> distribution\USER_INSTRUCTIONS.md
echo ## Windows Users >> distribution\USER_INSTRUCTIONS.md
echo 1. Ensure Java 8+ is installed >> distribution\USER_INSTRUCTIONS.md
echo 2. Run: theorem-prover.bat >> distribution\USER_INSTRUCTIONS.md
echo. >> distribution\USER_INSTRUCTIONS.md
echo ## Linux Users >> distribution\USER_INSTRUCTIONS.md
echo 1. Install Java: sudo apt-get install openjdk-11-jdk >> distribution\USER_INSTRUCTIONS.md
echo 2. Make executable: chmod +x theorem-prover.sh >> distribution\USER_INSTRUCTIONS.md
echo 3. Run: ./theorem-prover.sh >> distribution\USER_INSTRUCTIONS.md
echo. >> distribution\USER_INSTRUCTIONS.md
echo ## Cross-Platform Users >> distribution\USER_INSTRUCTIONS.md
echo 1. Ensure Java 8+ is installed >> distribution\USER_INSTRUCTIONS.md
echo 2. Run: java -jar theorem-prover.jar >> distribution\USER_INSTRUCTIONS.md

echo.
echo ========================================
echo   DISTRIBUTION PACKAGE CREATED!
echo ========================================
echo.
echo Package contents:
echo   distribution\windows\     - Windows executable and files
echo   distribution\linux\       - Linux executable and files  
echo   distribution\cross-platform\ - Cross-platform JAR file
echo   distribution\docs\        - Documentation files
echo.
echo Ready for distribution!
echo.
pause
