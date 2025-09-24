package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Term class extends WFExpression. It represents a term and can be used to
 * create instances of constants in particular. This class is extended by
 * Functions and Variables.
 ******************************************************************************/
public class Term extends WFExpression {
    /** Is the symbol that is the term* */
    protected Token symbol;
    /** The expression to the right of this termin */
    protected WFExpression rightExpression;

    /**
     * Initialise instance variables
     * 
     * @param Token
     *            is the token that represent the term
     * @param expression
     *            WFExpression is the next expression in the predicate logic
     *            sentence, following this term
     */
    public Term(Token token, WFExpression expression) {
        this.symbol = token;
        this.rightExpression = expression;
    }

    /**
     * Is used to get the symbol representing the term
     * 
     * @return Token is the connective symbol
     */
    public Token getToken() {
        return this.symbol;
    }

    /**
     * This method returns a refernce to the term
     * 
     * @return Term is a reference to this term
     */
    public Term getTerm() {
        return this;
    }

    /**
     * The method is used to get the right expression of this term
     * 
     * @return WFExpression is the right expression
     */
    public WFExpression getRightExpression() {
        return this.rightExpression;
    }

    /**
     * This method is used to set the right expression of the term
     * 
     * @param expression
     *            WFExpression is the new value of the right expression
     */
    public void setRightExpression(WFExpression expression) {
        this.rightExpression = expression;
    }

    /**
     * This method is used to get the term without the right expression
     * 
     * @return WFExpression is the term without the right expression
     */
    public WFExpression getPartExpression() {
        return new Term(this.symbol, null);
    }

    /**
     * Is used to determine if the term is free
     * 
     * @return boolean: any term other than a variable or function is defined as
     *         free
     */
    public boolean isFree() {
        return true;
    }

    /** Method not implemented in this class */
    public void bind() {
    }

    /**
     * The method is used to compare the symbols of this term with the input
     * term
     * 
     * @param Term
     *            is the term to be compared with this term
     * @return boolean: true if both terms are the same symbol, false otherwise
     */
    public boolean equals(Term term) {
        if (term.getToken().compareTo(symbol.getData()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Can be used to clone the term
     * 
     * @return WFExpression is this cloned term
     */
    public WFExpression cloneExpression() {
        return new Term(this.symbol, this.rightExpression);
    }

    /**
     * The method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    public String toString() {
        String s;
        if (this.rightExpression == null) {
            s = "";
        } else {
            s = this.rightExpression.toString();
        }
        return new String(this.symbol + " " + s);
    }
}
