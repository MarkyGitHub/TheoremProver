package predicate.scanner;

import inputoutput.InputReader;
import inputoutput.OutputWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import predicate.common.Token;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The SyntaxAnalyser class is used to check the syntax of a sequence of tokens
 * and see if there are any errors in there or not.
 ******************************************************************************/
public class SyntaxAnalyser {
    /** The sequence of tokens to be syntactically analysed* */
    private ArrayList inputTokens;
    /** The iterator is used to iterate the sequence of tokens* */
    private ListIterator it;
    /** A buffer that is used to match the brackets in the* */
    private char[] buffer;

    /**
     * Initialise instance variables
     * 
     * @param tokens
     *            ArrayList is the sequence of tokens to analyse
     */
    public SyntaxAnalyser(ArrayList tokens) {
        if (tokens != null) {
            inputTokens = tokens;
            buffer = new char[inputTokens.size()];
        }
    }

    /**
     * This method is used to check the syntax of brackets in the sentence
     * 
     * @return boolean: true if the bracktes match, false otherwise
     */
    private boolean matchBrackets() {
        ListIterator iter = inputTokens.listIterator();
        int index = 0;
        while (iter.hasNext()) {
            Token t = (Token) iter.next();
            if (t.isBracket()) {
                if (t.compareTo("(") == 0) {
                    buffer[index] = t.getData().charAt(0);
                    ++index;
                } else if (t.compareTo(")") == 0) {
                    if (index > 0) {
                        if (buffer[index - 1] == '(') {
                            --index;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        if (index != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is used to check the syntax of the input sequence
     * 
     * @return boolean: true indicates that the syntax is correct, false
     *         indicates that it is false
     */
    private boolean wellFormedExpression() {
        if (!inputTokens.isEmpty()) {
            it = inputTokens.listIterator();
            if (this.matchBrackets()) {
                while (it.hasNext()) {
                    Token t = (Token) it.next();
                    switch (t.getType()) {
                        case (1):
                            if (!this.validConstant()) {
                                OutputWriter.displayError("Error is processing constant. Please try again.e");
                                return false;
                            }
                            break;
                        case (2):
                            if (!this.validVariable()) {
                                OutputWriter.displayError("Error is processing variable. Please try again.b");
                                return false;
                            }
                            break;
                        case (3):
                        case (4):
                            if (!this.validQuantifier()) {
                                OutputWriter.displayError("Error is processing quantifier. Please try again.c");
                                return false;
                            }
                            break;
                        case (5):
                            if (!this.validPredicate()) {
                                OutputWriter.displayError("Error is processing predicate. Please try again.d");
                                return false;
                            }
                            break;
                        case (6):
                            break;
                        case (7):
                        case (8):
                        case (9):
                        case (10):
                        case (11):
                            if (!this.validConnective(t)) {
                                OutputWriter.displayError("Error is processing connective. Please try again.a");
                                return false;
                            }
                            break;
                        case (0):
                            if (!this.validFunction()) {
                                OutputWriter.displayError("Error is processing function. Please try again.f");
                                return false;
                            }
                            break;
                        default:
                            OutputWriter.displayError("Error in input. Please try again.g");
                            return false;
                    }
                }
            } else {
                OutputWriter.displayError("Error in input; missing bracket. Please try again.h");
                return false;
            }
        } else {
            OutputWriter.displayError("Unable to analyse an empty input.i");
            return false;
        }
        OutputWriter.displayMessage("Lexical analysis of symbols completed successfully.");
        return true;
    }

    /**
     * This method checks the syntax of a connective token
     * 
     * @param Token
     *            is the connective token for validation
     * @return boolean: true if syntax is correct, false otherwise
     */
    private boolean validConnective(Token token) {
        if (token.getType() == 11) {
            it.previous();
            if (it.hasPrevious()) {
                Token t = (Token) it.previous();
                if (t.isBracket() || t.isPredicate() || t.isBinary()) {
                    it.next();
                    if (it.hasNext()) {
                        it.next();
                        if (it.hasNext()) {
                            t = (Token) it.next();
                            if (t.isBracket() || t.isQuantifier() || t.isPredicate() || t.isUnary()) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            } else if (it.hasNext()) {
                it.next();
                if (it.hasNext()) {
                    Token t = (Token) it.next();
                    if (t.isBracket() || t.isQuantifier() || t.isPredicate() || t.isUnary()) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            it.previous();
            if (it.hasPrevious()) {
                Token t = (Token) it.previous();
                if (t.isBracket() || t.isPredicate()) {
                    it.next();
                    if (it.hasNext()) {
                        it.next();
                        if (it.hasNext()) {
                            t = (Token) it.next();
                            if (t.isBracket() || t.isQuantifier() || t.isPredicate() || t.isUnary()) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            } else if (it.hasNext()) {
                it.next();
                if (it.hasNext()) {
                    Token t = (Token) it.next();
                    if (t.isBracket() || t.isQuantifier() || t.isPredicate() || t.isUnary()) {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method checks the syntax of a quantifier token
     * 
     * @return boolean: true if the syntax is correct, false otherwise
     */
    private boolean validQuantifier() {
        it.previous();
        if (it.hasPrevious()) {
            Token t = (Token) it.previous();
            if (t.isBinary() || t.isBracket() || t.isVariable() || t.isUnary()) {
                it.next();
                if (it.hasNext()) {
                    it.next();
                    if (it.hasNext()) {
                        t = (Token) it.next();
                        if (t.isVariable()) {
                            return true;
                        }
                    }
                }
            }
        } else if (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                Token t = (Token) it.next();
                if (t.isVariable()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks the syntax of a predicate token
     * 
     * @return boolean: true if the syntax is correct, false otherwise
     */
    private boolean validPredicate() {
        it.previous();
        if (it.hasPrevious()) {
            Token t = (Token) it.previous();
            if (t.isBracket() || t.isBinary() || t.isVariable() || t.isUnary()) {
                it.next();
                if (it.hasNext()) {
                    it.next();
                    if (it.hasNext()) {
                        t = (Token) it.next();
                        if (t.isBracket()) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        } else if (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                Token t = (Token) it.next();
                if (t.isBracket()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks the syntax of a variable token
     * 
     * @return boolean: true if the syntax is correct, false otherwise
     */
    private boolean validVariable() {
        it.previous();
        if (it.hasPrevious()) {
            Token t = (Token) it.previous();
            if (t.isBracket() || t.isVariable() || t.isQuantifier() || t.isConstant()) {
                it.next();
                if (it.hasNext()) {
                    it.next();
                    if (it.hasNext()) {
                        t = (Token) it.next();
                        if (t.isConstant() || t.isVariable() || t.isBracket() || t.isPredicate() || t.isFunction() || t.isQuantifier()) {
                            return true;
                        }
                    }
                }
            }
        } else if (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                return false;
            }
        }
        return false;
    }

    /**
     * This method checks the syntax of a function token
     * 
     * @return boolean: true if the syntax is correct, false otherwise
     */
    private boolean validFunction() {
        it.previous();
        if (it.hasPrevious()) {
            Token t = (Token) it.previous();
            if (t.isBracket() || t.isVariable() || t.isConstant()) {
                it.next();
                if (it.hasNext()) {
                    it.next();
                    if (it.hasNext()) {
                        t = (Token) it.next();
                        if (t.isBracket()) {
                            return true;
                        }
                    }
                }
            }
        } else if (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                Token t = (Token) it.next();
                if (t.isBracket()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks the syntax of a constant token
     * 
     * @return boolean: true if the syntax is correct, false otherwise
     */
    private boolean validConstant() {
        it.previous();
        if (it.hasPrevious()) {
            Token t = (Token) it.previous();
            if (t.isBracket() || t.isVariable()) {
                it.next();
                if (it.hasNext()) {
                    it.next();
                    if (it.hasNext()) {
                        t = (Token) it.next();
                        if (t.isBracket() || t.isVariable() || t.isFunction()) {
                            return true;
                        }
                    }
                }
            }
        } else if (it.hasNext()) {
            it.next();
            if (it.hasNext()) {
                Token t = (Token) it.next();
                if (t.isVariable() || t.isFunction()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Thois method is used to validate the input sequence
     * 
     * @return ArrayList is the valid input sequence of tokens
     */
    public boolean getValidatedTokens() {
        if (this.wellFormedExpression()) {
            return true;
        } else {
            return false;
        }
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
            System.out.println(sa);
        }
    }
}
