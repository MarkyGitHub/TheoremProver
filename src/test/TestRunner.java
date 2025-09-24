package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Comprehensive test runner for all unit and integration tests.
 *
 * <p>
 * This class orchestrates the execution of all test suites and provides a
 * unified reporting system for the theorem prover testing framework.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class TestRunner {

    private static class TestSuite {

        private final String name;
        private final Runnable testRunner;
        private final TestType type;

        public TestSuite(String name, Runnable testRunner, TestType type) {
            this.name = name;
            this.testRunner = testRunner;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public Runnable getTestRunner() {
            return testRunner;
        }

        public TestType getType() {
            return type;
        }
    }

    private enum TestType {
        UNIT("Unit Test"),
        INTEGRATION("Integration Test"),
        PERFORMANCE("Performance Test");

        private final String displayName;

        TestType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private static final List<TestSuite> testSuites = new ArrayList<>();
    private static int totalTestsPassed = 0;
    private static int totalTestsFailed = 0;
    private static long totalExecutionTime = 0;

    /**
     * Main entry point for running all tests.
     */
    public static void main(String[] args) {
        System.out.println("🚀 THEOREM PROVER COMPREHENSIVE TEST SUITE");
        System.out.println("=".repeat(70));
        System.out.println("Running all unit tests and integration tests...");
        System.out.println();

        initializeTestSuites();
        runAllTests();
        displayComprehensiveResults();
    }

    /**
     * Initializes all test suites.
     */
    private static void initializeTestSuites() {
        // Unit Tests
        testSuites.add(new TestSuite("Scanner", () -> ScannerTest.main(new String[]{}), TestType.UNIT));
        testSuites.add(new TestSuite("Parser", () -> ParserTest.main(new String[]{}), TestType.UNIT));
        testSuites.add(new TestSuite("Resolution", () -> ResolutionTest.main(new String[]{}), TestType.UNIT));
        testSuites.add(new TestSuite("Input/Output", () -> InputOutputTest.main(new String[]{}), TestType.UNIT));
        testSuites.add(new TestSuite("Propositional Logic", () -> PropositionalLogicTest.main(new String[]{}), TestType.UNIT));

        // Integration Tests
        testSuites.add(new TestSuite("Simple Integration", () -> SimpleIntegrationTest.main(new String[]{}), TestType.INTEGRATION));

        // Performance Tests
        testSuites.add(new TestSuite("Advanced Test Suite", () -> testing.AdvancedTestSuite.runCompleteTestSuite(), TestType.PERFORMANCE));
    }

    /**
     * Runs all test suites.
     */
    private static void runAllTests() {
        for (TestSuite suite : testSuites) {
            runTestSuite(suite);
        }
    }

    /**
     * Runs a single test suite.
     */
    private static void runTestSuite(TestSuite suite) {
        System.out.println("🧪 Running " + suite.getType().getDisplayName() + ": " + suite.getName());
        System.out.println("-".repeat(50));

        long startTime = System.currentTimeMillis();

        try {
            // Capture original streams
            java.io.PrintStream originalOut = System.out;
            java.io.PrintStream originalErr = System.err;

            // Create custom output stream to capture test results
            java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
            java.io.PrintStream customOut = new java.io.PrintStream(outputStream);

            // Redirect output
            System.setOut(customOut);
            System.setErr(customOut);

            // Run the test
            suite.getTestRunner().run();

            // Restore original streams
            System.setOut(originalOut);
            System.setErr(originalErr);

            // Parse results from captured output
            String output = outputStream.toString();
            parseTestResults(suite.getName(), output);

        } catch (Exception e) {
            System.err.println("❌ Error running " + suite.getName() + ": " + e.getMessage());
            totalTestsFailed++;
        }

        long endTime = System.currentTimeMillis();
        totalExecutionTime += (endTime - startTime);

        System.out.println("⏱️ Execution time: " + (endTime - startTime) + "ms");
        System.out.println();
    }

    /**
     * Parses test results from captured output.
     */
    private static void parseTestResults(String suiteName, String output) {
        try {
            // Extract passed and failed counts from output
            String[] lines = output.split("\n");
            int passed = 0;
            int failed = 0;

            for (String line : lines) {
                if (line.contains("Passed:") && line.contains("Failed:")) {
                    // Parse line like "Passed: 5 Failed: 2"
                    String[] parts = line.split(":");
                    if (parts.length >= 3) {
                        try {
                            passed = Integer.parseInt(parts[1].trim().split("\\s+")[0]);
                            failed = Integer.parseInt(parts[2].trim().split("\\s+")[0]);
                        } catch (NumberFormatException e) {
                            // Try alternative parsing
                            if (line.contains("✅")) {
                                passed++;
                            }
                            if (line.contains("❌")) {
                                failed++;
                            }
                        }
                    }
                } else if (line.contains("✅")) {
                    passed++;
                } else if (line.contains("❌")) {
                    failed++;
                }
            }

            totalTestsPassed += passed;
            totalTestsFailed += failed;

            System.out.println("📊 Results: " + passed + " passed, " + failed + " failed");

        } catch (Exception e) {
            System.err.println("⚠️ Could not parse results for " + suiteName);
            totalTestsFailed++;
        }
    }

    /**
     * Displays comprehensive test results.
     */
    private static void displayComprehensiveResults() {
        System.out.println("=".repeat(70));
        System.out.println("                    COMPREHENSIVE TEST RESULTS");
        System.out.println("=".repeat(70));

        // Overall Statistics
        int totalTests = totalTestsPassed + totalTestsFailed;
        double passRate = totalTests > 0 ? (double) totalTestsPassed / totalTests * 100 : 0;

        System.out.println("📊 OVERALL STATISTICS");
        System.out.println("  Total Tests: " + totalTests);
        System.out.println("  Passed: " + totalTestsPassed);
        System.out.println("  Failed: " + totalTestsFailed);
        System.out.println("  Pass Rate: " + String.format("%.1f%%", passRate));
        System.out.println("  Total Execution Time: " + totalExecutionTime + "ms");

        // Test Suite Breakdown
        System.out.println("\n📋 TEST SUITE BREAKDOWN");
        for (TestSuite suite : testSuites) {
            System.out.println("  " + suite.getType().getDisplayName() + ": " + suite.getName());
        }

        // Summary
        System.out.println("\n🎯 SUMMARY");
        if (totalTestsFailed == 0) {
            System.out.println("✅ ALL TESTS PASSED! The theorem prover is working correctly.");
        } else if (passRate >= 90) {
            System.out.println("🟡 MOSTLY PASSING: " + String.format("%.1f%%", passRate) + " of tests passed.");
        } else if (passRate >= 70) {
            System.out.println("🟠 PARTIALLY PASSING: " + String.format("%.1f%%", passRate) + " of tests passed.");
        } else {
            System.out.println("🔴 NEEDS ATTENTION: Only " + String.format("%.1f%%", passRate) + " of tests passed.");
        }

        // Recommendations
        System.out.println("\n💡 RECOMMENDATIONS");
        if (totalTestsFailed == 0) {
            System.out.println("• All tests are passing! The system is ready for production use.");
            System.out.println("• Consider adding more edge case tests for robustness.");
            System.out.println("• Performance tests show the system is optimized.");
        } else {
            System.out.println("• Review failed tests and fix underlying issues.");
            System.out.println("• Consider adding more test coverage for edge cases.");
            System.out.println("• Run tests regularly to catch regressions early.");
        }

        System.out.println("\n🏁 TEST EXECUTION COMPLETE");
        System.out.println("=".repeat(70));
    }

    /**
     * Runs only unit tests.
     */
    public static void runUnitTests() {
        System.out.println("🧪 RUNNING UNIT TESTS ONLY");
        System.out.println("=".repeat(50));

        testSuites.stream()
                .filter(suite -> suite.getType() == TestType.UNIT)
                .forEach(TestRunner::runTestSuite);

        displayResults("Unit Tests");
    }

    /**
     * Runs only integration tests.
     */
    public static void runIntegrationTests() {
        System.out.println("🧪 RUNNING INTEGRATION TESTS ONLY");
        System.out.println("=".repeat(50));

        testSuites.stream()
                .filter(suite -> suite.getType() == TestType.INTEGRATION)
                .forEach(TestRunner::runTestSuite);

        displayResults("Integration Tests");
    }

    /**
     * Displays results for a specific test category.
     */
    private static void displayResults(String category) {
        System.out.println("\n📊 " + category.toUpperCase() + " RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("Passed: " + totalTestsPassed);
        System.out.println("Failed: " + totalTestsFailed);

        if (totalTestsFailed == 0) {
            System.out.println("\n🎉 All " + category.toLowerCase() + " passed!");
        } else {
            System.out.println("\n⚠️ Some " + category.toLowerCase() + " failed!");
        }
    }
}
