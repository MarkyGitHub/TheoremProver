package predicate.scanner;

import inputoutput.InputReader;
import inputoutput.OutputWriter;
import java.util.ArrayList;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Scanner class scans through a String sequence and creates tokens of type
 * Token and String, corresponding to the predicate symbols in the input String.
 ******************************************************************************/
public class Scanner {
    /** The precedence of any function symbol */
    private final int PRECEDENCE_FUNCTION = 0;
    /** The precedence of any constant symbol */
    private final int PRECEDENCE_CONSTANT = 1;
    /** The precedence of any variable symbol */
    private final int PRECEDENCE_VARIABLE = 2;
    /** The precedence of any universal quantifier symbol */
    private final int PRECEDENCE_UNIVERSAL = 3;
    /** The precedence of any existential quantifier symbol */
    private final int PRECEDENCE_EXISTENTIAL = 4;
    /** The precedence of any predicate symbol */
    private final int PRECEDENCE_PREDICATE = 5;
    /** The precedence of any bracket */
    private final int PRECEDENCE_BRACKETS = 6;
    /** The precedence of any iff connective */
    private final int PRECEDENCE_IFF = 7;
    /** The precedence of any implies connective */
    private final int PRECEDENCE_IMP = 8;
    /** The precedence of any or conective */
    private final int PRECEDENCE_OR = 9;
    /** The precedence of any and conective */
    private final int PRECEDENCE_AND = 10;
    /** The precedence of any not conective */
    private final int PRECEDENCE_NOT = 11;
    /** A TokenGenerator instance is used to generate the tokens */
    private TokenGenerator tokens;
    /** The input String is converted to a char array for processing */
    private char[] formula;
    /** A buffer is used to hold individual tokens */
    private char[] buffer;

    /** Initialisation of private data members */
    public Scanner(String inputString) {
        tokens = new TokenGenerator();
        tokens.makeToken("(", 6);
        formula = inputString.toCharArray();
    }

    /**
     * This method is used to advance the scanner by a number of characters
     * 
     * @param chars
     *            int is the number of chars the method should shift forward
     */
    private void shiftChar(int chars) {
        char[] temp = new char[(formula.length - chars)];
        for (int i = chars; i < formula.length; i++) {
            temp[(i - chars)] = formula[i];
        }
        formula = temp;
        this.getCharState();
    }

    /**
     * This method is used to tokanise a symbol scanned by the scanner. It
     * creates a token of type Token
     * 
     * @param chars
     *            int is the number of characters representing the token's data
     * @param type
     *            int is the token's precedence
     */
    private void nextItem(int type, int chars) {
        buffer = new char[chars];
        for (int i = 0; i < chars; i++) {
            buffer[i] = formula[i];
        }
        tokens.makeToken(new String(buffer), type);
        this.shiftChar(chars);
    }

    /**
     * Checks to see if the character is a number
     * 
     * @param c
     *            char is the character to be validated as numeric
     * @return boolean true if the character is numeric, false otherwise
     */
    private boolean isNumber(char c) {
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
                return true;
            default:
                return false;
        }
    }

    private boolean getCharState() {
        if (formula != null) {
            switch (formula[0]) {
                // tokenising bracket symbols
                case '(':
                case ')':
                    nextItem(this.PRECEDENCE_BRACKETS, 1);
                    break;
                // tokenising single character connective symbols
                case '|':
                    nextItem(this.PRECEDENCE_OR, 1);
                    break;
                case '!':
                    nextItem(this.PRECEDENCE_NOT, 1);
                    break;
                case '&':
                    nextItem(this.PRECEDENCE_AND, 1);
                    break;
                // tokenising iff connective "<=>"
                case '<':
                    if (formula.length > 2 && (formula[1] == '=' && formula[2] == '>')) {
                        nextItem(this.PRECEDENCE_IMP, 3);
                    } else {
                        OutputWriter.displayError("Error in input. Please try again.");
                    }
                    break;
                // tokenising implies connective "=>"
                case '=':
                    if (formula.length > 1 && formula[1] == '>') {
                        nextItem(this.PRECEDENCE_IFF, 2);
                    } else {
                        OutputWriter.displayError("Error in input. Please try again.");
                    }
                    break;
                // tokenising variables symbols
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    if (formula.length > 3 && (this.isNumber(formula[3]) && this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        OutputWriter.displayError("Error in input. Please try again.");
                    } else if ((formula.length > 2) && (this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        nextItem(this.PRECEDENCE_VARIABLE, 3);
                    } else if (formula.length > 1 && this.isNumber(formula[1])) {
                        nextItem(this.PRECEDENCE_VARIABLE, 2);
                    } else {
                        nextItem(this.PRECEDENCE_VARIABLE, 1);
                    }
                    break;
                // tokenising quantifier symbols
                case 'A':
                    nextItem(this.PRECEDENCE_UNIVERSAL, 1);
                    break;
                case 'E':
                    nextItem(this.PRECEDENCE_EXISTENTIAL, 1);
                    break;
                // tokenising predicate symbols
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                    if (formula.length > 3 && (this.isNumber(formula[3]) && this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        OutputWriter.displayError("Error in input. Please try again.");
                    } else if ((formula.length > 2) && (this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        nextItem(this.PRECEDENCE_PREDICATE, 3);
                    } else if (formula.length > 1 && this.isNumber(formula[1])) {
                        nextItem(this.PRECEDENCE_PREDICATE, 2);
                    } else {
                        nextItem(this.PRECEDENCE_PREDICATE, 1);
                    }
                    break;
                // tokenising constants symbols
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                    if (formula.length > 3 && (this.isNumber(formula[3]) && this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        OutputWriter.displayError("Error in input. Please try again.");
                    } else if ((formula.length > 2) && (this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        nextItem(this.PRECEDENCE_CONSTANT, 3);
                    } else if (formula.length > 1 && this.isNumber(formula[1])) {
                        nextItem(this.PRECEDENCE_CONSTANT, 2);
                    } else {
                        nextItem(this.PRECEDENCE_CONSTANT, 1);
                    }
                    break;
                // tokenising function symbols
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                    if (formula.length > 3 && (this.isNumber(formula[3]) && this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        OutputWriter.displayError("Error in input. Please try again.");
                    } else if ((formula.length > 2) && (this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        nextItem(this.PRECEDENCE_FUNCTION, 3);
                    } else if (formula.length > 1 && this.isNumber(formula[1])) {
                        nextItem(this.PRECEDENCE_FUNCTION, 2);
                    } else {
                        nextItem(this.PRECEDENCE_FUNCTION, 1);
                    }
                    break;
                // removing whitespace characters
                case ' ':
                case '\n':
                case '\t':
                    this.shiftChar(1);
                    break;
                // End of input string
                case '.':
                    tokens.makeToken(")", 6);
                    OutputWriter.displayMessage("Scanning process completed successfully.");
                    return true;
                default:
                    OutputWriter.displayError("Error in input; unrecognised character used. Please try again.");
                    return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * This method can be used to set an input for the scanner to tokenise
     * 
     * @param string
     *            String is the input string
     */
    public void setInput(String string) {
        this.formula = string.toCharArray();
    }

    /**
     * This method is used to get the sequence of tokens that are generated by
     * the scanner
     * 
     * @return ArrayList is the sequence of tokens generated by the scanner
     */
    public ArrayList getScannedTokens() {
        if (this.getCharState()) {
            return tokens.getTokens();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        InputReader ir = new InputReader();
        String s = ir.getInput();
        System.out.println(s);
        Scanner sc = new Scanner(s);
        ArrayList l = sc.getScannedTokens();
        System.out.println(l);
    }
}
