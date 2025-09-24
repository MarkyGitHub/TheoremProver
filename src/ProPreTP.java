import inputoutput.InputReader;
import inputoutput.OutputWriter;
import inputoutput.Prompt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import predicate.parser.FreeTermsTable;
import propositional.scanner.Token;

public class ProPreTP {
    private propositional.sequent.SequentMethod sequent;
    private propositional.resolution.ResolutionMethod resolutionPro;
    private propositional.scanner.Scanner scanPro;
    private propositional.parser.Parser parserPro;
    private String input;
    /** This class displays a prompt to the user for data input */
    private Prompt prompt;
    private predicate.scanner.Scanner scanPre;
    private predicate.parser.Parser parserPre;
    private predicate.scanner.SyntaxAnalyser syntax;
    private predicate.resolution.ResolutionMethod resolutionPre;
    /**
     * A buffered reader is used to read the characters from the console using
     * InputStreamReader and System.in
     */
    private BufferedReader buffer;

    public ProPreTP() {
        input = new String("");
        buffer = new BufferedReader(new InputStreamReader(System.in));
        prompt = new Prompt();
    }

    public void run() {
        prompt.welcome();
        do {
            prompt.prompt();
            try {
                input = buffer.readLine().trim();
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
                }
            } else {
                OutputWriter.displayError("Please enter A or B to select a proof method");
            }
        } while (input.compareTo("0") != 0);
        if (input.compareTo("0") == 0) {
            OutputWriter.displayMessage("Thank you for choosing this program. Goodbye.");
            System.exit(0);
        }
    }

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
                    OutputWriter.displayMessage("");
                    System.out.println("You have entered the following Predicate logic formula: " + formula);
                    resolutionPre = new predicate.resolution.ResolutionMethod(formula);
                    predicate.resolution.NormalFormAlgorithm nfa = new predicate.resolution.NormalFormAlgorithm(formula, tab);
                    System.out.println("The conjunctive normal form of this predicate sentence: " + nfa.getNormalForm());
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

    private void propositionalProof(String s) {
        scanPro = new propositional.scanner.Scanner(s);
        ArrayList<Token> tokens = scanPro.getTokens();
        if (tokens.size() != 0) {
            parserPro = new propositional.parser.Parser(tokens);
            propositional.common.Formula formula = parserPro.parse();
            if (formula != null) {
                OutputWriter.displayMessage("");
                System.out.println("You have entered the following Propositional formula: " + formula);
                resolutionPro = new propositional.resolution.ResolutionMethod(formula);
                if (resolutionPro.resolve()) {
                    System.out.println("This Propositional sentence is valid!");
                } else {
                    System.out.println("This is not a valid Propositional sentence.");
                }
                System.out.println("");
                System.out.println("Constructing proof sequence with sequent method.......");
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

    public static void main(String[] args) {
        ProPreTP protp = new ProPreTP();
        protp.run();
    }
}
