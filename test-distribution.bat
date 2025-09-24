@echo off
echo Testing Distribution Version...
echo.

REM Test compilation
echo Compiling distribution version...
javac -encoding Windows-1252 -d . -cp . ProPreTP-Distribution.java

if %errorlevel% neq 0 (
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

echo Compilation successful!
echo.

REM Test with simple input
echo Testing with sample input...
echo A
echo P => P.
echo 0
) | java ProPreTP_Distribution

echo.
echo Test completed!
pause
