package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Unary Formula extends Formula to represents a unary formula. It has a
 * token representing the unary symbol, and a right formula that it is connected
 * to. Various methods allow manipulating the formula.
 ******************************************************************************/
public class Unary extends Formula {
    /** Is the quantifier symbol* */
    private Token unaryToken;
    /** The formula that is the right part of the unary formula */
    private Formula formula;

    /**
     * Initialising the instance variables
     * 
     * @param Token
     *            is the token representing the unary connective symbol
     * @param Formula
     *            is the right formula that the connective is connected to
     */
    public Unary(Token token, Formula formula) {
        this.unaryToken = token;
        this.formula = formula;
    }

    /**
     * Is used to get the connective symbol of this formula
     * 
     * @return Token is the unary connective symbol
     */
    public Token getToken() {
        return this.unaryToken;
    }

    /**
     * This method can be used to get this entire unary formula
     * 
     * @return Formula is this unary formula
     */
    public Formula getFormula() {
        return this;
    }

    /**
     * A method that allows getting the formula that is the left side of a
     * connective
     * 
     * @return Formula is null as a unary formula does not have left connections
     */
    public Formula getleftPredicate() {
        return null;
    }

    /**
     * A method that allows to get the formula that is the right side of a
     * connective
     * 
     * @return Formula is the right formula being returned by this method
     */
    public Formula getRightPredicate() {
        return this.formula;
    }

    /**
     * This method gets the variable bound to the quantifier
     * 
     * @return Term is the variable bound to the quantifier
     */
    public Term getVariable() {
        return null;
    }

    /**
     * This method is not implemented in this class
     * 
     * @return WFExpression is null
     */
    public WFExpression getRightExpression() {
        return null;
    }

    /**
     * This method is not implemented in this class
     * 
     * @param e
     *            Term is not used
     */
    public void setRightExpression(Term e) {
    }

    /**
     * This method is not implemented in this class
     * 
     * @return WFExpression is null
     */
    public WFExpression getPartExpression() {
        return null;
    }

    /**
     * This method is not implemented in this class
     * 
     * @param e
     *            Term is not used
     */
    public void setRightExpression(WFExpression e) {
    }

    /**
     * This method is used to determine if an instance of this class is a
     * predicate or the negation of a predicate
     * 
     * @return boolean is true if the right formula is a predicate, false
     *         otherwise
     */
    public boolean isLiteral() {
        if (formula instanceof Predicate) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    public String toString() {
        return new String(this.unaryToken + " " + this.formula);
    }

    /**
     * Can be used to clone the formula
     * 
     * @return WFExpression is this cloned formula
     */
    public WFExpression cloneExpression() {
        return new Unary(this.unaryToken, this.formula);
    }
}
