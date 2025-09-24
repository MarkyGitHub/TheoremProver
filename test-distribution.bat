@echo off
echo Testing Distribution Version...
echo.

REM Check if JAR file exists
if not exist "theorem-prover.jar" (
    echo Creating JAR file...
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

REM Test with JAR file
echo Testing JAR file with sample input...
echo A
echo P => P.
echo 0
) | java -jar theorem-prover.jar

echo.
echo Test completed!
pause
