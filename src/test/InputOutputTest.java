package test;

import inputoutput.Prompt;
import inputoutput.InputReader;
import inputoutput.OutputWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for input/output components.
 * 
 * <p>This test suite validates the user interface components including
 * prompt display, input reading, and output formatting functionality.</p>
 * 
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class InputOutputTest {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    /**
     * Main entry point for running all input/output tests.
     */
    public static void main(String[] args) {
        System.out.println("🧪 RUNNING INPUT/OUTPUT UNIT TESTS");
        System.out.println("=".repeat(50));
        
        testPromptCreation();
        testOutputWriterMethods();
        testErrorMessageFormatting();
        testSuccessMessageFormatting();
        testWarningMessageFormatting();
        testInfoMessageFormatting();
        testSectionHeaderFormatting();
        testInputReaderCreation();
        
        displayResults();
    }
    
    /**
     * Tests prompt object creation.
     */
    private static void testPromptCreation() {
        System.out.println("Testing prompt creation...");
        try {
            Prompt prompt = new Prompt();
            assert prompt != null : "Prompt should be created successfully";
            recordPass("Prompt creation");
            
        } catch (Exception e) {
            recordFail("Prompt creation", e.getMessage());
        }
    }
    
    /**
     * Tests OutputWriter static methods.
     */
    private static void testOutputWriterMethods() {
        System.out.println("Testing OutputWriter methods...");
        try {
            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            // Test displayMessage
            OutputWriter.displayMessage("Test message");
            String output = outputStream.toString();
            assert output.contains("Test message") : "displayMessage should output the message";
            
            // Reset output stream
            outputStream.reset();
            
            // Test displayPrompt
            OutputWriter.displayPrompt("Test prompt");
            output = outputStream.toString();
            assert output.contains("Test prompt") : "displayPrompt should output the prompt";
            
            // Restore original output
            System.setOut(originalOut);
            
            recordPass("OutputWriter methods");
            
        } catch (Exception e) {
            recordFail("OutputWriter methods", e.getMessage());
        }
    }
    
    /**
     * Tests error message formatting.
     */
    private static void testErrorMessageFormatting() {
        System.out.println("Testing error message formatting...");
        try {
            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalErr = System.err;
            System.setErr(new PrintStream(outputStream));
            
            OutputWriter.displayError("Test error");
            String output = outputStream.toString();
            assert output.contains("❌ ERROR: Test error") : "Error message should be formatted with emoji and prefix";
            
            // Restore original error stream
            System.setErr(originalErr);
            
            recordPass("Error message formatting");
            
        } catch (Exception e) {
            recordFail("Error message formatting", e.getMessage());
        }
    }
    
    /**
     * Tests success message formatting.
     */
    private static void testSuccessMessageFormatting() {
        System.out.println("Testing success message formatting...");
        try {
            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            OutputWriter.displaySuccess("Test success");
            String output = outputStream.toString();
            assert output.contains("✅ SUCCESS: Test success") : "Success message should be formatted with emoji and prefix";
            
            // Restore original output stream
            System.setOut(originalOut);
            
            recordPass("Success message formatting");
            
        } catch (Exception e) {
            recordFail("Success message formatting", e.getMessage());
        }
    }
    
    /**
     * Tests warning message formatting.
     */
    private static void testWarningMessageFormatting() {
        System.out.println("Testing warning message formatting...");
        try {
            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            OutputWriter.displayWarning("Test warning");
            String output = outputStream.toString();
            assert output.contains("⚠️ WARNING: Test warning") : "Warning message should be formatted with emoji and prefix";
            
            // Restore original output stream
            System.setOut(originalOut);
            
            recordPass("Warning message formatting");
            
        } catch (Exception e) {
            recordFail("Warning message formatting", e.getMessage());
        }
    }
    
    /**
     * Tests info message formatting.
     */
    private static void testInfoMessageFormatting() {
        System.out.println("Testing info message formatting...");
        try {
            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            OutputWriter.displayInfo("Test info");
            String output = outputStream.toString();
            assert output.contains("ℹ️ INFO: Test info") : "Info message should be formatted with emoji and prefix";
            
            // Restore original output stream
            System.setOut(originalOut);
            
            recordPass("Info message formatting");
            
        } catch (Exception e) {
            recordFail("Info message formatting", e.getMessage());
        }
    }
    
    /**
     * Tests section header formatting.
     */
    private static void testSectionHeaderFormatting() {
        System.out.println("Testing section header formatting...");
        try {
            // Capture output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            OutputWriter.displaySectionHeader("Test Header");
            String output = outputStream.toString();
            assert output.contains("TEST HEADER") : "Section header should be uppercase";
            assert output.contains("=".repeat(70)) : "Section header should have separator line";
            
            // Restore original output stream
            System.setOut(originalOut);
            
            recordPass("Section header formatting");
            
        } catch (Exception e) {
            recordFail("Section header formatting", e.getMessage());
        }
    }
    
    /**
     * Tests InputReader creation.
     */
    private static void testInputReaderCreation() {
        System.out.println("Testing InputReader creation...");
        try {
            InputReader reader = new InputReader();
            assert reader != null : "InputReader should be created successfully";
            recordPass("InputReader creation");
            
        } catch (Exception e) {
            recordFail("InputReader creation", e.getMessage());
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
        System.out.println("\n📊 INPUT/OUTPUT TEST RESULTS");
        System.out.println("=".repeat(50));
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total: " + (testsPassed + testsFailed));
        
        if (testsFailed == 0) {
            System.out.println("\n🎉 All input/output tests passed!");
        } else {
            System.out.println("\n⚠️ Some input/output tests failed!");
        }
    }
}
