package propositional.common;

import propositional.scanner.Token;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Predicate class is the representation of Predicate formulas, made up of a
 * propositional symbol. It extends the abstract class Formula.
 */
public class Propositional extends Formula {
    /** The propositional symbol */
    private Token symbol;

    /**
     * Initialising the predicate
     * 
     * @param Token
     *            is the token representing the propositional symbol
     */
    public Propositional(Token token) {
        symbol = token;
    }

    /**
     * Is used to get the propositional symbol of this predicate formula
     * 
     * @return Token is the connective symbol
     */
    @Override
    public Token getToken() {
        return this.symbol;
    }

    /**
     * This method can be used to get the propositional symbol
     * 
     * @return Formula is this predicate formula
     */
    @Override
    public Formula getFormula() {
        return this;
    }

    /**
     * A method that allows to get the formula that is the left side of a
     * connective
     * 
     * @return Formula is null in the predicate
     */
    @Override
    public Formula getleftPredicate() {
        return null;
    }

    /**
     * A method that allows to get the formula that is the right side of a
     * connective
     * 
     * @return Formula is null in the predicate
     */
    @Override
    public Formula getRightPredicate() {
        return null;
    }

    /**
     * This method is used to display the contents of the formula as a string
     * 
     * @return String is the string representing the formula
     */
    @Override
    public String toString() {
        return this.symbol.toString();
    }

    /**
     * Can be used to clone the formula
     * 
     * @return Formula is this cloned formula
     */
    @Override
    public Formula cloneFormula() {
        return new Propositional(new Token(this.symbol));
    }
}
