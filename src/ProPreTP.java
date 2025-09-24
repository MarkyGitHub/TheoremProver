
import inputoutput.InputReader;
import inputoutput.OutputWriter;
import inputoutput.Prompt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import predicate.parser.FreeTermsTable;
import propositional.scanner.Token;

/**
 * Main class for the Theorem Prover supporting both propositional and predicate
 * logic.
 *
 * <p>
 * This class provides an interactive command-line interface for theorem
 * proving. Users can choose between propositional logic (option A) or predicate
 * logic (option B) and input logical formulas for validation.</p>
 *
 * <p>
 * Features:
 * <ul>
 * <li>Propositional logic theorem proving with resolution and sequent
 * methods</li>
 * <li>Predicate logic theorem proving with CNF conversion</li>
 * <li>Interactive user interface with input validation</li>
 * <li>Comprehensive error handling and user feedback</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 1.0
 * @since 2005
 */
public class ProPreTP {

    private propositional.sequent.SequentMethod sequent;
    private propositional.resolution.ResolutionMethod resolutionPro;
    private propositional.scanner.Scanner scanPro;
    private propositional.parser.Parser parserPro;
    private String input;
    /**
     * Prompt handler for user interaction
     */
    private Prompt prompt;
    /**
     * Predicate logic scanner
     */
    private predicate.scanner.Scanner scanPre;
    /**
     * Predicate logic parser
     */
    private predicate.parser.Parser parserPre;
    /**
     * Syntax analyzer for predicate logic
     */
    private predicate.scanner.SyntaxAnalyser syntax;
    /**
     * Resolution method for predicate logic
     */
    private predicate.resolution.ResolutionMethod resolutionPre;
    /**
     * Buffered reader for console input
     */
    private BufferedReader buffer;

    /**
     * Constructs a new Theorem Prover instance. Initializes input handling and
     * prompt system.
     */
    public ProPreTP() {
        input = new String("");
        buffer = new BufferedReader(new InputStreamReader(System.in));
        prompt = new Prompt();
    }

    /**
     * Main execution loop for the theorem prover. Displays welcome message and
     * handles user input until exit.
     */
    public void run() {
        prompt.welcome();
        do {
            prompt.prompt();
            try {
                String line = buffer.readLine();
                if (line == null) {
                    // Handle end of input stream
                    input = "0";
                    break;
                }
                input = line.trim();
            } catch (IOException ex) {
                OutputWriter.displayMessage(ex.getMessage().toString());
                prompt.prompt();
            }
            if (input.length() == 1) {
                if (input.compareTo("A") == 0 || input.compareTo("a") == 0) {
                    // do propositional logic proof
                    InputReader ir = new InputReader();
                    String s = ir.getInput();
                    propositionalProof(s);
                } else if (input.compareTo("B") == 0 || input.compareTo("b") == 0) {
                    // do predicate logic proof
                    InputReader ir = new InputReader();
                    String s = ir.getInput();
                    predicateProof(s);
                } else if (input.compareTo("H") == 0 || input.compareTo("h") == 0) {
                    // show help
                    prompt.promptHelp();
                } else {
                    OutputWriter.displayError("Please enter A, B, H, or 0 to select an option");
                }
            } else {
                OutputWriter.displayError("Please enter A, B, H, or 0 to select an option");
            }
        } while (input.compareTo("0") != 0);
        if (input.compareTo("0") == 0) {
            OutputWriter.displayMessage("Thank you for choosing this program. Goodbye.");
            System.exit(0);
        }
    }

    /**
     * Handles predicate logic theorem proving.
     *
     * @param s the input formula string
     */
    private void predicateProof(String s) {
        scanPre = new predicate.scanner.Scanner(s);
        ArrayList tokens = scanPre.getScannedTokens();
        if (tokens.size() != 0) {
            syntax = new predicate.scanner.SyntaxAnalyser(tokens);
            if (syntax.getValidatedTokens()) {
                FreeTermsTable tab = null;
                parserPre = new predicate.parser.Parser(tokens);
                predicate.common.WFExpression formula = parserPre.accept();
                tab = parserPre.getReferenceTable();
                if (formula != null) {
                    prompt.displayProcessing("Analyzing predicate logic formula...");
                    OutputWriter.displaySectionHeader("PREDICATE LOGIC ANALYSIS");
                    OutputWriter.displayMessage("📝 Formula: " + formula);

                    resolutionPre = new predicate.resolution.ResolutionMethod(formula);
                    predicate.resolution.NormalFormAlgorithm nfa = new predicate.resolution.NormalFormAlgorithm(formula, tab);

                    OutputWriter.displayMessage("🔧 CNF Conversion:");
                    OutputWriter.displayMessage("   " + nfa.getNormalForm());

                    OutputWriter.displayInfo("Predicate logic analysis completed. Note: Full theorem proving for predicate logic is currently in development.");
                    /*
                     * if(resolutionPre.resolve()){ System.out.println("This
                     * Predicate logic sentence is valid!"); } else{
                     * System.out.println("This is not a valid Predicate logic
                     * sentence."); }
                     */
                } else {
                    OutputWriter.displayError("Parsing produced errors, please try again");
                }
            } else {
                OutputWriter.displayError("Syntax analysis produced errors, please try again");
            }
        } else {
            OutputWriter.displayError("Scanning produced errors, please try again");
        }
    }

    /**
     * Handles propositional logic theorem proving.
     *
     * @param s the input formula string
     */
    private void propositionalProof(String s) {
        scanPro = new propositional.scanner.Scanner(s);
        ArrayList<Token> tokens = scanPro.getTokens();
        if (tokens.size() != 0) {
            parserPro = new propositional.parser.Parser(tokens);
            propositional.common.Formula formula = parserPro.parse();
            if (formula != null) {
                prompt.displayProcessing("Analyzing propositional logic formula...");
                OutputWriter.displaySectionHeader("PROPOSITIONAL LOGIC ANALYSIS");
                OutputWriter.displayMessage("📝 Formula: " + formula);

                resolutionPro = new propositional.resolution.ResolutionMethod(formula);
                boolean isTheorem = resolutionPro.resolve();

                prompt.displayTheoremResult(isTheorem, formula.toString());

                if (isTheorem) {
                    OutputWriter.displaySuccess("The formula is a valid theorem!");
                } else {
                    OutputWriter.displayWarning("The formula is not a theorem.");
                }

                OutputWriter.displayMessage("");
                OutputWriter.displayMessage("🔍 Constructing proof sequence with sequent method...");
                sequent = new propositional.sequent.SequentMethod(formula);
                sequent.searchSequent();
                sequent.display();
            } else {
                OutputWriter.displayError("Parsing produced errors, please try again");
            }
        } else {
            OutputWriter.displayError("Scanning produced errors, please try again");
        }
    }

    /**
     * Main entry point for the theorem prover application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        ProPreTP protp = new ProPreTP();
        protp.run();
    }
}
