package predicate.parser;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import predicate.common.Binary;
import predicate.common.Formula;
import predicate.common.Function;
import predicate.common.Predicate;
import predicate.common.Quantifier;
import predicate.common.Term;
import predicate.common.Token;
import predicate.common.Unary;
import predicate.common.Variable;
import predicate.common.WFExpression;
import predicate.scanner.Scanner;
import predicate.scanner.SyntaxAnalyser;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Parser class is used to parse a sequence of input tokens into an abstract
 * representation of the predicate logic sentence, which the tokens represent.
 ******************************************************************************/
public class Parser {
    /** The iterator is used to iterate the sequence of tokens* */
    private Iterator it;
    /***************************************************************************
     * This is the abstract formula created by the parser and returned as output
     * by the parser
     **************************************************************************/
    private WFExpression syntax = null;
    /** The sequence of input tokens for parsing* */
    private ArrayList tokens;
    /** A stack is used to parse all terms */
    private TermStack terms = new TermStack();
    /** The stack is used to parse and store formulas */
    private FormulaStack formulas = new FormulaStack();
    /** Is used as an intermediary function stack */
    private FunctionStack functions = new FunctionStack();
    /** This stack is used to parse all predicate symbols */
    private PredicateStack predicates = new PredicateStack();
    /** Another stack is used to parse all the logical symbols* */
    private ConnectiveStack operators = new ConnectiveStack();
    /** Is used to allocate free variables and parameters during parsing */
    private static FreeTermsTable variableTable = new FreeTermsTable();
    /** Counts the number of quantifiers during parsing */
    private int quantifiers = 0;
    /** A list of all variables */
    private ArrayList vartokens = new ArrayList();

    /**
     * Initialising all instanse variables
     * 
     * @param inputTokens
     *            ArrayList is the sequence of tokens to parse
     */
    public Parser(ArrayList inputTokens) {
        this.tokens = inputTokens;
        it = this.tokens.iterator();
    }

    /**
     * This method reduces the parsed formulas and connectives and creates the
     * final abstract formula for output
     * 
     * @return boolean: true if this method is successfull, false indicates an
     *         incorrect input syntax
     */
    private boolean reduce() {
        if (operators.isEmpty()) {
            if (predicates.isEmpty() && formulas.isEmpty()) {
                if (functions.isEmpty()) {
                    Token n = terms.popTerm();
                    Term t = new Term(n, null);
                    if (t instanceof Variable) {
                        variableTable.addVariable(t);
                    }
                    formulas.pushFormula(t);
                    return true;
                } else if (terms.isEmpty()) {
                    formulas.pushFormula(functions.popFunction());
                }
            } else {
                return false;
            }
        } else {
            while (!operators.isEmpty()) {
                Token op = operators.popOp();
                if (op.isUnary()) {
                    if (tokens.isEmpty()) {
                        return false;
                    } else {
                        // parsing unary formula
                        Formula f = (Formula) formulas.popFormula();
                        Unary u = new Unary(op, f);
                        formulas.pushFormula(u);
                    }
                } else if (op.isBinary()) {
                    if (formulas.isEmpty()) {
                        return false;
                    } else {
                        // parsing binary formula
                        Formula f2 = (Formula) formulas.popFormula();
                        Formula f1 = (Formula) formulas.popFormula();
                        Binary b = new Binary(op, f1, f2);
                        formulas.pushFormula(b);
                    }
                }
            }
            if (formulas.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * The main accept method in this parser, it accepts the series of tokens in
     * a while loop one after the other
     * 
     * @return WFExpression is the parsed abstract formula
     */
    public WFExpression accept() {
        boolean var = false;
        while (it.hasNext()) {
            Token t = (Token) it.next();
            // accept token if token is left-parenthesis
            if (t.isBracket() && t.getData().compareTo("(") == 0) {
                operators.pushOp(t);
            }
            // accept token if token is variable symbol
            else if (t.isVariable() || t.isConstant()) {
                if (var) {
                    this.vartokens.add(t);
                    var = false;
                }
                terms.pushTerm(t);
            }
            // accept token if token is constant symbol
            else if (t.isFunction()) {
                terms.pushTerm(t);
                acceptFunction();
            }
            // accept token if token is predicate symbol
            else if (t.isPredicate()) {
                this.predicates.pushPredicate(t);
            }
            // accept token if it is a connective symbol
            else if (t.isBinary() || t.isUnary()) {
                acceptConnective(t);
            }
            // accept token if it is a quantifier symbol
            else if (t.isQuantifier()) {
                this.operators.pushOp(t);
                var = true;
                ++this.quantifiers;
            }
            // accept sub-expression if token is right-parenthesis
            else if (t.isBracket() && t.getData().compareTo(")") == 0) {
                acceptFormula();
            } else {
                return syntax;
            }
        }
        /** Reduce all items in the stacks and return the abstract formula* */
        if (reduce()) {
            syntax = formulas.popFormula();
            System.out.println("Parsing completed successfully.");
        }
        return syntax;
    }

    /**
     * This method accepts a sub-expression whenever the right parenthesis is
     * parsed. This helps to parse the preceding tokens in the stack upto this
     * enclosing bracket.
     */
    private void acceptFormula() {
        Token op = operators.peekOp();
        if (op.isBracket() && op.getData().compareTo("(") == 0) {
            operators.popOp();
            if (!operators.isEmpty()) {
                Token nextOp = operators.peekOp();
                if (!nextOp.isQuantifier()) {
                    // parsing predicate formula
                    if (!predicates.isEmpty() && !terms.isEmpty()) {
                        Term temp = null;
                        Token token;
                        while (!terms.isEmpty()) {
                            token = terms.popTerm();
                            if (token == null) {
                                Term term = functions.popFunction();
                                term.setRightExpression(temp);
                                temp = term;
                            } else {
                                temp = new Term(token, temp);
                                if (temp instanceof Variable) {
                                    variableTable.addVariable(temp);
                                }
                            }
                        }
                        token = predicates.popPredicate();
                        Predicate f = new Predicate(token, temp);
                        formulas.pushFormula(f);
                        return;
                    } else {
                        return;
                    }
                } else if (nextOp.isQuantifier()) {
                    while (this.quantifiers != 0) {
                        if (!predicates.isEmpty() && !terms.isEmpty()) {
                            Term temp = null;
                            Token token;
                            boolean free = true;
                            Token t = new Token();
                            while (terms.size() != this.quantifiers) {
                                token = terms.popTerm();
                                if (token == null) {
                                    Term tr1 = functions.popFunction();
                                    tr1.setRightExpression(temp);
                                    temp = tr1;
                                } else {
                                    for (int i = 0; i < this.vartokens.size(); i++) {
                                        Token t1 = (Token) this.vartokens.get(i);
                                        if (token.compareTo(t1.getData()) == 0) {
                                            t = null;
                                        }
                                    }
                                    if (t == null) {
                                        Variable v = new Variable(token, temp, false);
                                        temp = v;
                                        t = new Token();
                                        variableTable.addVariable(temp);
                                    } else {
                                        Term v = new Term(token, temp);
                                        temp = v;
                                    }
                                }
                            }
                            token = predicates.popPredicate();
                            Predicate f = new Predicate(token, temp);
                            token = operators.popOp();
                            Token n = terms.popTerm();
                            Term tr = new Term(n, null);
                            tr.bind();
                            variableTable.addVariable(tr);
                            Quantifier q = new Quantifier(token, tr, f);
                            formulas.pushFormula(q);
                            --this.quantifiers;
                        } else {
                            Formula f = (Formula) formulas.popFormula();
                            Token token = operators.popOp();
                            Token n = terms.popTerm();
                            Term tr = new Term(n, null);
                            tr.bind();
                            variableTable.addVariable(tr);
                            Quantifier q = new Quantifier(token, tr, f);
                            formulas.pushFormula(q);
                            --this.quantifiers;
                        }
                    }
                    return;
                }
            } else if (op.isBinary() || op.isUnary()) {
                op = operators.popOp();
                if (predicates.isEmpty()) {
                    if (op.getType() == 11) {
                        // parsing unary formula
                        Formula f = (Formula) formulas.popFormula();
                        Unary u = new Unary(op, f);
                        formulas.pushFormula(u);
                        return;
                    } else {
                        // parsing binary formula
                        Formula f2 = (Formula) formulas.popFormula();
                        Formula f1 = (Formula) formulas.popFormula();
                        Binary b = new Binary(op, f1, f2);
                        formulas.pushFormula(b);
                        return;
                    }
                } else {
                    System.out.println("Error in syntax - not a first-order logic sentence");
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    /**
     * The method is called to check if there are any preceding items left in
     * the stacks that can be parsed together into a formula
     * 
     * @param token
     *            Token is the connective symbol to be parsed
     */
    private void acceptConnective(Token token) {
        Token op = operators.peekOp();
        if (op.isBracket() && op.getData().compareTo("(") == 0) {
            operators.pushOp(token);
            return;
        } else if (token.isBinary()) {
            op = operators.popOp();
            operators.pushOp(token);
            if (predicates.isEmpty()) {
                if (op.isUnary()) {
                    // parsing unary formula
                    Formula f = (Formula) formulas.popFormula();
                    Unary u = new Unary(op, f);
                    formulas.pushFormula(u);
                    return;
                } else {
                    // parsing binary formula
                    Formula f2 = (Formula) formulas.popFormula();
                    Formula f1 = (Formula) formulas.popFormula();
                    Binary b = new Binary(op, f1, f2);
                    formulas.pushFormula(b);
                    return;
                }
            } else {
                System.out.println("Error in syntax - not a first-order logic sentence");
                return;
            }
        } else if (token.isUnary()) {
            operators.pushOp(token);
        }
    }

    /**
     * This method is used to recursively parse a function and its arguments
     */
    private void acceptFunction() {
        int count = 1;
        while (it.hasNext() && (operators.size() != 2) && count != 0) {
            Token token = (Token) it.next();
            if (token.isBracket() && token.getData().compareTo("(") == 0) {
                operators.pushOp(token);
            } else if (token.isVariable() || token.isConstant()) {
                terms.pushTerm(token);
            } else if ((token.isBracket() && token.getData().compareTo(")") == 0)) {
                count = count - 1;
                boolean free = true;
                Token op = operators.popOp();
                if (op.getData().compareTo("(") == 0) {
                    op = operators.peekOp();
                    if (op.getData().compareTo("(") == 0) {
                        token = terms.popTerm();
                        Token t = new Token();
                        Term temp = null;
                        while (token == null || (!token.isFunction())) {
                            if (token == null) {
                                Term tr1 = functions.popFunction();
                                tr1.setRightExpression(temp);
                                temp = tr1;
                            } else if (!token.isFunction()) {
                                for (int i = 0; i < this.vartokens.size(); i++) {
                                    Token t1 = (Token) this.vartokens.get(i);
                                    if (token.compareTo(t1.getData()) == 0) {
                                        t = null;
                                    }
                                }
                                if (t == null) {
                                    Variable v = new Variable(token, temp, false);
                                    temp = v;
                                    t = new Token();
                                    free = false;
                                    variableTable.addVariable(temp);
                                } else {
                                    Term v = new Term(token, temp);
                                    temp = v;
                                }
                            }
                            token = terms.popTerm();
                        }
                        Token n = token;
                        Term tr = new Function(n, null, temp);
                        if (!free) {
                            tr.bind();
                            free = true;
                        }
                        functions.pushFunction(tr);
                        n = null;
                        terms.pushTerm(n);
                    }
                }
            } else if (token.isFunction()) {
                terms.pushTerm(token);
                acceptFunction();
            }
        }
    }

    /**
     * This method is used to create a reference table for all the free and
     * bound variables in the sentence during parsing
     * 
     * @return FreeTermsTable is the variable table
     */
    public FreeTermsTable getReferenceTable() {
        this.variableTable.initialisePars();
        this.variableTable.initialiseVars();
        return this.variableTable;
    }

    public static void main(String[] args) {
        InputReader ir = new InputReader();
        String s = ir.getInput();
        Scanner sc = new Scanner(s);
        System.out.println(s);
        ArrayList l = sc.getScannedTokens();
        System.out.println(l);
        SyntaxAnalyser sa = new SyntaxAnalyser(l);
        if (sa.getValidatedTokens()) {
            System.out.println(l);
            if (l != null) {
                Parser sm = new Parser(l);
                WFExpression f = sm.accept();
                System.out.println(f);
            }
        }
    }
}
