package propositional.scanner;

import inputoutput.InputReader;
import java.util.ArrayList;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Scanner class scans through a String sequence and creates tokens of type
 * Token and String, corresponding to the propositional symbols in the input
 * String.
 ******************************************************************************/
public class Scanner {
    /** The input String is converted to a char array for processing */
    private char[] formula;
    /** A buffer is used to hold individual tokens */
    private char[] buffer;
    /** A TokenGenerator instance is used to generate the tokens */
    private TokenGenerator tokens;

    /**
     * Initialisation of private data members
     * 
     * @param inputString
     *            String is the input string for tokenising
     */
    public Scanner(String inputString) {
        tokens = new TokenGenerator();
        tokens.makeToken("(", Precedence.BRACKETS);
        formula = inputString.toCharArray();
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
        return;
    }

    /**
     * This method is used to tokanise a symbol scanned by the scanner. It
     * creates a token of type Token and also returns a String version of that
     * same token.
     * 
     * @param chars
     *            int is the number of characters representing the token's data
     * @param type
     *            int is the token's precedence
     * @return String the token as a string
     */
    private String nextItem(int chars, Precedence type) {
        buffer = new char[chars];
        for (int i = 0; i < chars; i++) {
            buffer[i] = formula[i];
        }
        tokens.makeToken(new String(buffer), type);
        this.shiftChar(chars);
        return new String(buffer);
    }

    /**
     * This method calls the other methods to scan the input string. It only
     * makes a single token at each call.
     * 
     * @return String is the string representing the token
     */
    public String getCharState() {
        while (formula.length != 0) {
            switch (formula[0]) {
                // tokenising bracket symbols
                case '(':
                case ')':
                    return nextItem(1, Precedence.BRACKETS);
                // tokenising single character connective symbols
                case '|':
                    return nextItem(1, Precedence.OR);
                case '!':
                    return nextItem(1, Precedence.NOT);
                case '&':
                    return nextItem(1, Precedence.AND);
                // tokenising iff connective "<=>"
                case '<':
                    if (formula.length > 2 && (formula[1] == '=' && formula[2] == '>')) {
                        return nextItem(3, Precedence.IFF);
                    }
                    System.out.println("There was an error in your input. Please try again.");
                    tokens.clearTokens();
                    return "";
                // tokenising implies connective "=>"
                case '=':
                    if (formula.length > 1 && formula[1] == '>') {
                        return nextItem(2, Precedence.IMPLY);
                    }
                    System.out.println("There was an error in your input. Please try again.");
                    tokens.clearTokens();
                    return "";
                // removing whitespace characters
                case ' ':
                case '\n':
                case '\t':
                    this.shiftChar(1);
                    continue;
                // End of input string
                case '.':
                    tokens.makeToken(")", Precedence.BRACKETS);
                    System.out.println("Scanning process completed successfully.");
                    return "";
                // tokenising predicate symbols
                default:
                    if (formula.length > 3 && (this.isNumber(formula[3]) && this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        System.out.println("There was an error in your input. Please try again.");
                        tokens.clearTokens();
                        return "";
                    } else if ((formula.length > 2) && (this.isNumber(formula[1]) && this.isNumber(formula[2]))) {
                        return nextItem(3, Precedence.PREDICATE);
                    } else if (formula.length > 1 && this.isNumber(formula[1])) {
                        return nextItem(2, Precedence.PREDICATE);
                    } else {
                        return nextItem(1, Precedence.PREDICATE);
                    }
            }
        }
        return "";
    }

    /**
     * This method is used to drive the scanner from start to finish, until the
     * entire input string has been processed.
     * 
     * @return ArrayList is an ArrayList containing the tokens
     */
    public ArrayList<Token> getTokens() {
        String s = "";
        do {
            s = this.getCharState();
        } while (s.length() != 0);
        return tokens.getTokens();
    }

    /**
     * This method can be used to set the input to the scanner
     * 
     * @param s
     *            String is the input string for scanning
     */
    public void setInput(String s) {
        this.formula = s.toCharArray();
    }

    public static void main(String[] args) {
        InputReader ir = new InputReader();
        System.out.println();
        Scanner sc = new Scanner(ir.getInput());
        @SuppressWarnings("unused")
        ArrayList l = sc.getTokens();
    }
}
