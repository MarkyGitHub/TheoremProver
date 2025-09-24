package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The abstract class WFExpression represents an abstract well-formed expression
 * that can be extended to by terms and formulas. It declares a set of abstract
 * methods that the sub-classes must implement.
 ******************************************************************************/
public abstract class WFExpression {
    /** Is used to get the token representing the logical or non-logical symbol* */
    public abstract Token getToken();

    /***************************************************************************
     * Is used to get the part expression corresponding to the quantifier and
     * its variable
     **************************************************************************/
    public abstract WFExpression getPartExpression();

    /** Used to get the right expression of a formula or a term */
    public abstract WFExpression getRightExpression();

    /** Is used to set the right expression of a term or formula */
    public abstract void setRightExpression(WFExpression e);

    /** Is used to clone the expression* */
    public abstract WFExpression cloneExpression();
}
