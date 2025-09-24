package demo;

import analysis.AlgorithmAnalyzer;
import common.ProofTree;
import java.util.ArrayList;
import java.util.Scanner;
import optimization.PerformanceOptimizer;
import propositional.common.Formula;
import propositional.parser.Parser;
import testing.AdvancedTestSuite;
import visualization.ProofVisualizer;

/**
 * Comprehensive demo of all advanced features.
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class AdvancedFeaturesDemo {

    public static void main(String[] args) {
        System.out.println("🚀 ADVANCED THEOREM PROVER FEATURES DEMO");
        System.out.println("=".repeat(70));

        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-6): ");
            String choice = inputScanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runAlgorithmAnalysis();
                    break;
                case "2":
                    runPerformanceOptimization();
                    break;
                case "3":
                    runAdvancedTesting();
                    break;
                case "4":
                    runProofVisualization();
                    break;
                case "5":
                    runCompleteDemo();
                    break;
                case "6":
                    System.out.println("👋 Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please enter 1-6.");
                    break;
            }

            System.out.println("\n" + "=".repeat(70));
            System.out.print("Press Enter to continue...");
            inputScanner.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n📋 ADVANCED FEATURES MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. 🔬 Algorithm Analysis");
        System.out.println("2. ⚡ Performance Optimization");
        System.out.println("3. 🧪 Advanced Testing Suite");
        System.out.println("4. 🎨 Proof Visualization");
        System.out.println("5. 🎯 Complete Demo (All Features)");
        System.out.println("6. 🚪 Exit");
        System.out.println("=".repeat(50));
    }

    private static void runAlgorithmAnalysis() {
        System.out.println("\n🔬 ALGORITHM ANALYSIS DEMO");
        System.out.println("=".repeat(50));

        try {
            // Create a sample formula for analysis
            String formulaString = "(P => Q) & (Q => R) => (P => R)";
            propositional.scanner.Scanner formulaScanner = new propositional.scanner.Scanner(formulaString + ".");
            ArrayList tokens = formulaScanner.getTokens();
            Parser parser = new Parser(tokens);
            Formula formula = parser.parse();

            if (formula != null) {
                AlgorithmAnalyzer.generateAnalysisReport(formula, null);
            } else {
                System.out.println("❌ Failed to parse formula for analysis");
            }

        } catch (Exception e) {
            System.err.println("❌ Error in algorithm analysis: " + e.getMessage());
        }
    }

    private static void runPerformanceOptimization() {
        System.out.println("\n⚡ PERFORMANCE OPTIMIZATION DEMO");
        System.out.println("=".repeat(50));

        try {
            // Test caching
            System.out.println("📦 Testing Formula Caching...");
            long startTime = System.currentTimeMillis();
            String formula = "(P => Q) & (Q => R)";

            // First call (cache miss)
            PerformanceOptimizer.convertToCNFOptimized(formula);
            long firstCall = System.currentTimeMillis();

            // Second call (cache hit)
            PerformanceOptimizer.convertToCNFOptimized(formula);
            long secondCall = System.currentTimeMillis();

            System.out.println("First call: " + (firstCall - startTime) + "ms");
            System.out.println("Second call (cached): " + (secondCall - firstCall) + "ms");

            // Test parallel processing
            System.out.println("\n🚀 Testing Parallel Processing...");
            String largeFormula = "P1 & P2 & P3 & P4 & P5";
            PerformanceOptimizer.convertToCNFOptimized(largeFormula);

            // Display performance report
            PerformanceOptimizer.displayPerformanceReport();

        } catch (Exception e) {
            System.err.println("❌ Error in performance optimization demo: " + e.getMessage());
        }
    }

    private static void runAdvancedTesting() {
        System.out.println("\n🧪 ADVANCED TESTING SUITE DEMO");
        System.out.println("=".repeat(50));

        try {
            System.out.println("Running comprehensive test suite...");
            AdvancedTestSuite.runCompleteTestSuite();

        } catch (Exception e) {
            System.err.println("❌ Error in advanced testing demo: " + e.getMessage());
        }
    }

    private static void runProofVisualization() {
        System.out.println("\n🎨 PROOF VISUALIZATION DEMO");
        System.out.println("=".repeat(50));

        try {
            // Create and display a sample proof
            ProofTree proofTree = ProofVisualizer.createSampleProofTree();

            // ASCII visualization
            ProofVisualizer.ASCIIProofTreeVisualizer.displayProofTree(proofTree);

            // Export capabilities
            System.out.println("\n📁 Testing Export Capabilities...");
            ProofVisualizer.ProofExporter.exportToText(proofTree, "sample_proof.txt");
            ProofVisualizer.ProofExporter.exportFormulaToFile("(P => Q) & (Q => R) => (P => R)", "sample_formula.txt");

            System.out.println("\n🔍 Interactive Proof Exploration:");
            System.out.println("(Demo mode - would normally start interactive session)");

        } catch (Exception e) {
            System.err.println("❌ Error in proof visualization demo: " + e.getMessage());
        }
    }

    private static void runCompleteDemo() {
        System.out.println("\n🎯 COMPLETE ADVANCED FEATURES DEMO");
        System.out.println("=".repeat(70));

        try {
            System.out.println("This demo showcases all advanced features:");
            System.out.println("✅ Algorithm Analysis");
            System.out.println("✅ Performance Optimization");
            System.out.println("✅ Advanced Testing");
            System.out.println("✅ Proof Visualization");
            System.out.println("✅ Export Capabilities");

            System.out.println("\n🚀 Running Algorithm Analysis...");
            runAlgorithmAnalysis();

            System.out.println("\n🚀 Running Performance Optimization...");
            runPerformanceOptimization();

            System.out.println("\n🚀 Running Advanced Testing...");
            runAdvancedTesting();

            System.out.println("\n🚀 Running Proof Visualization...");
            runProofVisualization();

            System.out.println("\n🎉 COMPLETE DEMO FINISHED!");
            System.out.println("=".repeat(70));
            System.out.println("All advanced features have been demonstrated:");
            System.out.println("• Deep algorithm understanding and analysis");
            System.out.println("• Performance optimization with caching and parallel processing");
            System.out.println("• Comprehensive testing with edge cases and benchmarks");
            System.out.println("• Advanced proof visualization and export capabilities");
            System.out.println("• Professional user interface and documentation");

        } catch (Exception e) {
            System.err.println("❌ Error in complete demo: " + e.getMessage());
        }
    }
}
