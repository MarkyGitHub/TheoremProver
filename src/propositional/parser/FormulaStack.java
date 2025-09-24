package propositional.parser;

import java.util.Stack;
import propositional.common.Formula;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The FormulaStack is used to hold formulas during parsing of tokens. It is a
 * sub-class of the Stack class.
 ******************************************************************************/
public class FormulaStack extends Stack<Formula> {
    /** Initialisation of formula stack */
    public FormulaStack() {
        super();
    }

    /**
     * This method is used to push formulas into the stack
     * 
     * @param formula
     *            Formula is the item to be placed in the stack
     */
    public void pushFormula(Formula formula) {
        super.push(formula);
    }

    /**
     * This method is used to pop a formula from the stack
     * 
     * @return Formula is the item being popped
     */
    public Formula popFormula() {
        return super.pop();
    }

    /**
     * This method can be used to peek the first item in the stack
     * 
     * @return Formula is the item being peeked
     */
    public Formula peekFormula() {
        return super.peek();
    }

    /** This method can be used to clear the stack of its contents */
    public void clearStack() {
        super.clear();
    }
}
