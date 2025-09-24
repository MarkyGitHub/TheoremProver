package propositional.common;

import propositional.scanner.Token;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Formula class is an abstract class declares a set of methods that allow
 * subclasses to extend it. This allows to create a homogeneous data structure
 * of propositional formulas.
 */
public abstract class Formula implements Cloneable {
    /**
     * A method that will get the formula
     * 
     * @return Formula is the formula being returned by this method
     */
    public abstract Formula getFormula();

    /**
     * A method that will get the token associated with the propositional symbol
     * 
     * @return Token is the token being returned by this method
     */
    public abstract Token getToken();

    /**
     * A method that allows to get the formula that is the left side of a
     * connective
     * 
     * @return Formula is the left formula being returned by this method
     */
    public abstract Formula getleftPredicate();

    /**
     * A method that allows to get the formula that is the right side of a
     * connective
     * 
     * @return Formula is the right formula being returned by this method
     */
    public abstract Formula getRightPredicate();

    /**
     * Can be used to clone the formula
     * 
     * @return Formula is this cloned formula
     */
    public abstract Formula cloneFormula();
}
