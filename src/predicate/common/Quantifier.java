package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Quantifier Formula extends Formula to represents a quantified formula. It
 * has a token representing the symbol, a variable that it quantifies and a
 * right expression that are the arguments being quantified. Various methods
 * allow manipulating the formula.
 ******************************************************************************/
public class Quantifier extends Formula {
    /** Is the quantifier symbol */
    private Token symbol;
    /** The variable bound to the quantifier */
    private Term variable;
    /** The expression that is quantified by this quantifier */
    private Formula rightFormula;

    /**
     * Initialise instance variables
     * 
     * @param Token
     *            is the token representing the quantifier
     * @param var
     *            Term is the variable bound to the quantifier
     * @param Formula
     *            is the formula corresponding to the right expression being
     *            quantified
     */
    public Quantifier(Token token, Term var, Formula formula) {
        this.symbol = token;
        this.variable = var;
        this.rightFormula = formula;
    }

    /**
     * This method gets the variable bound to the quantifier
     * 
     * @return Term is the variable bound to the quantifier
     */
    public Term getVariable() {
        return this.variable;
    }

    /**
     * Is used to get the quantifier symbol
     * 
     * @return Token is the quantifier symbol
     */
    public Token getToken() {
        return this.symbol;
    }

    /**
     * This method can be used to get a reference to this formula
     * 
     * @return Formula is this quantifier formula
     */
    public Formula getFormula() {
        return this;
    }

    /**
     * This method only returns the quantifier formulaand its variable without
     * the right quantified formula
     * 
     * @return WFExpression is the quantifier without the formula
     */
    public WFExpression getPartExpression() {
        return new Quantifier(symbol, variable, null);
    }

    /**
     * Method not implemented in this class
     * 
     * @return Formula is null
     */
    public Formula getleftPredicate() {
        return null;
    }

    /**
     * A method that allows getting the formula that is quantified
     * 
     * @return Formula is the right formula being returned
     */
    public Formula getRightPredicate() {
        return this.rightFormula;
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
    public void setRightExpression(WFExpression e) {
        this.rightFormula = (Formula) e;
    }

    /**
     * This method is not implemented in this class
     * 
     * @param e
     *            Term is not used
     */
    public void setRightExpression(Term e) {
        this.variable = e;
    }

    /**
     * This method always returns false as a quantifier formula is not a literal
     * 
     * @return boolean is false
     */
    public boolean isLiteral() {
        return false;
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    public String toString() {
        return new String(this.symbol + "" + this.variable + " " + this.rightFormula);
    }

    /**
     * Can be used to clone the formula
     * 
     * @return WFExpression is this cloned formula
     */
    public WFExpression cloneExpression() {
        return new Quantifier(this.symbol, this.variable, this.rightFormula);
    }
}
