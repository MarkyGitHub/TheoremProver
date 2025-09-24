import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Theorem Prover - Distribution Version
 * Clean version without Unicode emojis for maximum compatibility
 * 
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class ProPreTP_Distribution {
    
    public static void main(String[] args) {
        ProPreTP_Distribution prover = new ProPreTP_Distribution();
        prover.welcome();
        prover.run();
    }
    
    /**
     * Displays welcome message
     */
    public void welcome() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("   THEOREM PROVER - DISTRIBUTION VERSION");
        System.out.println("========================================");
        System.out.println();
        System.out.println("WELCOME! This program can prove theorems in:");
        System.out.println("  * Propositional Logic");
        System.out.println("  * Predicate Logic");
        System.out.println();
        System.out.println("Type '0' at any time to exit.");
        System.out.println("========================================");
        System.out.println();
    }
    
    /**
     * Main program loop
     */
    public void run() {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String input;
        
        try {
            while (true) {
                prompt();
                input = buffer.readLine();
                
                if (input == null || input.equals("0")) {
                    System.out.println();
                    System.out.println("Thank you for using the Theorem Prover. Goodbye!");
                    break;
                }
                
                input = input.trim().toUpperCase();
                
                if (input.equals("A")) {
                    propositionalProof(buffer);
                } else if (input.equals("B")) {
                    predicateProof(buffer);
                } else if (input.equals("H")) {
                    showHelp();
                } else {
                    System.out.println("Invalid input. Please enter A, B, H, or 0.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
    
    /**
     * Displays main prompt
     */
    public void prompt() {
        System.out.println();
        System.out.println("Choose the type of logic to work with:");
        System.out.println("  A) Propositional Logic");
        System.out.println("  B) Predicate Logic");
        System.out.println("  H) Show help and syntax guide");
        System.out.println("  0) Exit the program");
        System.out.println();
        System.out.print("Enter your choice (A/B/H/0): ");
    }
    
    /**
     * Handles propositional logic proof
     */
    public void propositionalProof(BufferedReader buffer) {
        try {
            System.out.println();
            System.out.println("=== PROPOSITIONAL LOGIC THEOREM PROVER ===");
            System.out.println();
            System.out.println("Enter a propositional logic formula.");
            System.out.println("Example: (P => Q) & (Q => R) => (P => R).");
            System.out.println();
            System.out.print("Formula: ");
            
            String formula = buffer.readLine();
            if (formula == null || formula.equals("0")) {
                return;
            }
            
            System.out.println();
            System.out.println("Processing: " + formula);
            System.out.println("Please wait...");
            
            try {
                // Use the existing propositional logic classes
                propositional.scanner.Scanner scanner = new propositional.scanner.Scanner(formula);
                java.util.ArrayList tokens = scanner.getTokens();
                
                propositional.parser.Parser parser = new propositional.parser.Parser(tokens);
                propositional.common.Formula parsedFormula = parser.parse();
                
                if (parsedFormula != null) {
                    propositional.resolution.ResolutionMethod resolver = new propositional.resolution.ResolutionMethod(parsedFormula);
                    boolean result = resolver.resolve();
                    
                    System.out.println();
                    if (result) {
                        System.out.println("*** THEOREM PROVEN! ***");
                        System.out.println("The formula is a valid theorem.");
                    } else {
                        System.out.println("*** NOT A THEOREM ***");
                        System.out.println("The formula is not a theorem.");
                    }
                } else {
                    System.out.println("*** PARSING ERROR ***");
                    System.out.println("Could not parse the formula. Please check syntax.");
                }
            } catch (Exception e) {
                System.out.println("*** ERROR ***");
                System.out.println("Error processing formula: " + e.getMessage());
            }
            
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
    
    /**
     * Handles predicate logic proof
     */
    public void predicateProof(BufferedReader buffer) {
        try {
            System.out.println();
            System.out.println("=== PREDICATE LOGIC THEOREM PROVER ===");
            System.out.println();
            System.out.println("Enter a predicate logic formula.");
            System.out.println("Example: Ax (P(x) => Q(x)).");
            System.out.println();
            System.out.print("Formula: ");
            
            String formula = buffer.readLine();
            if (formula == null || formula.equals("0")) {
                return;
            }
            
            System.out.println();
            System.out.println("Processing: " + formula);
            System.out.println("Please wait...");
            
            try {
                // Use the existing predicate logic classes
                predicate.scanner.Scanner scanner = new predicate.scanner.Scanner(formula);
                java.util.ArrayList tokens = scanner.getScannedTokens();
                
                predicate.scanner.SyntaxAnalyser syntax = new predicate.scanner.SyntaxAnalyser(tokens);
                boolean isValid = syntax.getValidatedTokens();
                
                System.out.println();
                if (isValid) {
                    System.out.println("*** FORMULA VALIDATED ***");
                    System.out.println("The formula syntax is correct.");
                    System.out.println("Note: Full theorem proving for predicate logic is in development.");
                } else {
                    System.out.println("*** SYNTAX ERROR ***");
                    System.out.println("The formula syntax is incorrect.");
                }
            } catch (Exception e) {
                System.out.println("*** ERROR ***");
                System.out.println("Error processing formula: " + e.getMessage());
            }
            
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
    
    /**
     * Shows help and syntax guide
     */
    public void showHelp() {
        System.out.println();
        System.out.println("=== THEOREM PROVER HELP GUIDE ===");
        System.out.println();
        System.out.println("PROPOSITIONAL LOGIC:");
        System.out.println("  Variables: P, Q, R, S, T (capital letters)");
        System.out.println("  Connectives:");
        System.out.println("    !  - NOT (negation)");
        System.out.println("    &  - AND (conjunction)");
        System.out.println("    |  - OR (disjunction)");
        System.out.println("    => - IMPLIES (implication)");
        System.out.println("    <=> - IFF (biconditional)");
        System.out.println("  Brackets: ( and )");
        System.out.println("  End formula with: .");
        System.out.println();
        System.out.println("PREDICATE LOGIC:");
        System.out.println("  Variables: x, y, z (lowercase)");
        System.out.println("  Constants: a, b, c (lowercase)");
        System.out.println("  Predicates: P, Q, R (capital letters)");
        System.out.println("  Quantifiers:");
        System.out.println("    A - Universal quantifier (for all)");
        System.out.println("    E - Existential quantifier (there exists)");
        System.out.println("  Same connectives as propositional logic");
        System.out.println("  End formula with: .");
        System.out.println();
        System.out.println("EXAMPLES:");
        System.out.println("  Propositional: P => P.");
        System.out.println("  Propositional: (P => Q) & (Q => R) => (P => R).");
        System.out.println("  Predicate: Ax P(x).");
        System.out.println("  Predicate: Ax (P(x) => Q(x)).");
        System.out.println();
        System.out.println("USAGE TIPS:");
        System.out.println("  1. Always end formulas with a period (.)");
        System.out.println("  2. Use parentheses to clarify precedence");
        System.out.println("  3. Check syntax carefully before submitting");
        System.out.println("  4. Type '0' to exit at any time");
        System.out.println();
    }
}
