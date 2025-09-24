package inputoutput;

import java.util.Date;

/**
 * Enhanced prompt system for user interaction.
 *
 * <p>
 * This class provides user-friendly prompts and guidance for the theorem
 * prover. It includes welcome messages, syntax help, and clear instructions for
 * both propositional and predicate logic input.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2005
 */
public class Prompt {

    /**
     * Constructs a new Prompt instance.
     */
    public Prompt() {
        super();
    }

    /**
     * Displays an enhanced welcome message with clear instructions.
     */
    public void welcome() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("=".repeat(70));
        OutputWriter.displayMessage("           THEOREM PROVER - LOGICAL FORMULA VALIDATOR");
        OutputWriter.displayMessage("=".repeat(70));
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Welcome! This program can prove theorems in:");
        OutputWriter.displayMessage("  • Propositional Logic (simple statements with connectives)");
        OutputWriter.displayMessage("  • Predicate Logic (statements with quantifiers and variables)");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Examples:");
        OutputWriter.displayMessage("  Propositional: (P => Q) & (Q => R) => (P => R)");
        OutputWriter.displayMessage("  Predicate: (Ax (P(x) => Q(x))) & P(a) => Q(a)");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Type 'help' at any time for syntax information.");
        OutputWriter.displayMessage("Type '0' at any time to exit.");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("-".repeat(70));
        OutputWriter.displayMessage("Author: Mark P Schlichtmann | Supervisor: Dr Chris Fox");
        OutputWriter.displayMessage("Department of Computer Science - " + new Date());
        OutputWriter.displayMessage("-".repeat(70));
    }

    /**
     * Displays the main selection prompt with clear options.
     */
    public void prompt() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Choose the type of logic to work with:");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("  A) Propositional Logic");
        OutputWriter.displayMessage("     - Simple statements with logical connectives");
        OutputWriter.displayMessage("     - Variables: P, Q, R, S, T (capital letters)");
        OutputWriter.displayMessage("     - Example: (P => Q) & (Q => R)");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("  B) Predicate Logic");
        OutputWriter.displayMessage("     - Statements with quantifiers and variables");
        OutputWriter.displayMessage("     - Variables: x, y, z (lowercase)");
        OutputWriter.displayMessage("     - Example: (Ax (P(x) => Q(x)))");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("  H) Show detailed help and syntax guide");
        OutputWriter.displayMessage("  0) Exit the program");
        OutputWriter.displayMessage("");
        OutputWriter.displayPrompt("Enter your choice (A/B/H/0): ");
    }

    /**
     * Displays enhanced syntax symbols prompt for propositional logic.
     */
    public void promptSymbols() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Propositional Logic Syntax Guide:");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Connectives:");
        OutputWriter.displayMessage("  &    AND     (conjunction)");
        OutputWriter.displayMessage("  |    OR      (disjunction)");
        OutputWriter.displayMessage("  !    NOT     (negation)");
        OutputWriter.displayMessage("  =>   IMPLY   (implication)");
        OutputWriter.displayMessage("  <=>  IFF     (biconditional)");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Variables: P, Q, R, S, T (capital letters)");
        OutputWriter.displayMessage("Parentheses: Use () to group expressions");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Examples:");
        OutputWriter.displayMessage("  Simple: P");
        OutputWriter.displayMessage("  Complex: ((P => Q) & (Q => R)) => (P => R)");
        OutputWriter.displayMessage("  Negation: !P");
        OutputWriter.displayMessage("");
        OutputWriter.displayPrompt("Enter your propositional formula: ");
    }

    /**
     * Displays comprehensive help information.
     */
    public void promptHelp() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("=".repeat(70));
        OutputWriter.displayMessage("                    THEOREM PROVER HELP GUIDE");
        OutputWriter.displayMessage("=".repeat(70));

        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("PROPOSITIONAL LOGIC:");
        OutputWriter.displayMessage("  Variables: P, Q, R, S, T (capital letters)");
        OutputWriter.displayMessage("  Connectives:");
        OutputWriter.displayMessage("    &    AND     (P & Q means 'P and Q')");
        OutputWriter.displayMessage("    |    OR      (P | Q means 'P or Q')");
        OutputWriter.displayMessage("    !    NOT     (!P means 'not P')");
        OutputWriter.displayMessage("    =>   IMPLY   (P => Q means 'if P then Q')");
        OutputWriter.displayMessage("    <=>  IFF     (P <=> Q means 'P if and only if Q')");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("  Examples:");
        OutputWriter.displayMessage("    P => P                    (tautology)");
        OutputWriter.displayMessage("    (P => Q) & (Q => R)       (transitivity)");
        OutputWriter.displayMessage("    P & !P                    (contradiction)");

        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("PREDICATE LOGIC:");
        OutputWriter.displayMessage("  Variables: x, y, z, u, v, w (lowercase)");
        OutputWriter.displayMessage("  Constants: a, b, c, d, e (lowercase)");
        OutputWriter.displayMessage("  Functions: f, g, h, ... (lowercase)");
        OutputWriter.displayMessage("  Predicates: P, Q, R, S, T (capital letters)");
        OutputWriter.displayMessage("  Quantifiers:");
        OutputWriter.displayMessage("    A     Universal   (Ax P(x) means 'for all x, P(x)')");
        OutputWriter.displayMessage("    E     Existential (Ex P(x) means 'there exists x such that P(x)')");
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("  Examples:");
        OutputWriter.displayMessage("    Ax (P(x) => Q(x))         (universal implication)");
        OutputWriter.displayMessage("    Ex P(x)                   (existential statement)");
        OutputWriter.displayMessage("    P(a)                      (atomic statement)");

        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("USAGE TIPS:");
        OutputWriter.displayMessage("  • Always end formulas with a period (.)");
        OutputWriter.displayMessage("  • Use parentheses to clarify precedence");
        OutputWriter.displayMessage("  • The program will tell you if your formula is a theorem");
        OutputWriter.displayMessage("  • Type '0' to exit at any time");

        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("=".repeat(70));
    }

    /**
     * Displays a success message for theorem validation.
     */
    public void displayTheoremResult(boolean isTheorem, String formula) {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("-".repeat(50));
        if (isTheorem) {
            OutputWriter.displayMessage("✓ THEOREM PROVEN!");
            OutputWriter.displayMessage("The formula '" + formula + "' is a valid theorem.");
        } else {
            OutputWriter.displayMessage("✗ NOT A THEOREM");
            OutputWriter.displayMessage("The formula '" + formula + "' is not a theorem.");
        }
        OutputWriter.displayMessage("-".repeat(50));
    }

    /**
     * Displays processing status message.
     */
    public void displayProcessing(String message) {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Processing: " + message);
        OutputWriter.displayMessage("Please wait...");
    }
}
