# 🧪 Theorem Prover Testing Guide

A comprehensive guide for running and managing the theorem prover test suite.

## 📋 Table of Contents

- [Quick Start](#-quick-start)
- [Test Suites Overview](#-test-suites-overview)
- [Running Individual Tests](#-running-individual-tests)
- [Running Complete Test Suite](#-running-complete-test-suite)
- [Advanced Testing](#-advanced-testing)
- [Test Results Interpretation](#-test-results-interpretation)
- [Development Workflow](#-development-workflow)
- [Troubleshooting](#-troubleshooting)
- [Best Practices](#-best-practices)

## 🚀 Quick Start

### Prerequisites

- Java 8 or higher (tested with Java 21)
- All project files compiled

### Fastest Way to Run All Tests

```bash
# Run all tests sequentially
java test.ScannerTest && java test.ParserTest && java test.ResolutionTest && java test.InputOutputTest && java test.SimpleIntegrationTest
```

## 🎯 Test Suites Overview

| Test Suite             | Purpose                 | Tests  | Status |
| ---------------------- | ----------------------- | ------ | ------ |
| **Scanner Tests**      | Tokenization validation | 8      | ✅     |
| **Parser Tests**       | Parsing functionality   | 10     | ✅     |
| **Resolution Tests**   | Theorem proving         | 9      | ✅     |
| **Input/Output Tests** | UI components           | 8      | ✅     |
| **Integration Tests**  | End-to-end workflows    | 3      | ✅     |
| **Total**              | **Complete coverage**   | **38** | **✅** |

## 🔍 Running Individual Tests

### Scanner Tests

Tests the lexical analysis and tokenization functionality.

```bash
java test.ScannerTest
```

**What it validates:**

- ✅ Empty input handling
- ✅ Single variable scanning
- ✅ Simple formula tokenization
- ✅ Complex formula tokenization
- ✅ Invalid input handling
- ✅ Nested parentheses scanning
- ✅ All operators recognition
- ✅ Token precedence assignment

### Parser Tests

Tests the parsing functionality and AST generation.

```bash
java test.ParserTest
```

**What it validates:**

- ✅ Empty input parsing
- ✅ Single variable parsing
- ✅ Simple implication parsing
- ✅ Conjunction parsing
- ✅ Disjunction parsing
- ✅ Negation parsing
- ✅ Operator precedence handling
- ✅ Nested parentheses parsing
- ✅ Complex formula parsing
- ✅ Invalid syntax handling

### Resolution Tests

Tests the theorem proving and resolution algorithm.

```bash
java test.ResolutionTest
```

**What it validates:**

- ✅ Tautology resolution (P => P)
- ✅ Contradiction resolution (P & !P)
- ✅ Transitivity law resolution
- ✅ Modus ponens resolution
- ✅ Double negation resolution
- ✅ De Morgan's law resolution
- ✅ Complex theorem resolution
- ✅ Non-theorem resolution
- ✅ Invalid formula handling

### Input/Output Tests

Tests the user interface components.

```bash
java test.InputOutputTest
```

**What it validates:**

- ✅ Prompt creation and display
- ✅ OutputWriter methods
- ✅ Error message formatting
- ✅ Success message formatting
- ✅ Warning message formatting
- ✅ Info message formatting
- ✅ Section header formatting
- ✅ InputReader creation

### Integration Tests

Tests complete workflows from input to theorem validation.

```bash
java test.SimpleIntegrationTest
```

**What it validates:**

- ✅ Scanner-parser integration
- ✅ Parser-resolution integration
- ✅ Complete workflow validation

## 🏃‍♂️ Running Complete Test Suite

### Option 1: Sequential Execution (Recommended)

```bash
# Windows Command Prompt
java test.ScannerTest && java test.ParserTest && java test.ResolutionTest && java test.InputOutputTest && java test.SimpleIntegrationTest

# PowerShell
java test.ScannerTest; java test.ParserTest; java test.ResolutionTest; java test.InputOutputTest; java test.SimpleIntegrationTest

# Bash/Unix
java test.ScannerTest && java test.ParserTest && java test.ResolutionTest && java test.InputOutputTest && java test.SimpleIntegrationTest
```

### Option 2: Using Test Runner Scripts

```bash
# Windows Batch File
run_tests.bat

# PowerShell Script
.\run_tests.ps1
```

### Option 3: Individual Execution

```bash
# Run each test suite individually for detailed output
java test.ScannerTest
echo "Scanner tests completed"
java test.ParserTest
echo "Parser tests completed"
java test.ResolutionTest
echo "Resolution tests completed"
java test.InputOutputTest
echo "Input/Output tests completed"
java test.SimpleIntegrationTest
echo "Integration tests completed"
```

## 🚀 Advanced Testing

### Advanced Features Demo

Interactive demonstration of all advanced capabilities.

```bash
# Interactive demo (requires user input)
java demo.AdvancedFeaturesDemo
```

**Features demonstrated:**

- 🔬 Algorithm Analysis
- ⚡ Performance Optimization
- 🧪 Advanced Testing Suite
- 🎨 Proof Visualization
- 📊 Complete Demo

### Performance Testing

```bash
# Run advanced test suite with performance benchmarks
java testing.AdvancedTestSuite
```

### Stress Testing

```bash
# Test with complex formulas
echo "((P => Q) & (Q => R) & (R => S) & (S => T)) => (P => T)." | java ProPreTP
```

## 📊 Test Results Interpretation

### Expected Output Format

```
🧪 RUNNING [TEST SUITE] TESTS
==================================================
Testing [test case]...
  ✅ [test name]
Testing [test case]...
  ❌ [test name] - [error message]

📊 [TEST SUITE] TEST RESULTS
==================================================
Passed: X
Failed: Y
Total: Z

🎉 All [test suite] tests passed!
```

### Success Indicators

- ✅ Green checkmarks for passed tests
- 📊 Clear test result summary
- 🎉 Success message at the end
- Exit code 0

### Failure Indicators

- ❌ Red X marks for failed tests
- ⚠️ Warning messages
- Error details in output
- Exit code 1

## 🔧 Development Workflow

### During Development

```bash
# Quick validation after code changes
java test.ScannerTest && java test.ParserTest
```

### Before Committing

```bash
# Full validation before commit
java test.ScannerTest && java test.ParserTest && java test.ResolutionTest && java test.InputOutputTest && java test.SimpleIntegrationTest
```

### After Major Changes

```bash
# Complete test suite + advanced features
java test.ScannerTest && java test.ParserTest && java test.ResolutionTest && java test.InputOutputTest && java test.SimpleIntegrationTest && java demo.AdvancedFeaturesDemo
```

### Continuous Integration

```bash
# Automated testing script
#!/bin/bash
set -e
echo "Running theorem prover test suite..."
java test.ScannerTest
java test.ParserTest
java test.ResolutionTest
java test.InputOutputTest
java test.SimpleIntegrationTest
echo "All tests passed successfully!"
```

## 🛠️ Troubleshooting

### Common Issues

#### Compilation Errors

```bash
# Recompile all test files
javac -encoding UTF-8 -d . -cp . src/test/*.java
```

#### Test Failures

1. **Check error messages** in test output
2. **Verify formula syntax** (must end with `.`)
3. **Ensure proper encoding** (UTF-8)
4. **Check Java version** (Java 8+ required)

#### No Output

```bash
# Run with verbose output
java -verbose test.ScannerTest
```

#### Memory Issues

```bash
# Increase heap size for large tests
java -Xmx1g test.ResolutionTest
```

### Debug Mode

```bash
# Run with debug information
java -Djava.util.logging.config.file=logging.properties test.ScannerTest
```

## 📋 Best Practices

### ✅ Do's

1. **Run tests frequently** during development
2. **Run complete suite** before committing changes
3. **Check all test results** before deployment
4. **Use sequential execution** for reliable results
5. **Document test failures** with error messages
6. **Keep tests updated** with code changes

### ❌ Don'ts

1. **Don't ignore test failures** - fix them immediately
2. **Don't skip integration tests** - they catch workflow issues
3. **Don't run tests in parallel** - they may interfere with each other
4. **Don't modify test files** without understanding their purpose
5. **Don't commit failing tests** - ensure all tests pass first

### 🎯 Testing Strategy

#### Unit Testing

- Test individual components in isolation
- Verify specific functionality
- Catch bugs early in development

#### Integration Testing

- Test component interactions
- Verify complete workflows
- Ensure end-to-end functionality

#### Regression Testing

- Run after each change
- Prevent introduction of bugs
- Maintain system stability

## 📈 Performance Considerations

### Test Execution Time

- **Scanner Tests**: ~1 second
- **Parser Tests**: ~2 seconds
- **Resolution Tests**: ~3 seconds
- **Input/Output Tests**: ~1 second
- **Integration Tests**: ~2 seconds
- **Total**: ~9 seconds

### Optimization Tips

1. **Run only changed components** during development
2. **Use parallel execution** for CI/CD (with caution)
3. **Cache compilation results** to speed up testing
4. **Monitor test performance** over time

## 🔄 Continuous Integration

### GitHub Actions Example

```yaml
name: Theorem Prover Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v2
        with:
          java-version: "21"
          distribution: "temurin"
      - name: Compile
        run: javac -encoding UTF-8 -d . -cp . src/test/*.java
      - name: Run Tests
        run: |
          java test.ScannerTest
          java test.ParserTest
          java test.ResolutionTest
          java test.InputOutputTest
          java test.SimpleIntegrationTest
```

### Jenkins Pipeline

```groovy
pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                sh 'javac -encoding UTF-8 -d . -cp . src/test/*.java'
            }
        }
        stage('Test') {
            steps {
                sh 'java test.ScannerTest'
                sh 'java test.ParserTest'
                sh 'java test.ResolutionTest'
                sh 'java test.InputOutputTest'
                sh 'java test.SimpleIntegrationTest'
            }
        }
    }
}
```

## 📚 Additional Resources

### Related Documentation

- [README.md](README.md) - Main project documentation
- [RUN.md](RUN.md) - Quick start guide
- Source code comments - Detailed implementation notes

### Test Files Location

```
src/test/
├── ScannerTest.java           # Scanner unit tests
├── ParserTest.java            # Parser unit tests
├── ResolutionTest.java        # Resolution unit tests
├── InputOutputTest.java       # UI component tests
├── SimpleIntegrationTest.java # Integration tests
└── TestRunner.java            # Central test runner
```

### Advanced Features

```
src/demo/AdvancedFeaturesDemo.java     # Interactive demo
src/testing/AdvancedTestSuite.java     # Performance tests
src/optimization/PerformanceOptimizer.java # Optimization tests
src/analysis/AlgorithmAnalyzer.java    # Algorithm analysis
```

---

## 🎉 Summary

The theorem prover includes a **comprehensive, production-ready testing framework** with:

- ✅ **38 tests** covering all components
- ✅ **100% pass rate** - all tests working perfectly
- ✅ **Multiple execution options** for different needs
- ✅ **Clear result reporting** with visual indicators
- ✅ **Integration with CI/CD** pipelines
- ✅ **Advanced features** for performance testing

**Remember**: Always run the complete test suite before deploying to ensure system reliability and correctness! 🚀
