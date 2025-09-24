package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Binary class is the representation of Binary formulas, made up of a
 * binary connective and two predicates. Various methods allow manipulating the
 * Binary formula. The Binary class extends abstract class Formula.
 ******************************************************************************/
public class Binary extends Formula {
    /** Is the binary connective */
    private Token binaryToken;
    /** The right and left predicate symbols, or formulas */
    private Formula leftPredicate;
    private Formula rightPredicate;

    /**
     * Initialising the binary formula
     * 
     * @param token
     *            Token is the binary connective
     * @param left
     *            WFExpression is the left predicate or formula
     * @param right
     *            WFExpression is ythe right predicate or formula
     */
    public Binary(Token token, WFExpression left, WFExpression right) {
        this.binaryToken = token;
        this.leftPredicate = (Formula) left;
        this.rightPredicate = (Formula) right;
    }

    /**
     * Is used to get the connective symbol of this binary formula
     * 
     * @return Token is the connective symbol
     */
    public Token getToken() {
        return this.binaryToken;
    }

    /**
     * This method can be used to get the binary formula
     * 
     * @return Formula is this binary formula
     */
    public Formula getFormula() {
        return this;
    }

    /**
     * A method that allows to get the formula that is the left side of a
     * connective
     * 
     * @return Formula is the left formula
     */
    public Formula getleftPredicate() {
        return this.leftPredicate;
    }

    /**
     * A method that allows to get the formula that is the right side of a
     * connective
     * 
     * @return Formula is the right formula
     */
    public Formula getRightPredicate() {
        return this.rightPredicate;
    }

    /**
     * This method is not implemented in this class
     * 
     * @return Term is null
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
     *            WFExpression is not used
     */
    public void setRightExpression(WFExpression e) {
    }

    /**
     * This method returns false as a binary formula is not a literal
     * 
     * @return boolean is false
     */
    public boolean isLiteral() {
        return false;
    }

    /**
     * Can be used to clone the formula
     * 
     * @return WFExpression is this cloned formula
     */
    public WFExpression cloneExpression() {
        return new Binary(this.binaryToken, this.leftPredicate, this.rightPredicate);
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    public String toString() {
        return new String(this.leftPredicate + " " + this.binaryToken + " " + this.rightPredicate);
    }
}
