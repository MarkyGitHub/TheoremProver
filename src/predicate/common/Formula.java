package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The abstract class Formula extends WFExpression. It represents a abstract
 * formula that can be extended by different binary, unary, quantifier and
 * predicate formulas. This allows to process any type of formula in a
 * homogeneous way by the program.
 */
public abstract class Formula extends WFExpression {
    /** A method that will get the formula */
    public abstract Formula getFormula();

    /**
     * A method that will allow to get the formula that is the left side of a
     * connective
     */
    public abstract Formula getleftPredicate();

    /**
     * A method that will allow to get the formula that is the right side of a
     * connective
     */
    public abstract Formula getRightPredicate();

    /** This method will allow getting the variable associated with a quantifier */
    public abstract Term getVariable();

    /**
     * This method is used to get the right expression of terms and predicates
     * 
     * @return WFExpression is the right expression
     */
    public abstract WFExpression getRightExpression();

    /**
     * This method is used to set the right expression of predicate and terms
     * 
     * @param expression
     *            Term is the new value of the right expression
     */
    public abstract void setRightExpression(Term expression);

    /** Is used to get the formula without the right expression or formula* */
    public abstract WFExpression getPartExpression();

    /**
     * This method is used to determine if the formula is a literal
     * 
     * @return boolean is true if formula is predicate or negation of a
     *         predicate, false otherwise
     */
    public abstract boolean isLiteral();
}
