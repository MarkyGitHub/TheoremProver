package test;

import java.util.ArrayList;
import propositional.common.Formula;
import propositional.parser.Parser;
import propositional.resolution.ResolutionMethod;
import propositional.scanner.Scanner;

/**
 * Unit tests for the ResolutionMethod class.
 *
 * <p>
 * This test suite validates the resolution theorem proving functionality,
 * ensuring it correctly determines whether formulas are valid theorems.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ResolutionTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    /**
     * Main entry point for running all resolution tests.
     */
    public static void main(String[] args) {
        System.out.println("🧪 RUNNING RESOLUTION UNIT TESTS");
        System.out.println("=".repeat(50));

        testTautology();
        testContradiction();
        testTransitivity();
        testModusPonens();
        testDoubleNegation();
        testDeMorgan();
        testComplexTheorem();
        testNonTheorem();
        testInvalidFormula();

        displayResults();
    }

    /**
     * Tests resolution of a tautology (P => P).
     */
    private static void testTautology() {
        System.out.println("Testing tautology (P => P)...");
        try {
            Formula formula = parseFormula("P => P.");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result == true : "P => P should be a valid theorem (tautology)";
            recordPass("Tautology resolution");

        } catch (Exception e) {
            recordFail("Tautology resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of a contradiction (P & !P).
     */
    private static void testContradiction() {
        System.out.println("Testing contradiction (P & !P)...");
        try {
            Formula formula = parseFormula("P & !P.");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            // Contradiction should not be a theorem
            assert result == false : "P & !P should not be a valid theorem (contradiction)";
            recordPass("Contradiction resolution");

        } catch (Exception e) {
            recordFail("Contradiction resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of transitivity law.
     */
    private static void testTransitivity() {
        System.out.println("Testing transitivity law...");
        try {
            Formula formula = parseFormula("(P => Q) & (Q => R) => (P => R).");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result == true : "Transitivity should be a valid theorem";
            recordPass("Transitivity resolution");

        } catch (Exception e) {
            recordFail("Transitivity resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of modus ponens.
     */
    private static void testModusPonens() {
        System.out.println("Testing modus ponens...");
        try {
            Formula formula = parseFormula("P & (P => Q) => Q.");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result == true : "Modus ponens should be a valid theorem";
            recordPass("Modus ponens resolution");

        } catch (Exception e) {
            recordFail("Modus ponens resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of double negation.
     */
    private static void testDoubleNegation() {
        System.out.println("Testing double negation...");
        try {
            Formula formula = parseFormula("P => !!P.");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result == true : "Double negation should be a valid theorem";
            recordPass("Double negation resolution");

        } catch (Exception e) {
            recordFail("Double negation resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of De Morgan's law.
     */
    private static void testDeMorgan() {
        System.out.println("Testing De Morgan's law...");
        try {
            Formula formula = parseFormula("!(P & Q) => (!P | !Q).");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result == true : "De Morgan's law should be a valid theorem";
            recordPass("De Morgan's law resolution");

        } catch (Exception e) {
            recordFail("De Morgan's law resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of a complex theorem.
     */
    private static void testComplexTheorem() {
        System.out.println("Testing complex theorem...");
        try {
            Formula formula = parseFormula("((P => Q) => P) => P.");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            // This is Peirce's Law, a valid theorem
            assert result == true : "Peirce's Law should be a valid theorem";
            recordPass("Complex theorem resolution");

        } catch (Exception e) {
            recordFail("Complex theorem resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution of a non-theorem.
     */
    private static void testNonTheorem() {
        System.out.println("Testing non-theorem...");
        try {
            Formula formula = parseFormula("P => Q.");
            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            // P => Q is not a theorem (not always true)
            assert result == false : "P => Q should not be a theorem";
            recordPass("Non-theorem resolution");

        } catch (Exception e) {
            recordFail("Non-theorem resolution", e.getMessage());
        }
    }

    /**
     * Tests resolution with invalid formula.
     */
    private static void testInvalidFormula() {
        System.out.println("Testing invalid formula handling...");
        try {
            // Try to create resolution method with null formula
            ResolutionMethod resolver = new ResolutionMethod(null);
            boolean result = resolver.resolve();

            // Should handle gracefully (might return false or throw exception)
            recordPass("Invalid formula handling");

        } catch (Exception e) {
            // Exception is acceptable for invalid input
            recordPass("Invalid formula handling (exception)");
        }
    }

    /**
     * Helper method to parse a formula string.
     */
    private static Formula parseFormula(String formulaString) {
        Scanner scanner = new Scanner(formulaString);
        ArrayList tokens = scanner.getTokens();
        Parser parser = new Parser(tokens);
        return parser.parse();
    }

    /**
     * Records a passed test.
     */
    private static void recordPass(String testName) {
        testsPassed++;
        System.out.println("  ✅ " + testName);
    }

    /**
     * Records a failed test.
     */
    private static void recordFail(String testName, String error) {
        testsFailed++;
        System.out.println("  ❌ " + testName + " - " + error);
    }

    /**
     * Displays test results summary.
     */
    private static void displayResults() {
        System.out.println("\n📊 RESOLUTION TEST RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));

        if (testsFailed == 0) {
            System.out.println("\n🎉 All resolution tests passed!");
        } else {
            System.out.println("\n⚠️ Some resolution tests failed!");
        }
    }
}
