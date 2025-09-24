package visualization;

import common.ProofStep;
import common.ProofTree;
import java.io.*;
import java.util.*;

/**
 * Advanced proof visualization system for the theorem prover.
 *
 * <p>
 * This class provides graphical proof tree display, interactive proof
 * exploration, and export capabilities for proofs and formulas.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ProofVisualizer {

    /**
     * Interactive proof explorer for step-by-step proof analysis.
     */
    public static class InteractiveProofExplorer {

        private final ProofTree proofTree;
        private int currentStep = 0;
        private final Scanner scanner;

        public InteractiveProofExplorer(ProofTree proofTree) {
            this.proofTree = proofTree;
            this.scanner = new Scanner(System.in);
        }

        public void startExploration() {
            System.out.println("\n🔍 INTERACTIVE PROOF EXPLORATION");
            System.out.println("=".repeat(60));
            System.out.println("Commands: next, prev, goto <step>, show, help, exit");

            boolean running = true;
            while (running) {
                System.out.print("\nProof Explorer> ");
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "next":
                        nextStep();
                        break;
                    case "prev":
                        previousStep();
                        break;
                    case "show":
                        showCurrentStep();
                        break;
                    case "help":
                        showHelp();
                        break;
                    case "exit":
                        running = false;
                        break;
                    default:
                        if (command.startsWith("goto ")) {
                            try {
                                int step = Integer.parseInt(command.substring(5));
                                gotoStep(step);
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Invalid step number");
                            }
                        } else {
                            System.out.println("❌ Unknown command. Type 'help' for available commands.");
                        }
                        break;
                }
            }
        }

        private void nextStep() {
            if (currentStep < proofTree.getStepCount() - 1) {
                currentStep++;
                showCurrentStep();
            } else {
                System.out.println("📋 Already at the last step");
            }
        }

        private void previousStep() {
            if (currentStep > 0) {
                currentStep--;
                showCurrentStep();
            } else {
                System.out.println("📋 Already at the first step");
            }
        }

        private void gotoStep(int step) {
            if (step >= 0 && step < proofTree.getStepCount()) {
                currentStep = step;
                showCurrentStep();
            } else {
                System.out.println("❌ Step " + step + " does not exist (0-" + (proofTree.getStepCount() - 1) + ")");
            }
        }

        private void showCurrentStep() {
            if (proofTree.getStepCount() == 0) {
                System.out.println("📋 No proof steps available");
                return;
            }

            ProofStep step = proofTree.getSteps().get(currentStep);
            System.out.println("\n📋 Step " + (currentStep + 1) + " of " + proofTree.getStepCount());
            System.out.println("-".repeat(40));
            System.out.println("Type: " + step.getType().getDescription());
            System.out.println("Formula: " + step.getFormula());
            if (step.getRule() != null && !step.getRule().isEmpty()) {
                System.out.println("Rule: " + step.getRule());
            }
            if (!step.getDependencies().isEmpty()) {
                System.out.println("Dependencies: " + step.getDependencies());
            }
            if (step.getExplanation() != null && !step.getExplanation().isEmpty()) {
                System.out.println("Explanation: " + step.getExplanation());
            }
        }

        private void showHelp() {
            System.out.println("\n📖 Interactive Proof Explorer Help:");
            System.out.println("  next    - Go to next step");
            System.out.println("  prev    - Go to previous step");
            System.out.println("  goto N  - Go to step N");
            System.out.println("  show    - Show current step details");
            System.out.println("  help    - Show this help");
            System.out.println("  exit    - Exit exploration");
        }
    }

    /**
     * ASCII art proof tree visualizer.
     */
    public static class ASCIIProofTreeVisualizer {

        public static void displayProofTree(ProofTree proofTree) {
            System.out.println("\n🌳 PROOF TREE VISUALIZATION");
            System.out.println("=".repeat(60));

            if (proofTree.getStepCount() == 0) {
                System.out.println("📋 No proof steps to visualize");
                return;
            }

            System.out.println("Original Formula: " + proofTree.getOriginalFormula());
            System.out.println("Proof Steps: " + proofTree.getStepCount());
            System.out.println("Status: " + (proofTree.isValid() ? "✅ Valid" : "❌ Invalid"));
            System.out.println();

            // Display proof steps in tree format
            for (int i = 0; i < proofTree.getStepCount(); i++) {
                ProofStep step = proofTree.getSteps().get(i);
                displayProofStep(step, i);
            }

            System.out.println("\n" + "=".repeat(60));
            System.out.println("Legend:");
            System.out.println("├─ Assumption");
            System.out.println("├─ Negation");
            System.out.println("├─ CNF Conversion");
            System.out.println("├─ Resolution");
            System.out.println("├─ Contradiction");
            System.out.println("└─ Conclusion");
        }

        private static void displayProofStep(ProofStep step, int index) {
            String prefix = "├─ ";
            if (index == 0) {
                prefix = "┌─ ";
            } else if (index == 9) { // Last step
                prefix = "└─ ";
            }

            System.out.println(prefix + String.format("%2d. %-15s %s",
                    step.getStepNumber(),
                    step.getType().getDescription(),
                    truncate(step.getFormula(), 40)));

            // Show dependencies if any
            if (!step.getDependencies().isEmpty()) {
                System.out.println("│   └─ Depends on: " + step.getDependencies());
            }

            // Show rule if present
            if (step.getRule() != null && !step.getRule().isEmpty()) {
                System.out.println("│   └─ Rule: " + step.getRule());
            }
        }

        private static String truncate(String text, int maxLength) {
            if (text.length() <= maxLength) {
                return text;
            }
            return text.substring(0, maxLength - 3) + "...";
        }
    }

    /**
     * Export system for saving proofs and formulas.
     */
    public static class ProofExporter {

        public static void exportToText(ProofTree proofTree, String filename) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("THEOREM PROVER - PROOF EXPORT");
                writer.println("=".repeat(50));
                writer.println("Original Formula: " + proofTree.getOriginalFormula());
                writer.println("Export Date: " + new Date());
                writer.println("Proof Steps: " + proofTree.getStepCount());
                writer.println("Status: " + (proofTree.isValid() ? "Valid" : "Invalid"));
                writer.println();

                for (ProofStep step : proofTree.getSteps()) {
                    writer.println(step.format());
                }

                writer.println();
                writer.println("End of Proof");

                System.out.println("✅ Proof exported to " + filename);

            } catch (IOException e) {
                System.err.println("❌ Error exporting proof: " + e.getMessage());
            }
        }

        public static void exportToLaTeX(ProofTree proofTree, String filename) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("\\documentclass{article}");
                writer.println("\\usepackage{proof}");
                writer.println("\\begin{document}");
                writer.println();
                writer.println("\\title{Theorem Prover Proof}");
                writer.println("\\author{Mark Schlichtmann}");
                writer.println("\\date{" + new Date() + "}");
                writer.println("\\maketitle");
                writer.println();
                writer.println("\\section{Original Formula}");
                writer.println("\\[" + escapeLaTeX(proofTree.getOriginalFormula()) + "\\]");
                writer.println();
                writer.println("\\section{Proof}");
                writer.println("\\begin{prooftree}");

                for (ProofStep step : proofTree.getSteps()) {
                    writer.println("  \\AxiomC{" + escapeLaTeX(step.getFormula()) + "}");
                }

                writer.println("\\end{prooftree}");
                writer.println();
                writer.println("\\section{Conclusion}");
                writer.println("The formula is " + (proofTree.isValid() ? "valid" : "invalid") + ".");
                writer.println();
                writer.println("\\end{document}");

                System.out.println("✅ LaTeX proof exported to " + filename);

            } catch (IOException e) {
                System.err.println("❌ Error exporting LaTeX proof: " + e.getMessage());
            }
        }

        public static void exportFormulaToFile(String formula, String filename) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println("THEOREM PROVER - FORMULA EXPORT");
                writer.println("=".repeat(50));
                writer.println("Formula: " + formula);
                writer.println("Export Date: " + new Date());
                writer.println();
                writer.println("Usage: Copy this formula into the theorem prover for analysis.");

                System.out.println("✅ Formula exported to " + filename);

            } catch (IOException e) {
                System.err.println("❌ Error exporting formula: " + e.getMessage());
            }
        }

        private static String escapeLaTeX(String text) {
            return text.replace("&", "\\&")
                    .replace("%", "\\%")
                    .replace("$", "\\$")
                    .replace("#", "\\#")
                    .replace("^", "\\textasciicircum{}")
                    .replace("_", "\\_")
                    .replace("{", "\\{")
                    .replace("}", "\\}")
                    .replace("~", "\\textasciitilde{}");
        }
    }

    /**
     * Creates a sample proof tree for demonstration.
     */
    public static ProofTree createSampleProofTree() {
        ProofTree proofTree = new ProofTree("(P => Q) & (Q => R) => (P => R)");

        proofTree.addStep(ProofStep.StepType.ASSUMPTION, "(P => Q) & (Q => R)", null,
                "Assume the antecedent");
        proofTree.addStep(ProofStep.StepType.CNF_CONVERSION, "¬((P => Q) & (Q => R)) ∨ (P => R)", "Implication",
                "Convert to CNF");
        proofTree.addStep(ProofStep.StepType.NEGATION, "¬(P => Q) ∨ ¬(Q => R) ∨ (P => R)", "De Morgan",
                "Apply De Morgan's law");
        proofTree.addStep(ProofStep.StepType.RESOLUTION, "P ∨ ¬Q ∨ ¬Q ∨ R ∨ ¬P ∨ R", "Resolution",
                "Apply resolution rule");
        proofTree.addStep(ProofStep.StepType.RESOLUTION, "¬Q ∨ ¬Q ∨ R ∨ R", "Resolution",
                "Resolve P and ¬P");
        proofTree.addStep(ProofStep.StepType.CONTRADICTION, "⊥", "Contradiction",
                "Empty clause derived");
        proofTree.addStep(ProofStep.StepType.CONCLUSION, "Formula is valid", "QED",
                "Proof complete");

        proofTree.markValid("The formula is a valid theorem");
        return proofTree;
    }

    /**
     * Main visualization demo.
     */
    public static void runVisualizationDemo() {
        System.out.println("🎨 PROOF VISUALIZATION DEMO");
        System.out.println("=".repeat(60));

        // Create sample proof
        ProofTree proofTree = createSampleProofTree();

        // ASCII visualization
        ASCIIProofTreeVisualizer.displayProofTree(proofTree);

        // Interactive exploration
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Starting interactive proof exploration...");
        InteractiveProofExplorer explorer = new InteractiveProofExplorer(proofTree);
        explorer.startExploration();

        // Export capabilities
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Exporting proof...");
        ProofExporter.exportToText(proofTree, "proof_export.txt");
        ProofExporter.exportToLaTeX(proofTree, "proof_export.tex");
        ProofExporter.exportFormulaToFile("(P => Q) & (Q => R) => (P => R)", "formula_export.txt");

        System.out.println("\n🎉 Visualization demo complete!");
    }
}
