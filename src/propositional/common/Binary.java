package propositional.common;

import propositional.scanner.Token;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Binary class is the representation of Binary formulas, made up of binary
 * connectives and propositional symbols. It extends the abstract class Formula.
 */
public class Binary extends Formula {
    /** Is the binary connective */
    private Token binaryToken;
    /** The right and left propositional symbols, or formulas */
    private Formula leftPredicate;
    private Formula rightPredicate;

    /**
     * Initialising the binary formula
     * 
     * @param token
     *            Token is the token corresponding to the binary connective
     * @param left
     *            Formula is the left part of the binary formula
     * @param right
     *            Formula is the right part of the binary formula
     */
    public Binary(Token token, Formula left, Formula right) {
        this.binaryToken = token;
        this.leftPredicate = left;
        this.rightPredicate = right;
    }

    /**
     * Is used to get the connective symbol of this binary formula
     * 
     * @return Token is the connective symbol
     */
    @Override
    public Token getToken() {
        return this.binaryToken;
    }

    /**
     * This method can be used to get the binary formula
     * 
     * @return Formula is this binary formula
     */
    @Override
    public Formula getFormula() {
        return this;
    }

    /**
     * A method that allows to get the formula that is the left side of a
     * connective
     * 
     * @return Formula is the left formula being returned by this method
     */
    @Override
    public Formula getleftPredicate() {
        return this.leftPredicate;
    }

    /**
     * A method that allows to get the formula that is the right side of a
     * connective
     * 
     * @return Formula is the right formula being returned by this method
     */
    @Override
    public Formula getRightPredicate() {
        return this.rightPredicate;
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    @Override
    public String toString() {
        return this.leftPredicate.toString() + " " + this.binaryToken.toString() + " " + this.rightPredicate.toString();
    }

    /**
     * Can be used to clone the formula
     * 
     * @return Formula is this cloned formula
     */
    @Override
    public Formula cloneFormula() {
        Token t = new Token();
        return new Binary(new Token(this.binaryToken), this.leftPredicate, this.rightPredicate);
    }
}
