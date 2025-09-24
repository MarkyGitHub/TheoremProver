package predicate.parser;

import java.util.Stack;
import predicate.common.Term;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The FunctionStack class extends the Stack class and is used to represent a
 * function stack. It has a set of methods to push and pop Tokens in and out of
 * the stack
 ******************************************************************************/
public class FunctionStack extends Stack {
    /** Initialisation of function stack */
    public FunctionStack() {
        super();
    }

    /**
     * This method is used to push functions into the stack
     * 
     * @param function
     *            Term is the item to be placed in the stack
     */
    public void pushFunction(Term function) {
        super.push(function);
    }

    /**
     * This method is used to pop a function from the stack
     * 
     * @return Term is the item being popped
     */
    public Term popFunction() {
        return (Term) super.pop();
    }

    /**
     * This method can be used to peek the first function in the stack
     * 
     * @return Term is the item being peeked
     */
    public Term peekFunction() {
        return (Term) super.peek();
    }
}
