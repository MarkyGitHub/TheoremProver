package predicate.parser;

import java.util.Stack;
import predicate.common.WFExpression;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The FormulaStack class extends the Stack class and is used to represent a
 * formula stack. It has a set of method to push and pop Tokens in and out of
 * the stack
 ******************************************************************************/
public class FormulaStack extends Stack {
    /** Initialisation of formula stack */
    public FormulaStack() {
        super();
    }

    /**
     * This method is used to push formulas into the stack
     * 
     * @param formula
     *            WFExpression is the item to be placed in the stack
     */
    public void pushFormula(WFExpression formula) {
        super.push(formula);
    }

    /**
     * This method is used to pop a formula from the stack
     * 
     * @return WFExpression is the item being popped
     */
    public WFExpression popFormula() {
        return ((WFExpression) super.pop());
    }

    /**
     * This method can be used to peek the first item in the stack
     * 
     * @return WFExpression is the item being peeked
     */
    public WFExpression peekFormula() {
        return ((WFExpression) super.peek());
    }

    /** This method can be used to clear the stack of its contents */
    public void clearStack() {
        super.clear();
    }
}
