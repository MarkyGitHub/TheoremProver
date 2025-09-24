package test;

import inputoutput.OutputWriter;
import inputoutput.Prompt;

/**
 * Demo class to showcase the enhanced user interface.
 */
public class InterfaceDemo {

    public static void main(String[] args) {
        Prompt prompt = new Prompt();

        System.out.println("=== ENHANCED THEOREM PROVER INTERFACE DEMO ===\n");

        // Show welcome message
        prompt.welcome();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMO: Enhanced Prompts and Output");
        System.out.println("=".repeat(70));

        // Show main prompt
        prompt.prompt();

        // Show help
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMO: Help System");
        System.out.println("=".repeat(70));
        prompt.promptHelp();

        // Show enhanced output examples
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMO: Enhanced Output Messages");
        System.out.println("=".repeat(70));

        OutputWriter.displaySuccess("This is a success message!");
        OutputWriter.displayError("This is an error message!");
        OutputWriter.displayWarning("This is a warning message!");
        OutputWriter.displayInfo("This is an info message!");

        // Show theorem result
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMO: Theorem Results");
        System.out.println("=".repeat(70));

        prompt.displayTheoremResult(true, "(P => Q) & (Q => R) => (P => R)");
        prompt.displayTheoremResult(false, "P & !P");

        // Show processing message
        prompt.displayProcessing("Analyzing complex formula...");

        System.out.println("\n=== DEMO COMPLETE ===");
    }
}
