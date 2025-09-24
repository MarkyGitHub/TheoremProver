package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Function class extends Term to represent a function. It inherits the
 * methods and data fields from the Term class. It has two attributes, arities
 * and free, and a set of methods that allow to manipulate the data members of
 * this class.
 ******************************************************************************/
public class Function extends Term {
    /** The terms that are the arguments of the function */
    private Term arities;
    /**
     * This instance field indicates whether the function contains any variables
     * that are not free
     */
    private boolean free = true;

    /**
     * Initialise instance variables
     * 
     * @param Token
     *            is the token representing the function symbol
     * @param WFExpression
     *            is the next expression in the predicate sentence
     * @param term
     *            Term is the arguments of the function
     */
    public Function(Token token, WFExpression expression, Term term) {
        super(token, expression);
        this.arities = term;
    }

    /**
     * Is used to determine if this function contains any bound variables
     * 
     * @return boolean: true if the function has bound variables, false
     *         otherwise
     */
    public boolean isFree() {
        return this.free;
    }

    /** Can be used to reset the value of free */
    public void bind() {
        this.free = false;
    }

    /**
     * Is used to get the term that is the arguments of this function
     * 
     * @return Term is the argument
     */
    public Term getArguments() {
        return this.arities;
    }

    /**
     * This method is used to get the function's right expression
     * 
     * @return WFExpression is the right expression
     */
    public WFExpression getRightExpression() {
        return super.rightExpression;
    }

    /**
     * This method is used to set the right expression of the function
     * 
     * @param expression
     *            Term is the new value for the right expression
     */
    public void setRightExpression(WFExpression expression) {
        super.rightExpression = expression;
    }

    /**
     * This method is used to get the function without its arities and right
     * expression
     * 
     * @return WFExpression is the function by itself
     */
    public WFExpression getPartExpression() {
        return new Function(this.symbol, null, null);
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    public String toString() {
        String s;
        if (super.rightExpression == null) {
            s = "";
        } else {
            s = super.rightExpression.toString();
        }
        return new String(this.symbol + "" + this.arities + " " + s);
    }
}
