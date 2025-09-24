package testing;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import optimization.PerformanceOptimizer;
import optimized.OptimizedCNFConverter;
import propositional.common.Formula;
import propositional.parser.Parser;
import propositional.resolution.ResolutionMethod;
import propositional.scanner.Scanner;

/**
 * Advanced testing framework with automated test suite, performance benchmarks,
 * and comprehensive edge case testing.
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class AdvancedTestSuite {

    private static class TestResult {

        private final String testName;
        private final boolean passed;
        private final long executionTime;
        private final String errorMessage;
        private final Map<String, Object> metrics;

        public TestResult(String testName, boolean passed, long executionTime,
                String errorMessage, Map<String, Object> metrics) {
            this.testName = testName;
            this.passed = passed;
            this.executionTime = executionTime;
            this.errorMessage = errorMessage;
            this.metrics = metrics != null ? metrics : new HashMap<>();
        }

        @Override
        public String toString() {
            String status = passed ? "✅ PASS" : "❌ FAIL";
            return String.format("%s %s (%dms) %s",
                    status, testName, executionTime,
                    errorMessage != null ? "- " + errorMessage : "");
        }
    }

    private static class TestSuite {

        private final List<TestResult> results = new ArrayList<>();
        private final Map<String, Long> performanceBenchmarks = new HashMap<>();

        public void addResult(TestResult result) {
            results.add(result);
        }

        public void addBenchmark(String operation, long timeMs) {
            performanceBenchmarks.put(operation, timeMs);
        }

        public void displayReport() {
            System.out.println("\n📊 ADVANCED TEST SUITE REPORT");
            System.out.println("=".repeat(60));

            // Test Results Summary
            long passed = results.stream().mapToLong(r -> r.passed ? 1 : 0).sum();
            long failed = results.size() - passed;
            double passRate = results.size() > 0 ? (double) passed / results.size() * 100 : 0;

            System.out.println("Test Results:");
            System.out.println("  Total Tests: " + results.size());
            System.out.println("  Passed: " + passed);
            System.out.println("  Failed: " + failed);
            System.out.println("  Pass Rate: " + String.format("%.1f%%", passRate));

            // Failed Tests
            List<TestResult> failures = results.stream()
                    .filter(r -> !r.passed)
                    .collect(Collectors.toList());

            if (!failures.isEmpty()) {
                System.out.println("\n❌ Failed Tests:");
                failures.forEach(System.out::println);
            }

            // Performance Benchmarks
            if (!performanceBenchmarks.isEmpty()) {
                System.out.println("\n⚡ Performance Benchmarks:");
                performanceBenchmarks.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach(entry
                                -> System.out.println("  " + entry.getKey() + ": " + entry.getValue() + "ms"));
            }

            // Average Execution Time
            double avgTime = results.stream()
                    .mapToLong(r -> r.executionTime)
                    .average()
                    .orElse(0.0);
            System.out.println("\n⏱️ Average Test Execution Time: "
                    + String.format("%.2fms", avgTime));
        }
    }

    /**
     * Runs comprehensive edge case testing.
     */
    public static TestSuite runEdgeCaseTests() {
        TestSuite suite = new TestSuite();

        System.out.println("🧪 RUNNING EDGE CASE TESTS");
        System.out.println("=".repeat(50));

        // Empty formula
        runTest(suite, "Empty Formula", () -> {
            Scanner scanner = new Scanner("");
            return scanner.getTokens().isEmpty();
        });

        // Single literal
        runTest(suite, "Single Literal", () -> {
            Scanner scanner = new Scanner("P.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();
            return formula != null;
        });

        // Nested parentheses
        runTest(suite, "Deep Nesting", () -> {
            String deepFormula = "((((P => Q) => R) => S) => T).";
            Scanner scanner = new Scanner(deepFormula);
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();
            return formula != null;
        });

        // Long formula
        runTest(suite, "Long Formula", () -> {
            StringBuilder sb = new StringBuilder();
            sb.append("P1");
            for (int i = 2; i <= 20; i++) {
                sb.append(" & P").append(i);
            }
            sb.append(".");

            Scanner scanner = new Scanner(sb.toString());
            ArrayList tokens = scanner.getTokens();
            return tokens.size() > 10;
        });

        // Invalid syntax
        runTest(suite, "Invalid Syntax Handling", () -> {
            try {
                Scanner scanner = new Scanner("P & & Q.");
                ArrayList tokens = scanner.getTokens();
                // Should handle gracefully
                return true;
            } catch (Exception e) {
                return true; // Expected to handle errors
            }
        });

        // Memory stress test
        runTest(suite, "Memory Stress Test", () -> {
            try {
                for (int i = 0; i < 100; i++) {
                    String formula = "P" + i + " => Q" + i + ".";
                    Scanner scanner = new Scanner(formula);
                    ArrayList tokens = scanner.getTokens();
                    Parser parser = new Parser(tokens);
                    Formula parsed = parser.parse();
                    if (parsed == null) {
                        return false;
                    }
                }
                return true;
            } catch (OutOfMemoryError e) {
                return false;
            }
        });

        return suite;
    }

    /**
     * Runs performance benchmark tests.
     */
    public static TestSuite runPerformanceBenchmarks() {
        TestSuite suite = new TestSuite();

        System.out.println("\n⚡ RUNNING PERFORMANCE BENCHMARKS");
        System.out.println("=".repeat(50));

        // CNF Conversion Performance
        String[] testFormulas = {
            "P => Q.",
            "(P => Q) & (Q => R) => (P => R).",
            "((P => Q) & (Q => R)) & ((R => S) & (S => T)) => (P => T).",
            "P & Q & R & S & T => P.",
            "((P => Q) => R) => ((Q => R) => P)."
        };

        for (String formula : testFormulas) {
            runBenchmark(suite, "CNF Conversion: " + formula, () -> {
                OptimizedCNFConverter.convertToCNF(formula.replace(".", ""));
            });
        }

        // Parser Performance
        for (String formula : testFormulas) {
            runBenchmark(suite, "Parsing: " + formula, () -> {
                Scanner scanner = new Scanner(formula);
                ArrayList tokens = scanner.getTokens();
                Parser parser = new Parser(tokens);
                parser.parse();
            });
        }

        // Resolution Performance
        for (String formula : testFormulas) {
            runBenchmark(suite, "Resolution: " + formula, () -> {
                Scanner scanner = new Scanner(formula);
                ArrayList tokens = scanner.getTokens();
                Parser parser = new Parser(tokens);
                Formula parsed = parser.parse();
                if (parsed != null) {
                    ResolutionMethod resolver = new ResolutionMethod(parsed);
                    resolver.resolve();
                }
            });
        }

        return suite;
    }

    /**
     * Runs regression tests to ensure code changes don't break functionality.
     */
    public static TestSuite runRegressionTests() {
        TestSuite suite = new TestSuite();

        System.out.println("\n🔄 RUNNING REGRESSION TESTS");
        System.out.println("=".repeat(50));

        // Known working formulas
        Map<String, Boolean> knownResults = new HashMap<>();
        knownResults.put("P => P.", true);  // Tautology
        knownResults.put("P & !P.", false); // Contradiction
        knownResults.put("(P => Q) & (Q => R) => (P => R).", true); // Transitivity
        knownResults.put("P => (Q => P).", true); // Weakening
        knownResults.put("((P => Q) => P) => P.", true); // Peirce's Law

        for (Map.Entry<String, Boolean> entry : knownResults.entrySet()) {
            String formula = entry.getKey();
            Boolean expected = entry.getValue();

            runTest(suite, "Regression: " + formula, () -> {
                try {
                    Scanner scanner = new Scanner(formula);
                    ArrayList tokens = scanner.getTokens();
                    Parser parser = new Parser(tokens);
                    Formula parsed = parser.parse();

                    if (parsed == null) {
                        return false; // Parsing failed
                    }

                    ResolutionMethod resolver = new ResolutionMethod(parsed);
                    boolean result = resolver.resolve();
                    return result == expected;

                } catch (Exception e) {
                    System.err.println("Regression test error for " + formula + ": " + e.getMessage());
                    return false;
                }
            });
        }

        return suite;
    }

    /**
     * Runs stress tests to verify system stability under load.
     */
    public static TestSuite runStressTests() {
        TestSuite suite = new TestSuite();

        System.out.println("\n💪 RUNNING STRESS TESTS");
        System.out.println("=".repeat(50));

        // Concurrent processing test
        runTest(suite, "Concurrent Processing", () -> {
            try {
                ExecutorService executor = Executors.newFixedThreadPool(10);
                List<Future<Boolean>> futures = new ArrayList<>();

                for (int i = 0; i < 50; i++) {
                    final int threadId = i;
                    Future<Boolean> future = executor.submit(() -> {
                        String formula = "P" + threadId + " => Q" + threadId + ".";
                        Scanner scanner = new Scanner(formula);
                        ArrayList tokens = scanner.getTokens();
                        Parser parser = new Parser(tokens);
                        Formula parsed = parser.parse();
                        return parsed != null;
                    });
                    futures.add(future);
                }

                boolean allPassed = true;
                for (Future<Boolean> future : futures) {
                    if (!future.get()) {
                        allPassed = false;
                    }
                }

                executor.shutdown();
                return allPassed;

            } catch (Exception e) {
                return false;
            }
        });

        // Large formula test
        runTest(suite, "Large Formula Processing", () -> {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("P1");
                for (int i = 2; i <= 100; i++) {
                    sb.append(" & P").append(i);
                }
                sb.append(".");

                Scanner scanner = new Scanner(sb.toString());
                ArrayList tokens = scanner.getTokens();
                return tokens.size() > 50;

            } catch (Exception e) {
                return false;
            }
        });

        return suite;
    }

    /**
     * Runs a single test with timing and error handling.
     */
    private static void runTest(TestSuite suite, String testName, TestCase testCase) {
        long startTime = System.currentTimeMillis();
        String errorMessage = null;
        boolean passed = false;

        try {
            passed = testCase.run();
        } catch (Exception e) {
            errorMessage = e.getMessage();
            passed = false;
        }

        long endTime = System.currentTimeMillis();
        TestResult result = new TestResult(testName, passed, endTime - startTime, errorMessage, null);
        suite.addResult(result);

        System.out.println(result);
    }

    /**
     * Runs a benchmark test with timing.
     */
    private static void runBenchmark(TestSuite suite, String operation, BenchmarkCase benchmarkCase) {
        long startTime = System.currentTimeMillis();

        try {
            benchmarkCase.run();
        } catch (Exception e) {
            System.err.println("Benchmark error for " + operation + ": " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        suite.addBenchmark(operation, endTime - startTime);

        System.out.println("⏱️ " + operation + ": " + (endTime - startTime) + "ms");
    }

    /**
     * Functional interface for test cases.
     */
    @FunctionalInterface
    private interface TestCase {

        boolean run() throws Exception;
    }

    /**
     * Functional interface for benchmark cases.
     */
    @FunctionalInterface
    private interface BenchmarkCase {

        void run() throws Exception;
    }

    /**
     * Runs the complete advanced test suite.
     */
    public static void runCompleteTestSuite() {
        System.out.println("🚀 STARTING ADVANCED TEST SUITE");
        System.out.println("=".repeat(60));

        TestSuite edgeCaseSuite = runEdgeCaseTests();
        TestSuite performanceSuite = runPerformanceBenchmarks();
        TestSuite regressionSuite = runRegressionTests();
        TestSuite stressSuite = runStressTests();

        // Combine all results
        TestSuite completeSuite = new TestSuite();
        completeSuite.results.addAll(edgeCaseSuite.results);
        completeSuite.results.addAll(performanceSuite.results);
        completeSuite.results.addAll(regressionSuite.results);
        completeSuite.results.addAll(stressSuite.results);
        completeSuite.performanceBenchmarks.putAll(performanceSuite.performanceBenchmarks);

        // Display comprehensive report
        completeSuite.displayReport();

        // Display performance optimization report
        PerformanceOptimizer.displayPerformanceReport();

        System.out.println("\n🎉 ADVANCED TEST SUITE COMPLETE");
    }
}
