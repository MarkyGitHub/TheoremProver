# PowerShell script to run all theorem prover tests
Write-Host "🚀 THEOREM PROVER COMPREHENSIVE TEST SUITE" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan

Write-Host ""
Write-Host "📋 Available Test Commands:" -ForegroundColor Yellow
Write-Host ""
Write-Host "1. Individual Unit Tests:" -ForegroundColor Green
Write-Host "   java test.ScannerTest"
Write-Host "   java test.ParserTest"  
Write-Host "   java test.ResolutionTest"
Write-Host "   java test.InputOutputTest"
Write-Host ""
Write-Host "2. Integration Tests:" -ForegroundColor Green
Write-Host "   java test.SimpleIntegrationTest"
Write-Host ""
Write-Host "3. Run All Tests Sequentially:" -ForegroundColor Green
Write-Host "   java test.ScannerTest; java test.ParserTest; java test.ResolutionTest; java test.InputOutputTest; java test.SimpleIntegrationTest"
Write-Host ""
Write-Host "4. Advanced Features Demo:" -ForegroundColor Green
Write-Host "   java demo.AdvancedFeaturesDemo"
Write-Host ""
Write-Host "5. Main Application:" -ForegroundColor Green
Write-Host "   java ProPreTP"
Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

$testResults = @()
$totalTests = 0
$passedTests = 0

Write-Host "🧪 Running All Tests Sequentially..." -ForegroundColor Yellow
Write-Host ""

# Run Scanner Tests
Write-Host "🔍 Running Scanner Tests..." -ForegroundColor Blue
try {
    $output = java test.ScannerTest 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Scanner Tests PASSED" -ForegroundColor Green
        $testResults += "Scanner Tests: ✅ PASSED"
        $passedTests++
    } else {
        Write-Host "❌ Scanner Tests FAILED" -ForegroundColor Red
        $testResults += "Scanner Tests: ❌ FAILED"
    }
    $totalTests++
} catch {
    Write-Host "❌ Scanner Tests FAILED with error: $_" -ForegroundColor Red
    $testResults += "Scanner Tests: ❌ FAILED"
    $totalTests++
}
Write-Host ""

# Run Parser Tests
Write-Host "📊 Running Parser Tests..." -ForegroundColor Blue
try {
    $output = java test.ParserTest 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Parser Tests PASSED" -ForegroundColor Green
        $testResults += "Parser Tests: ✅ PASSED"
        $passedTests++
    } else {
        Write-Host "❌ Parser Tests FAILED" -ForegroundColor Red
        $testResults += "Parser Tests: ❌ FAILED"
    }
    $totalTests++
} catch {
    Write-Host "❌ Parser Tests FAILED with error: $_" -ForegroundColor Red
    $testResults += "Parser Tests: ❌ FAILED"
    $totalTests++
}
Write-Host ""

# Run Resolution Tests
Write-Host "⚡ Running Resolution Tests..." -ForegroundColor Blue
try {
    $output = java test.ResolutionTest 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Resolution Tests PASSED" -ForegroundColor Green
        $testResults += "Resolution Tests: ✅ PASSED"
        $passedTests++
    } else {
        Write-Host "❌ Resolution Tests FAILED" -ForegroundColor Red
        $testResults += "Resolution Tests: ❌ FAILED"
    }
    $totalTests++
} catch {
    Write-Host "❌ Resolution Tests FAILED with error: $_" -ForegroundColor Red
    $testResults += "Resolution Tests: ❌ FAILED"
    $totalTests++
}
Write-Host ""

# Run Input/Output Tests
Write-Host "🎨 Running Input/Output Tests..." -ForegroundColor Blue
try {
    $output = java test.InputOutputTest 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Input/Output Tests PASSED" -ForegroundColor Green
        $testResults += "Input/Output Tests: ✅ PASSED"
        $passedTests++
    } else {
        Write-Host "❌ Input/Output Tests FAILED" -ForegroundColor Red
        $testResults += "Input/Output Tests: ❌ FAILED"
    }
    $totalTests++
} catch {
    Write-Host "❌ Input/Output Tests FAILED with error: $_" -ForegroundColor Red
    $testResults += "Input/Output Tests: ❌ FAILED"
    $totalTests++
}
Write-Host ""

# Run Integration Tests
Write-Host "🔗 Running Integration Tests..." -ForegroundColor Blue
try {
    $output = java test.SimpleIntegrationTest 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Integration Tests PASSED" -ForegroundColor Green
        $testResults += "Integration Tests: ✅ PASSED"
        $passedTests++
    } else {
        Write-Host "❌ Integration Tests FAILED" -ForegroundColor Red
        $testResults += "Integration Tests: ❌ FAILED"
    }
    $totalTests++
} catch {
    Write-Host "❌ Integration Tests FAILED with error: $_" -ForegroundColor Red
    $testResults += "Integration Tests: ❌ FAILED"
    $totalTests++
}
Write-Host ""

Write-Host "================================================" -ForegroundColor Cyan
if ($passedTests -eq $totalTests) {
    Write-Host "🎉 ALL TESTS COMPLETED SUCCESSFULLY!" -ForegroundColor Green
} else {
    Write-Host "⚠️ SOME TESTS FAILED!" -ForegroundColor Yellow
}
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "📊 Test Summary:" -ForegroundColor Yellow
foreach ($result in $testResults) {
    Write-Host "   $result" -ForegroundColor White
}
Write-Host ""
Write-Host "Total Tests: $totalTests" -ForegroundColor White
Write-Host "Passed: $passedTests" -ForegroundColor Green
Write-Host "Failed: $($totalTests - $passedTests)" -ForegroundColor Red
Write-Host "Success Rate: $([math]::Round(($passedTests / $totalTests) * 100, 1))%" -ForegroundColor Cyan
Write-Host ""

if ($passedTests -eq $totalTests) {
    Write-Host "🚀 The theorem prover is working correctly!" -ForegroundColor Green
} else {
    Write-Host "🔧 Some tests need attention." -ForegroundColor Yellow
}
Write-Host ""
