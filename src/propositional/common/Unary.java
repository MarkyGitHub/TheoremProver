package propositional.common;

import propositional.scanner.Token;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Unary class is the representation of Unary formulas, made up of a unary
 * connective and propositional symbol. It extends the abstract class Formula.
 */
public class Unary extends Formula {
    /** Is the unary connective */
    private Token unaryToken;
    /** The propositional symbol, or formula */
    private Formula predicate;

    /**
     * Initialising the unary formula
     * 
     * @param Token
     *            is the token representing the unary connective
     * @param Formula
     *            is the right predicate or formula
     */
    public Unary(Token token, Formula formula) {
        this.unaryToken = token;
        this.predicate = formula;
    }

    /**
     * Is used to get the connective symbol of this unary formula
     * 
     * @return Token is the connective symbol
     */
    @Override
    public Token getToken() {
        return this.unaryToken;
    }

    /**
     * This method can be used to get the unary formula
     * 
     * @return Formula is this unary formula
     */
    @Override
    public Formula getFormula() {
        return this;
    }

    /**
     * A method that allows to get the formula that is the left side of a
     * connective
     * 
     * @return Formula is null in the unary formula
     */
    @Override
    public Formula getleftPredicate() {
        return null;
    }

    /**
     * A method that allows to get the formula that is the right side of a
     * connective
     * 
     * @return Formula is the right formula being returned by this method
     */
    @Override
    public Formula getRightPredicate() {
        return this.predicate;
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    @Override
    public String toString() {
        return this.unaryToken.toString() + " " + this.predicate.toString();
    }

    /**
     * Can be used to clone the formula
     * 
     * @return Formula is this cloned formula
     */
    @Override
    public Formula cloneFormula() {
        return new Unary(new Token(this.unaryToken), this.predicate);
    }
}
