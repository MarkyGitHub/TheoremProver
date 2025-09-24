package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single step in a proof tree.
 *
 * <p>
 * This class enables the theorem prover to generate detailed proof trees
 * instead of just yes/no answers. Each proof step contains information about
 * the formula, the rule applied, dependencies on other steps, and explanatory
 * text.</p>
 *
 * <p>
 * Features:
 * <ul>
 * <li>Step type classification (Assumption, Negation, CNF Conversion,
 * etc.)</li>
 * <li>Dependency tracking between proof steps</li>
 * <li>Formatted output for human-readable proofs</li>
 * <li>Rule and explanation documentation</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ProofStep {

    /**
     * Enumeration of proof step types with their descriptions.
     */
    public enum StepType {
        /**
         * Initial assumption or given formula
         */
        ASSUMPTION("Assumption"),
        /**
         * Negation of a formula
         */
        NEGATION("Negation"),
        /**
         * Conversion to conjunctive normal form
         */
        CNF_CONVERSION("CNF Conversion"),
        /**
         * Resolution rule application
         */
        RESOLUTION("Resolution"),
        /**
         * Contradiction found
         */
        CONTRADICTION("Contradiction"),
        /**
         * Final conclusion
         */
        CONCLUSION("Conclusion");

        /**
         * Human-readable description of this step type
         */
        private final String description;

        /**
         * Constructs a step type with the specified description.
         *
         * @param description the description of this step type
         */
        StepType(String description) {
            this.description = description;
        }

        /**
         * Returns the description of this step type.
         *
         * @return the step type description
         */
        public String getDescription() {
            return description;
        }
    }

    /**
     * The step number in the proof sequence
     */
    private final int stepNumber;
    /**
     * The type of this proof step
     */
    private final StepType type;
    /**
     * The formula at this step
     */
    private final String formula;
    /**
     * The rule applied at this step
     */
    private final String rule;
    /**
     * List of step numbers this step depends on
     */
    private final List<Integer> dependencies;
    /**
     * Additional explanation for this step
     */
    private final String explanation;

    /**
     * Constructs a proof step with all parameters.
     *
     * @param stepNumber the step number in the proof sequence
     * @param type the type of this proof step
     * @param formula the formula at this step
     * @param rule the rule applied at this step
     * @param dependencies list of step numbers this step depends on
     * @param explanation additional explanation for this step
     */
    public ProofStep(int stepNumber, StepType type, String formula, String rule,
            List<Integer> dependencies, String explanation) {
        this.stepNumber = stepNumber;
        this.type = type;
        this.formula = formula;
        this.rule = rule;
        this.dependencies = dependencies != null ? new ArrayList<>(dependencies) : new ArrayList<>();
        this.explanation = explanation;
    }

    /**
     * Constructs a proof step without dependencies.
     *
     * @param stepNumber the step number in the proof sequence
     * @param type the type of this proof step
     * @param formula the formula at this step
     * @param rule the rule applied at this step
     * @param explanation additional explanation for this step
     */
    public ProofStep(int stepNumber, StepType type, String formula, String rule, String explanation) {
        this(stepNumber, type, formula, rule, new ArrayList<>(), explanation);
    }

    /**
     * Returns the step number.
     *
     * @return the step number
     */
    public int getStepNumber() {
        return stepNumber;
    }

    /**
     * Returns the step type.
     *
     * @return the step type
     */
    public StepType getType() {
        return type;
    }

    /**
     * Returns the formula at this step.
     *
     * @return the formula
     */
    public String getFormula() {
        return formula;
    }

    /**
     * Returns the rule applied at this step.
     *
     * @return the rule
     */
    public String getRule() {
        return rule;
    }

    /**
     * Returns a copy of the dependencies list.
     *
     * @return list of step numbers this step depends on
     */
    public List<Integer> getDependencies() {
        return new ArrayList<>(dependencies);
    }

    /**
     * Returns the explanation for this step.
     *
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Formats this proof step for display in a human-readable format.
     *
     * @return formatted string representation of this proof step
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%2d. %-15s %-30s", stepNumber, type.getDescription(), formula));

        if (rule != null && !rule.isEmpty()) {
            sb.append(String.format(" [%s]", rule));
        }

        if (!dependencies.isEmpty()) {
            sb.append(" from ");
            for (int i = 0; i < dependencies.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(dependencies.get(i));
            }
        }

        if (explanation != null && !explanation.isEmpty()) {
            sb.append(" (").append(explanation).append(")");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return format();
    }
}
