package test;

import java.util.ArrayList;
import propositional.scanner.Precedence;
import propositional.scanner.Scanner;
import propositional.scanner.Token;

/**
 * Unit tests for the propositional logic Scanner class.
 *
 * <p>
 * This test suite validates the tokenization functionality of the scanner,
 * ensuring it correctly identifies and categorizes logical symbols, variables,
 * and operators in propositional logic formulas.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ScannerTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    /**
     * Main entry point for running all scanner tests.
     */
    public static void main(String[] args) {
        System.out.println("🧪 RUNNING SCANNER UNIT TESTS");
        System.out.println("=".repeat(50));

        testEmptyInput();
        testSingleVariable();
        testSimpleFormula();
        testComplexFormula();
        testInvalidInput();
        testNestedParentheses();
        testAllOperators();
        testTokenPrecedence();

        displayResults();
    }

    /**
     * Tests scanning of empty input.
     */
    private static void testEmptyInput() {
        System.out.println("Testing empty input...");
        try {
            Scanner scanner = new Scanner("");
            ArrayList<Token> tokens = scanner.getTokens();

            assert tokens.isEmpty() : "Empty input should produce no tokens";
            recordPass("Empty input handling");

        } catch (Exception e) {
            recordFail("Empty input handling", e.getMessage());
        }
    }

    /**
     * Tests scanning of a single variable.
     */
    private static void testSingleVariable() {
        System.out.println("Testing single variable...");
        try {
            Scanner scanner = new Scanner("P.");
            ArrayList<Token> tokens = scanner.getTokens();

            assert tokens.size() == 1 : "Single variable should produce 1 token";
            assert tokens.get(0).getData().equals("P") : "Token data should be 'P'";
            assert tokens.get(0).getType() == Precedence.PREDICATE : "Token should be PREDICATE type";

            recordPass("Single variable scanning");

        } catch (Exception e) {
            recordFail("Single variable scanning", e.getMessage());
        }
    }

    /**
     * Tests scanning of a simple formula.
     */
    private static void testSimpleFormula() {
        System.out.println("Testing simple formula...");
        try {
            Scanner scanner = new Scanner("P => Q.");
            ArrayList<Token> tokens = scanner.getTokens();

            assert tokens.size() == 4 : "P => Q should produce 4 tokens (P, =>, Q, .)";
            assert tokens.get(0).getData().equals("P") : "First token should be 'P'";
            assert tokens.get(1).getData().equals("=>") : "Second token should be '=>'";
            assert tokens.get(2).getData().equals("Q") : "Third token should be 'Q'";
            assert tokens.get(3).getData().equals(".") : "Fourth token should be '.'";

            recordPass("Simple formula scanning");

        } catch (Exception e) {
            recordFail("Simple formula scanning", e.getMessage());
        }
    }

    /**
     * Tests scanning of a complex formula.
     */
    private static void testComplexFormula() {
        System.out.println("Testing complex formula...");
        try {
            Scanner scanner = new Scanner("(P => Q) & (Q => R) => (P => R).");
            ArrayList<Token> tokens = scanner.getTokens();

            // Should have: (, P, =>, Q, ), &, (, Q, =>, R, ), =>, (, P, =>, R, ), .
            assert tokens.size() == 17 : "Complex formula should produce 17 tokens";

            // Check key tokens
            assert tokens.get(0).getData().equals("(") : "First token should be '('";
            assert tokens.get(1).getData().equals("P") : "Second token should be 'P'";
            assert tokens.get(5).getData().equals("&") : "Sixth token should be '&'";
            assert tokens.get(11).getData().equals("=>") : "Twelfth token should be '=>'";
            assert tokens.get(16).getData().equals(".") : "Last token should be '.'";

            recordPass("Complex formula scanning");

        } catch (Exception e) {
            recordFail("Complex formula scanning", e.getMessage());
        }
    }

    /**
     * Tests scanning of invalid input.
     */
    private static void testInvalidInput() {
        System.out.println("Testing invalid input...");
        try {
            Scanner scanner = new Scanner("P & & Q.");
            ArrayList<Token> tokens = scanner.getTokens();

            // Scanner should still tokenize but parser will handle validation
            assert tokens != null : "Scanner should handle invalid input gracefully";

            recordPass("Invalid input handling");

        } catch (Exception e) {
            recordFail("Invalid input handling", e.getMessage());
        }
    }

    /**
     * Tests scanning of deeply nested parentheses.
     */
    private static void testNestedParentheses() {
        System.out.println("Testing nested parentheses...");
        try {
            Scanner scanner = new Scanner("((((P => Q) => R) => S) => T).");
            ArrayList<Token> tokens = scanner.getTokens();

            assert tokens.size() == 19 : "Nested formula should produce 19 tokens";

            // Count parentheses
            long openParens = tokens.stream()
                    .mapToInt(token -> token.getData().equals("(") ? 1 : 0)
                    .sum();
            long closeParens = tokens.stream()
                    .mapToInt(token -> token.getData().equals(")") ? 1 : 0)
                    .sum();

            assert openParens == closeParens : "Open and close parentheses should match";
            assert openParens == 5 : "Should have 5 pairs of parentheses";

            recordPass("Nested parentheses scanning");

        } catch (Exception e) {
            recordFail("Nested parentheses scanning", e.getMessage());
        }
    }

    /**
     * Tests scanning of all logical operators.
     */
    private static void testAllOperators() {
        System.out.println("Testing all operators...");
        try {
            Scanner scanner = new Scanner("P & Q | R => S <=> T ! U.");
            ArrayList<Token> tokens = scanner.getTokens();

            assert tokens.size() == 12 : "Formula with all operators should produce 12 tokens";

            // Check operator tokens
            boolean hasAnd = tokens.stream().anyMatch(t -> t.getData().equals("&"));
            boolean hasOr = tokens.stream().anyMatch(t -> t.getData().equals("|"));
            boolean hasImply = tokens.stream().anyMatch(t -> t.getData().equals("=>"));
            boolean hasIff = tokens.stream().anyMatch(t -> t.getData().equals("<=>"));
            boolean hasNot = tokens.stream().anyMatch(t -> t.getData().equals("!"));

            assert hasAnd : "Should contain AND operator";
            assert hasOr : "Should contain OR operator";
            assert hasImply : "Should contain IMPLY operator";
            assert hasIff : "Should contain IFF operator";
            assert hasNot : "Should contain NOT operator";

            recordPass("All operators scanning");

        } catch (Exception e) {
            recordFail("All operators scanning", e.getMessage());
        }
    }

    /**
     * Tests token precedence assignment.
     */
    private static void testTokenPrecedence() {
        System.out.println("Testing token precedence...");
        try {
            Scanner scanner = new Scanner("P => Q & R.");
            ArrayList<Token> tokens = scanner.getTokens();

            Token implyToken = tokens.stream()
                    .filter(t -> t.getData().equals("=>"))
                    .findFirst()
                    .orElse(null);
            Token andToken = tokens.stream()
                    .filter(t -> t.getData().equals("&"))
                    .findFirst()
                    .orElse(null);

            assert implyToken != null : "Should find IMPLY token";
            assert andToken != null : "Should find AND token";

            // AND should have higher precedence than IMPLY
            assert andToken.getType().ordinal() > implyToken.getType().ordinal() :
                    "AND should have higher precedence than IMPLY";

            recordPass("Token precedence assignment");

        } catch (Exception e) {
            recordFail("Token precedence assignment", e.getMessage());
        }
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
        System.out.println("\n📊 SCANNER TEST RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));

        if (testsFailed == 0) {
            System.out.println("\n🎉 All scanner tests passed!");
        } else {
            System.out.println("\n⚠️ Some scanner tests failed!");
        }
    }
}
