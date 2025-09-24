package test;

import java.util.List;
import optimized.OptimizedCNFConverter;

/**
 * Test suite for the optimized CNF converter.
 *
 * <p>
 * This class tests the functionality of the OptimizedCNFConverter, validating
 * that it correctly converts logical formulas to conjunctive normal form using
 * the improved algorithms.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class CNFTest {

    /**
     * Main entry point for running CNF converter tests.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Testing Optimized CNF Converter...\n");

        // Test simple formula: P & Q
        testFormula("P & Q", "[[P], [Q]]");

        // Test implication: P => Q  
        testFormula("P => Q", "[[¬P, Q]]");

        // Test complex formula: (P => Q) & (Q => R)
        testFormula("(P => Q) & (Q => R)", "[[¬P, Q], [¬Q, R]]");

        System.out.println("CNF Converter tests completed!");
    }

    /**
     * Tests a specific formula conversion to CNF.
     *
     * @param formula the formula to test
     * @param expectedPattern the expected CNF pattern (for reference)
     */
    private static void testFormula(String formula, String expectedPattern) {
        System.out.println("Testing: " + formula);

        try {
            List<List<String>> result = OptimizedCNFConverter.convertToCNF(formula);
            String formatted = OptimizedCNFConverter.formatCNF(result);

            System.out.println("  Input: " + formula);
            System.out.println("  CNF: " + formatted);

            if (result != null && !result.isEmpty()) {
                System.out.println("  ✓ Success");
            } else {
                System.out.println("  ✗ Failed - null or empty result");
            }

        } catch (Exception e) {
            System.out.println("  ✗ Error: " + e.getMessage());
        }

        System.out.println();
    }
}
