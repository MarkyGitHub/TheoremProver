package test;

import java.util.ArrayList;

/**
 * Simple integration tests for the theorem prover components.
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class SimpleIntegrationTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("🧪 RUNNING SIMPLE INTEGRATION TESTS");
        System.out.println("=".repeat(50));

        testScannerParserIntegration();
        testParserResolutionIntegration();
        testCompleteWorkflow();

        displayResults();
    }

    private static void testScannerParserIntegration() {
        System.out.println("Testing scanner-parser integration...");
        try {
            propositional.scanner.Scanner scanner = new propositional.scanner.Scanner("P => Q.");
            ArrayList tokens = scanner.getTokens();

            propositional.parser.Parser parser = new propositional.parser.Parser(tokens);
            propositional.common.Formula formula = parser.parse();

            assert formula != null : "Scanner and parser should work together";
            recordPass("Scanner-parser integration");

        } catch (Exception e) {
            recordFail("Scanner-parser integration", e.getMessage());
        }
    }

    private static void testParserResolutionIntegration() {
        System.out.println("Testing parser-resolution integration...");
        try {
            propositional.scanner.Scanner scanner = new propositional.scanner.Scanner("P => P.");
            ArrayList tokens = scanner.getTokens();

            propositional.parser.Parser parser = new propositional.parser.Parser(tokens);
            propositional.common.Formula formula = parser.parse();

            propositional.resolution.ResolutionMethod resolver = new propositional.resolution.ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result == true : "P => P should be a theorem";
            recordPass("Parser-resolution integration");

        } catch (Exception e) {
            recordFail("Parser-resolution integration", e.getMessage());
        }
    }

    private static void testCompleteWorkflow() {
        System.out.println("Testing complete workflow...");
        try {
            String formulaString = "(P => Q) & (Q => R) => (P => R).";

            propositional.scanner.Scanner scanner = new propositional.scanner.Scanner(formulaString);
            ArrayList tokens = scanner.getTokens();

            propositional.parser.Parser parser = new propositional.parser.Parser(tokens);
            propositional.common.Formula formula = parser.parse();

            if (formula != null) {
                propositional.resolution.ResolutionMethod resolver = new propositional.resolution.ResolutionMethod(formula);
                boolean result = resolver.resolve();

                assert result == true : "Transitivity should be a theorem";
                recordPass("Complete workflow");
            } else {
                recordFail("Complete workflow", "Formula parsing failed");
            }

        } catch (Exception e) {
            recordFail("Complete workflow", e.getMessage());
        }
    }

    private static void recordPass(String testName) {
        testsPassed++;
        System.out.println("  ✅ " + testName);
    }

    private static void recordFail(String testName, String error) {
        testsFailed++;
        System.out.println("  ❌ " + testName + " - " + error);
    }

    private static void displayResults() {
        System.out.println("\n📊 SIMPLE INTEGRATION TEST RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));

        if (testsFailed == 0) {
            System.out.println("\n🎉 All integration tests passed!");
        } else {
            System.out.println("\n⚠️ Some integration tests failed!");
        }
    }
}
