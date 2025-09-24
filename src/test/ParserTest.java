package test;

import java.util.ArrayList;
import propositional.common.Binary;
import propositional.common.Formula;
import propositional.common.Propositional;
import propositional.common.Unary;
import propositional.parser.Parser;
import propositional.scanner.Scanner;

/**
 * Unit tests for the propositional logic Parser class.
 *
 * <p>
 * This test suite validates the parsing functionality, ensuring it correctly
 * builds abstract syntax trees from token sequences and handles operator
 * precedence properly.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ParserTest {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    /**
     * Main entry point for running all parser tests.
     */
    public static void main(String[] args) {
        System.out.println("🧪 RUNNING PARSER UNIT TESTS");
        System.out.println("=".repeat(50));

        testEmptyInput();
        testSingleVariable();
        testSimpleImplication();
        testConjunction();
        testDisjunction();
        testNegation();
        testOperatorPrecedence();
        testNestedParentheses();
        testComplexFormula();
        testInvalidSyntax();

        displayResults();
    }

    /**
     * Tests parsing of empty input.
     */
    private static void testEmptyInput() {
        System.out.println("Testing empty input parsing...");
        try {
            Scanner scanner = new Scanner("");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula == null : "Empty input should produce null formula";
            recordPass("Empty input parsing");

        } catch (Exception e) {
            recordFail("Empty input parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of a single variable.
     */
    private static void testSingleVariable() {
        System.out.println("Testing single variable parsing...");
        try {
            Scanner scanner = new Scanner("P.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Single variable should produce a formula";
            assert formula instanceof Propositional : "Formula should be a Propositional";
            assert formula.toString().equals("P") : "Formula should represent 'P'";

            recordPass("Single variable parsing");

        } catch (Exception e) {
            recordFail("Single variable parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of simple implication.
     */
    private static void testSimpleImplication() {
        System.out.println("Testing simple implication parsing...");
        try {
            Scanner scanner = new Scanner("P => Q.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Implication should produce a formula";
            assert formula instanceof Binary : "Implication should be a Binary formula";

            Binary binary = (Binary) formula;
            assert binary.getToken().getData().equals("=>") : "Connective should be '=>'";
            assert binary.getleftPredicate() instanceof Propositional : "Left side should be Propositional";
            assert binary.getRightPredicate() instanceof Propositional : "Right side should be Propositional";

            recordPass("Simple implication parsing");

        } catch (Exception e) {
            recordFail("Simple implication parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of conjunction.
     */
    private static void testConjunction() {
        System.out.println("Testing conjunction parsing...");
        try {
            Scanner scanner = new Scanner("P & Q.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Conjunction should produce a formula";
            assert formula instanceof Binary : "Conjunction should be a Binary formula";

            Binary binary = (Binary) formula;
            assert binary.getToken().getData().equals("&") : "Connective should be '&'";

            recordPass("Conjunction parsing");

        } catch (Exception e) {
            recordFail("Conjunction parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of disjunction.
     */
    private static void testDisjunction() {
        System.out.println("Testing disjunction parsing...");
        try {
            Scanner scanner = new Scanner("P | Q.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Disjunction should produce a formula";
            assert formula instanceof Binary : "Disjunction should be a Binary formula";

            Binary binary = (Binary) formula;
            assert binary.getToken().getData().equals("|") : "Connective should be '|'";

            recordPass("Disjunction parsing");

        } catch (Exception e) {
            recordFail("Disjunction parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of negation.
     */
    private static void testNegation() {
        System.out.println("Testing negation parsing...");
        try {
            Scanner scanner = new Scanner("!P.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Negation should produce a formula";
            assert formula instanceof Unary : "Negation should be a Unary formula";

            Unary unary = (Unary) formula;
            assert unary.getToken().getData().equals("!") : "Connective should be '!'";
            assert unary.getFormula() instanceof Propositional : "Negated formula should be Propositional";

            recordPass("Negation parsing");

        } catch (Exception e) {
            recordFail("Negation parsing", e.getMessage());
        }
    }

    /**
     * Tests operator precedence handling.
     */
    private static void testOperatorPrecedence() {
        System.out.println("Testing operator precedence...");
        try {
            Scanner scanner = new Scanner("P => Q & R.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Formula with precedence should parse";
            assert formula instanceof Binary : "Should be a Binary formula";

            // The formula should be parsed as P => (Q & R) due to precedence
            Binary binary = (Binary) formula;
            assert binary.getToken().getData().equals("=>") : "Outer operator should be '=>'";

            recordPass("Operator precedence handling");

        } catch (Exception e) {
            recordFail("Operator precedence handling", e.getMessage());
        }
    }

    /**
     * Tests parsing of nested parentheses.
     */
    private static void testNestedParentheses() {
        System.out.println("Testing nested parentheses parsing...");
        try {
            Scanner scanner = new Scanner("(P => Q) & (Q => R).");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Nested parentheses should parse";
            assert formula instanceof Binary : "Should be a Binary formula";

            Binary binary = (Binary) formula;
            assert binary.getToken().getData().equals("&") : "Outer operator should be '&'";
            assert binary.getleftPredicate() instanceof Binary : "Left side should be Binary";
            assert binary.getRightPredicate() instanceof Binary : "Right side should be Binary";

            recordPass("Nested parentheses parsing");

        } catch (Exception e) {
            recordFail("Nested parentheses parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of complex formula.
     */
    private static void testComplexFormula() {
        System.out.println("Testing complex formula parsing...");
        try {
            Scanner scanner = new Scanner("((P => Q) & (Q => R)) => (P => R).");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            assert formula != null : "Complex formula should parse";
            assert formula instanceof Binary : "Should be a Binary formula";

            Binary binary = (Binary) formula;
            assert binary.getToken().getData().equals("=>") : "Outer operator should be '=>'";

            // Check that both sides are properly parsed
            assert binary.getleftPredicate() instanceof Binary : "Left side should be Binary";
            assert binary.getRightPredicate() instanceof Binary : "Right side should be Binary";

            recordPass("Complex formula parsing");

        } catch (Exception e) {
            recordFail("Complex formula parsing", e.getMessage());
        }
    }

    /**
     * Tests parsing of invalid syntax.
     */
    private static void testInvalidSyntax() {
        System.out.println("Testing invalid syntax handling...");
        try {
            Scanner scanner = new Scanner("P & & Q.");
            ArrayList tokens = scanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            // Parser should handle invalid syntax gracefully
            // It might return null or throw an exception
            assert formula == null : "Invalid syntax should result in null formula or exception";

            recordPass("Invalid syntax handling");

        } catch (Exception e) {
            // Exception is also acceptable for invalid syntax
            recordPass("Invalid syntax handling (exception)");
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
        System.out.println("\n📊 PARSER TEST RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));

        if (testsFailed == 0) {
            System.out.println("\n🎉 All parser tests passed!");
        } else {
            System.out.println("\n⚠️ Some parser tests failed!");
        }
    }
}
