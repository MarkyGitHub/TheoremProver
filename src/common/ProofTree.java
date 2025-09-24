package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete proof tree for a theorem. This class manages the proof
 * steps and generates human-readable proof output.
 */
public class ProofTree {

    private final String originalFormula;
    private final List<ProofStep> steps;
    private boolean isValid;
    private String conclusion;

    public ProofTree(String originalFormula) {
        this.originalFormula = originalFormula;
        this.steps = new ArrayList<>();
        this.isValid = false;
        this.conclusion = "";
    }

    /**
     * Add a step to the proof
     */
    public void addStep(ProofStep step) {
        steps.add(step);
    }

    /**
     * Add a step with automatic step numbering
     */
    public void addStep(ProofStep.StepType type, String formula, String rule, String explanation) {
        addStep(new ProofStep(steps.size() + 1, type, formula, rule, explanation));
    }

    /**
     * Add a step with dependencies
     */
    public void addStep(ProofStep.StepType type, String formula, String rule,
            List<Integer> dependencies, String explanation) {
        addStep(new ProofStep(steps.size() + 1, type, formula, rule, dependencies, explanation));
    }

    /**
     * Mark the proof as valid with a conclusion
     */
    public void markValid(String conclusion) {
        this.isValid = true;
        this.conclusion = conclusion;
    }

    /**
     * Mark the proof as invalid
     */
    public void markInvalid() {
        this.isValid = false;
        this.conclusion = "Formula is not a theorem";
    }

    /**
     * Generate a formatted proof output
     */
    public String generateProof() {
        StringBuilder sb = new StringBuilder();

        sb.append("=".repeat(60)).append("\n");
        sb.append("THEOREM PROVER - PROOF GENERATION\n");
        sb.append("=".repeat(60)).append("\n");
        sb.append("Original Formula: ").append(originalFormula).append("\n\n");

        sb.append("PROOF STEPS:\n");
        sb.append("-".repeat(60)).append("\n");

        for (ProofStep step : steps) {
            sb.append(step.format()).append("\n");
        }

        sb.append("-".repeat(60)).append("\n");

        if (isValid) {
            sb.append("CONCLUSION: ").append(conclusion).append("\n");
            sb.append("STATUS: ✅ THEOREM PROVEN\n");
        } else {
            sb.append("CONCLUSION: ").append(conclusion).append("\n");
            sb.append("STATUS: ❌ NOT A THEOREM\n");
        }

        sb.append("=".repeat(60)).append("\n");

        return sb.toString();
    }

    /**
     * Generate a simplified proof summary
     */
    public String generateSummary() {
        StringBuilder sb = new StringBuilder();

        sb.append("Formula: ").append(originalFormula).append("\n");
        sb.append("Steps: ").append(steps.size()).append("\n");
        sb.append("Result: ");

        if (isValid) {
            sb.append("✅ ").append(conclusion);
        } else {
            sb.append("❌ ").append(conclusion);
        }

        return sb.toString();
    }

    // Getters
    public String getOriginalFormula() {
        return originalFormula;
    }

    public List<ProofStep> getSteps() {
        return new ArrayList<>(steps);
    }

    public boolean isValid() {
        return isValid;
    }

    public String getConclusion() {
        return conclusion;
    }

    public int getStepCount() {
        return steps.size();
    }

    @Override
    public String toString() {
        return generateSummary();
    }
}
