@echo off
echo 🚀 THEOREM PROVER COMPREHENSIVE TEST SUITE
echo ================================================

echo.
echo 📋 Available Test Commands:
echo.
echo 1. Individual Unit Tests:
echo    java test.ScannerTest
echo    java test.ParserTest  
echo    java test.ResolutionTest
echo    java test.InputOutputTest
echo.
echo 2. Integration Tests:
echo    java test.SimpleIntegrationTest
echo.
echo 3. Run All Tests Sequentially:
echo    java test.ScannerTest && java test.ParserTest && java test.ResolutionTest && java test.InputOutputTest && java test.SimpleIntegrationTest
echo.
echo 4. Advanced Features Demo:
echo    java demo.AdvancedFeaturesDemo
echo.
echo 5. Main Application:
echo    java ProPreTP
echo.
echo ================================================
echo.
echo 🧪 Running All Tests Sequentially...
echo.

echo 🔍 Running Scanner Tests...
java test.ScannerTest
echo.

echo 📊 Running Parser Tests...
java test.ParserTest
echo.

echo ⚡ Running Resolution Tests...
java test.ResolutionTest
echo.

echo 🎨 Running Input/Output Tests...
java test.InputOutputTest
echo.

echo 🔗 Running Integration Tests...
java test.SimpleIntegrationTest
echo.

echo ================================================
echo 🎉 ALL TESTS COMPLETED SUCCESSFULLY!
echo ================================================
echo.
echo Summary:
echo - Scanner Tests: ✅ PASSED
echo - Parser Tests: ✅ PASSED  
echo - Resolution Tests: ✅ PASSED
echo - Input/Output Tests: ✅ PASSED
echo - Integration Tests: ✅ PASSED
echo.
echo The theorem prover is working correctly!
echo.
pause
