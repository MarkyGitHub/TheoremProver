package common;

/**
 * Centralized error handling and user feedback system for the theorem prover.
 *
 * <p>
 * This class provides a unified approach to error handling and user
 * communication throughout the theorem prover. It categorizes different types
 * of errors and provides formatted output for consistent user experience.</p>
 *
 * <p>
 * Features:
 * <ul>
 * <li>Categorized error types with descriptive messages</li>
 * <li>Context-aware error reporting</li>
 * <li>Formatted output with visual indicators</li>
 * <li>Help text for syntax guidance</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ErrorHandler {

    /**
     * Enumeration of error types with their display names.
     */
    public enum ErrorType {
        SYNTAX_ERROR("Syntax Error"),
        PARSING_ERROR("Parsing Error"),
        INPUT_ERROR("Input Error"),
        LOGIC_ERROR("Logic Error"),
        SYSTEM_ERROR("System Error");

        /**
         * The display name for this error type
         */
        private final String displayName;

        /**
         * Constructs an error type with the specified display name.
         *
         * @param displayName the human-readable name for this error type
         */
        ErrorType(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Returns the display name of this error type.
         *
         * @return the display name
         */
        public String getDisplayName() {
            return displayName;
        }
    }

    /**
     * Displays a formatted error message with optional context.
     *
     * @param type the type of error
     * @param message the error message
     * @param context additional context information (can be null)
     */
    public static void displayError(ErrorType type, String message, String context) {
        System.err.println();
        System.err.println("❌ " + type.getDisplayName() + ": " + message);
        if (context != null && !context.trim().isEmpty()) {
            System.err.println("   Context: " + context);
        }
        System.err.println();
    }

    /**
     * Displays a formatted error message without context.
     *
     * @param type the type of error
     * @param message the error message
     */
    public static void displayError(ErrorType type, String message) {
        displayError(type, message, null);
    }

    /**
     * Displays a syntax error with helpful suggestions and position indicator.
     *
     * @param message the error message
     * @param input the input string that caused the error
     * @param position the position where the error occurred
     */
    public static void displaySyntaxError(String message, String input, int position) {
        System.err.println();
        System.err.println("❌ Syntax Error: " + message);
        System.err.println("   Input: " + input);
        if (position >= 0 && position < input.length()) {
            System.err.println("   Position: " + " ".repeat(position) + "^");
        }
        System.err.println("   Expected: Valid logical formula");
        System.err.println();
    }

    /**
     * Displays a parsing error with specific details about what was expected
     * vs. found.
     *
     * @param expected what was expected
     * @param found what was actually found
     * @param context additional context (can be null)
     */
    public static void displayParsingError(String expected, String found, String context) {
        System.err.println();
        System.err.println("❌ Parsing Error: Expected " + expected + ", but found " + found);
        if (context != null) {
            System.err.println("   Context: " + context);
        }
        System.err.println();
    }

    /**
     * Displays an input validation error with format guidance.
     *
     * @param message the error message
     * @param validFormat example of valid format (can be null)
     */
    public static void displayInputError(String message, String validFormat) {
        System.err.println();
        System.err.println("❌ Input Error: " + message);
        if (validFormat != null) {
            System.err.println("   Valid format: " + validFormat);
        }
        System.err.println();
    }

    /**
     * Displays a success message with visual indicator.
     *
     * @param message the success message
     */
    public static void displaySuccess(String message) {
        System.out.println();
        System.out.println("✅ " + message);
        System.out.println();
    }

    /**
     * Displays an informational message.
     *
     * @param message the info message
     */
    public static void displayInfo(String message) {
        System.out.println();
        System.out.println("ℹ️  " + message);
        System.out.println();
    }

    /**
     * Displays a warning message.
     *
     * @param message the warning message
     */
    public static void displayWarning(String message) {
        System.out.println();
        System.out.println("⚠️  " + message);
        System.out.println();
    }

    /**
     * Returns help text for propositional logic syntax.
     *
     * @return formatted help text for propositional logic
     */
    public static String getPropositionalHelp() {
        return "Propositional Logic Syntax:\n"
                + "  Variables: P, Q, R, S, T (capital letters)\n"
                + "  Connectives: ! (NOT), & (AND), | (OR), => (IMPLIES), <=> (IFF)\n"
                + "  Example: (P => Q) & (Q => R)";
    }

    /**
     * Returns help text for predicate logic syntax.
     *
     * @return formatted help text for predicate logic
     */
    public static String getPredicateHelp() {
        return "Predicate Logic Syntax:\n"
                + "  Variables: x, y, z, u, v, w (lowercase letters)\n"
                + "  Constants: a, b, c, d, e (lowercase letters)\n"
                + "  Functions: f, g, h, i, j, k, l, m, n, o, p, q, r, s, t (lowercase letters)\n"
                + "  Predicates: F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T (capital letters)\n"
                + "  Quantifiers: A (universal ∀), E (existential ∃)\n"
                + "  Example: (Ax (P(x) => Q(x)))";
    }
}
