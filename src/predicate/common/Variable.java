package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Variable class extends the concrete class Term. It represents a variable
 * that can be bound to a quantifier or free. The class has access to all the
 * data members of the Term class.
 ******************************************************************************/
public class Variable extends Term {
    /** This instance field indicates whether the variable is free or bound */
    private boolean free = true;

    /**
     * Initialise instance variables
     * 
     * @param Token
     *            is the token representing the variable
     * @param WFExpression
     *            is the next expression in the sentence
     * @param bind
     *            boolean: is used to set the variable to free property to true
     *            or false
     */
    public Variable(Token token, WFExpression expression, boolean bind) {
        super(token, expression);
        this.free = bind;
    }

    /**
     * Is used to determine if the variable is bound or free
     * 
     * @return boolean: true if the variable is free, false otherwise
     */
    public boolean isFree() {
        return this.free;
    }

    /** This method can be used to bind the variable* */
    public void bind() {
        this.free = false;
    }

    /**
     * This method is used to get the variable's right expression
     * 
     * @return WFExpression is the right expression after this variable
     */
    public WFExpression getRightExpression() {
        return super.rightExpression;
    }

    /**
     * This method return the variable only without the reference to the next
     * expression
     * 
     * @return WFExpression is the variable
     */
    public WFExpression getPartExpression() {
        return new Variable(this.symbol, null, this.free);
    }

    /**
     * This method is used to set the right expression of the variable
     * 
     * @param WFEpression
     *            is the new value of the right expression to set
     */
    public void setRightExpression(WFExpression e) {
        super.rightExpression = e;
    }

    /**
     * A method that returns true if the variable provided as an argument has
     * the same symbol as this variable
     * 
     * @param Variable
     *            is the Variable to be compared with this Variable
     * @return boolean: true if both Variable have the same symbol, false
     *         otherwise
     */
    public boolean equals(Variable var) {
        if (var.getToken().compareTo(symbol.getData()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to display the contents of the variable as a string
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
        return new String(this.symbol + " " + s);
    }
}
