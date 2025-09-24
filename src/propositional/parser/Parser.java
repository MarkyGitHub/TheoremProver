package propositional.parser;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import propositional.common.Binary;
import propositional.common.Formula;
import propositional.common.Propositional;
import propositional.common.Unary;
import propositional.scanner.Scanner;
import propositional.scanner.Token;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Parser is used to parse a sequence of input tokens into a propositional
 * logic formula.
 ******************************************************************************/
public class Parser {
    /** The iterator is used to iterate the sequence of tokens* */
    private Iterator it;
    /***************************************************************************
     * This is the abstract formula created by the parser and returned as output
     * by the parser
     **************************************************************************/
    private Formula syntax;
    /** The sequence of input tokens for parsing* */
    private ArrayList<Token> tokens;
    /** A stack is used to parse all propositional symbols* */
    private FormulaStack formulas;
    /** Another stack is used to parse all the logical symbols* */
    private ConnectiveStack operators;

    /**
     * Initialisation of the istance variables
     * 
     * @param inputTokens
     *            ArrayList is the sequence of tokens to be parsed
     */
    public Parser(ArrayList<Token> inputTokens) {
        syntax = null;
        tokens = inputTokens;
        it = tokens.iterator();
        formulas = new FormulaStack();
        operators = new ConnectiveStack();
    }

    /**
     * This method reduces the parsed formulas and connectives and creates the
     * final abstract formula for output
     * 
     * @return boolean: true if this method is successfull, false indicates an
     *         incorrect input syntax
     */
    private boolean reduce() {
        while (!operators.isEmpty()) {
            Token op = operators.popOp();
            if (op.isUnary()) {
                if (tokens.isEmpty()) {
                    System.out.println("Error in syntax - 1");
                    return false;
                }
                // parsing unary formula
                Formula f = formulas.popFormula();
                Unary u = new Unary(op, f);
                formulas.pushFormula(u);
            } else if (op.isBinary()) {
                if (formulas.isEmpty()) {
                    System.out.println("Error in syntax - 1");
                    return false;
                }
                if (formulas.size() > 1) {
                    // parsing binary formula
                    Formula f2 = formulas.popFormula();
                    Formula f1 = formulas.popFormula();
                    Binary b = new Binary(op, f1, f2);
                    formulas.pushFormula(b);
                } else {
                    System.out.println("Error in syntax - 3");
                    return false;
                }
            }
        }
        return ((formulas.size() == 1));
    }

    /**
     * The main accept method in this parser, it accepts the series of tokens in
     * a while loop one after the other
     * 
     * @return Formula is the parsed abstract formula
     */
    private Formula accept() {
        boolean noError = true;
        while (it.hasNext() && noError) {
            Token t = (Token) it.next();
            // accept token if token is left-parenthesis
            if (t.getData().compareTo("(") == 0) {
                operators.pushOp(t);
            }
            // accept token if token is predicate
            else if (t.isPredicate()) {
                this.formulas.pushFormula(new Propositional(t));
            }
            // accept tokenif token is connective accept connective
            else if (t.isConnective()) {
                operators.pushOp(t);
            }
            // accept sub-expression if token is right-parenthesis
            else if (t.getData().compareTo(")") == 0) {
                if (!acceptFormula()) {
                    noError = false;
                }
            } else {
                System.out.println("Error in syntax - 4");
                return syntax;
            }
        }
        /***********************************************************************
         * Reduce all items in the stacks and return the abstract formula if
         * there are no errors in the parsing
         **********************************************************************/
        if (noError) {
            if (reduce()) {
                syntax = formulas.popFormula();
            }
        }
        return syntax;
    }

    /**
     * This method accepts a sub-expression whenever the right parenthesis is
     * parsed. This helps to parse the preceding tokens in the stack upto this
     * enclosing bracket.
     * 
     * @return boolean is true when there is no error in the syntax, false
     *         otherwise
     */
    private boolean acceptFormula() {
        Token op = operators.peekOp();
        while (!op.isBracket()) {
            if (!formulas.isEmpty()) {
                op = operators.popOp();
                if (op.isUnary()) {
                    // parsing unary formula
                    Formula f = formulas.popFormula();
                    Unary u = new Unary(op, f);
                    formulas.pushFormula(u);
                } else {
                    if (formulas.size() > 1) {
                        // parsing binary formula
                        Formula f2 = formulas.popFormula();
                        Formula f1 = formulas.popFormula();
                        Binary b = new Binary(op, f1, f2);
                        formulas.pushFormula(b);
                    } else {
                        System.out.println("Error in syntax - 5");
                        return false;
                    }
                }
            } else {
                System.out.println("Error in syntax - 6");
                return false;
            }
            op = operators.peekOp();
        }
        return true;
    }

    /**
     * This method parses the input tokens into an abstract formula by calling
     * the method accept
     * 
     * @return Formula is the abstract formula parsed by this class
     */
    public Formula parse() {
        if (this.tokens != null) {
            syntax = this.accept();
            if (syntax != null) {
                System.out.println("Parsing completed successfully.");
                return this.syntax;
            }
            System.out.println("Parsing of input string was unsuccessfully.");
            return null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        InputReader ir = new InputReader();
        String s = ir.getInput();
        Scanner sc = new Scanner(s);
        System.out.println(s);
        sc.setInput(s);
        ArrayList l = sc.getTokens();
        System.out.println(l);
        if (l != null) {
            Parser sm = new Parser(l);
            Formula f = sm.parse();
            System.out.println(f);
        }
    }
}
