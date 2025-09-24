package inputoutput;

/**
 * Enhanced output writer for displaying formatted messages to the console.
 *
 * <p>
 * This class provides improved formatting and display capabilities for the
 * theorem prover, including color-coded messages, structured output, and better
 * user experience.</p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2005
 */
public class OutputWriter {

    /**
     * Constructs a new OutputWriter instance.
     */
    public OutputWriter() {
        super();
    }

    /**
     * Displays error messages with enhanced formatting.
     *
     * @param error the error message to be displayed
     */
    public static void displayError(String error) {
        System.err.println();
        System.err.println("❌ ERROR: " + error);
        System.err.println();
    }

    /**
     * Displays proof results with enhanced formatting.
     *
     * @param proof the proof to be displayed
     */
    public static void displayProof(String proof) {
        System.out.println();
        System.out.println("📋 PROOF:");
        System.out.println("-".repeat(50));
        System.out.println(proof);
        System.out.println("-".repeat(50));
        System.out.println();
    }

    /**
     * Displays general messages to the console.
     *
     * @param message the message to be displayed
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays prompts without line breaks.
     *
     * @param prompt the prompt message to be displayed
     */
    public static void displayPrompt(String prompt) {
        System.out.print(prompt);
    }

    /**
     * Displays success messages with enhanced formatting.
     *
     * @param message the success message to be displayed
     */
    public static void displaySuccess(String message) {
        System.out.println();
        System.out.println("✅ SUCCESS: " + message);
        System.out.println();
    }

    /**
     * Displays informational messages with enhanced formatting.
     *
     * @param message the info message to be displayed
     */
    public static void displayInfo(String message) {
        System.out.println();
        System.out.println("ℹ️  INFO: " + message);
        System.out.println();
    }

    /**
     * Displays warning messages with enhanced formatting.
     *
     * @param message the warning message to be displayed
     */
    public static void displayWarning(String message) {
        System.out.println();
        System.out.println("⚠️  WARNING: " + message);
        System.out.println();
    }

    /**
     * Displays a separator line for better visual organization.
     */
    public static void displaySeparator() {
        System.out.println("-".repeat(70));
    }

    /**
     * Displays a section header with enhanced formatting.
     *
     * @param title the section title
     */
    public static void displaySectionHeader(String title) {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("  " + title.toUpperCase());
        System.out.println("=".repeat(70));
        System.out.println();
    }
}
