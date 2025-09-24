package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Predicate Formula extends Formula to represents a predicate formula. It
 * has a token representing the symbol and a right expression that are the
 * arguments belonging to the predicate. Various methods allow manipulating the
 * formula.
 ******************************************************************************/
public class Predicate extends Formula {
    /** Is the predicate symbol* */
    private Token symbol;
    /** The right predicate symbols, or formulas */
    private Term rightExpression;

    /**
     * Initialise instance variables
     * 
     * @param Token
     *            is the token representing the predicate symbol
     * @param term
     *            Term is the right expression which are the arguments of this
     *            predicate
     */
    public Predicate(Token token, Term term) {
        symbol = token;
        this.rightExpression = term;
    }

    /**
     * Is used to get the predicate symbol of this binary formula
     * 
     * @return Token is the predicate symbol
     */
    public Token getToken() {
        return this.symbol;
    }

    /**
     * This method can be used to get a refernce to this formula
     * 
     * @return Formula is a reference to this predicate formula
     */
    public Formula getFormula() {
        return this;
    }

    /**
     * This method is not implemented in this class
     * 
     * @return Formula is null
     */
    public Formula getleftPredicate() {
        return null;
    }

    /**
     * This method is not implemented in this class
     * 
     * @return Formula is null
     */
    public Formula getRightPredicate() {
        return null;
    }

    /**
     * This method is used to get the arguments of the predicate that is the
     * right expression
     * 
     * @return WFExpression is the right expression
     */
    public WFExpression getRightExpression() {
        return this.rightExpression;
    }

    /**
     * This method can be used to set the right expression of the predicate
     * 
     * @param expression
     *            Term is the new value for the right expression
     */
    public void setRightExpression(Term expression) {
        this.rightExpression = expression;
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
        this.rightExpression = (Term) e;
    }

    /**
     * This method always returns true, as a predicate is a literal
     * 
     * @return boolean is true
     */
    public boolean isLiteral() {
        return true;
    }

    /**
     * Can be used to clone this predicate formula
     * 
     * @return WFExpression is this cloned formula
     */
    public WFExpression cloneExpression() {
        return new Predicate(this.symbol, this.rightExpression);
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    public String toString() {
        return new String(this.symbol + " " + this.rightExpression);
    }
}
