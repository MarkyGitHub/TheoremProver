package test;

import common.TokenType;
import common.UnifiedToken;
import java.util.ArrayList;
import propositional.common.Binary;
import propositional.common.Propositional;
import propositional.parser.Parser;
import propositional.resolution.ResolutionMethod;
import propositional.scanner.Scanner;

/**
 * Unit tests for propositional logic components.
 *
 * <p>
 * This test suite validates the core functionality of the theorem prover,
 * including token creation, scanning, parsing, and resolution. It provides
 * comprehensive coverage of the propositional logic implementation.</p>
 *
 * <p>
 * Test Coverage:
 * <ul>
 * <li>Token creation and type checking</li>
 * <li>Scanner functionality and tokenization</li>
 * <li>Parser correctness for various formulas</li>
 * <li>Resolution theorem proving</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class PropositionalLogicTest {

    /**
     * Counter for passed tests
     */
    private static int testsPassed = 0;
    /**
     * Counter for failed tests
     */
    private static int testsFailed = 0;

    /**
     * Main entry point for running all propositional logic tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Running Propositional Logic Tests...\n");

        testTokenCreation();
        testScanner();
        testParser();
        testResolution();

        System.out.println("\n=== Test Results ===");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));

        if (testsFailed == 0) {
            System.out.println("All tests passed! ✅");
        } else {
            System.out.println("Some tests failed! ❌");
        }
    }

    /**
     * Tests token creation and type checking functionality.
     */
    private static void testTokenCreation() {
        System.out.println("Testing Token Creation...");

        // Test predicate token
        UnifiedToken p = new UnifiedToken("P", TokenType.PREDICATE);
        assert p.isPredicate() : "P should be a predicate";
        assert !p.isVariable() : "P should not be a variable";

        // Test connective tokens
        UnifiedToken not = new UnifiedToken("!", TokenType.NOT);
        assert not.isUnary() : "! should be unary";
        assert not.isConnective() : "! should be a connective";

        UnifiedToken and = new UnifiedToken("&", TokenType.AND);
        assert and.isBinary() : "& should be binary";
        assert and.getPrecedence() == 10 : "& should have precedence 10";

        passTest("Token Creation");
    }

    /**
     * Tests scanner functionality and tokenization.
     */
    private static void testScanner() {
        System.out.println("Testing Scanner...");

        try {
            Scanner scanner = new Scanner("P & Q.");
            ArrayList tokens = scanner.getTokens();
            assert tokens != null : "Scanner should return tokens";
            assert tokens.size() > 0 : "Scanner should return non-empty token list";

            passTest("Scanner");
        } catch (Exception e) {
            failTest("Scanner", e.getMessage());
        }
    }

    /**
     * Tests parser correctness for various formula types.
     */
    private static void testParser() {
        System.out.println("Testing Parser...");

        try {
            // Test simple formula
            Scanner scanner = new Scanner("P.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            propositional.common.Formula formula = parser.parse();

            assert formula != null : "Parser should return a formula";
            assert formula instanceof Propositional : "Simple formula should be Propositional";

            // Test complex formula
            Scanner scanner2 = new Scanner("(P => Q).");
            ArrayList tokens2 = scanner2.getTokens();
            Parser parser2 = new Parser(tokens2);
            propositional.common.Formula formula2 = parser2.parse();

            assert formula2 != null : "Complex formula should parse";
            assert formula2 instanceof Binary : "P => Q should be Binary";

            passTest("Parser");
        } catch (Exception e) {
            failTest("Parser", e.getMessage());
        }
    }

    /**
     * Tests resolution theorem proving functionality.
     */
    private static void testResolution() {
        System.out.println("Testing Resolution...");

        try {
            // Test tautology: P => P
            Scanner scanner = new Scanner("(P => P).");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            propositional.common.Formula formula = parser.parse();

            ResolutionMethod resolver = new ResolutionMethod(formula);
            boolean result = resolver.resolve();

            assert result : "P => P should be a theorem (tautology)";

            // Test contradiction: P & !P
            Scanner scanner2 = new Scanner("(P & !P).");
            ArrayList tokens2 = scanner2.getTokens();
            Parser parser2 = new Parser(tokens2);
            propositional.common.Formula formula2 = parser2.parse();

            ResolutionMethod resolver2 = new ResolutionMethod(formula2);
            boolean result2 = resolver2.resolve();

            assert !result2 : "P & !P should not be a theorem";

            passTest("Resolution");
        } catch (Exception e) {
            failTest("Resolution", e.getMessage());
        }
    }

    /**
     * Records a passed test and displays success message.
     *
     * @param testName the name of the test that passed
     */
    private static void passTest(String testName) {
        testsPassed++;
        System.out.println("  ✓ " + testName);
    }

    /**
     * Records a failed test and displays error message.
     *
     * @param testName the name of the test that failed
     * @param error the error message
     */
    private static void failTest(String testName, String error) {
        testsFailed++;
        System.out.println("  ✗ " + testName + ": " + error);
    }
}
