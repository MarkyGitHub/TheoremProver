package predicate.parser;

import java.util.Stack;
import predicate.common.Token;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The PredicateStack class extends a Stack class and is used to create a stack
 * for predicates. It has pop and push methods to allow adding and removing
 * predicates from the stack.
 */
public class PredicateStack extends Stack {
    /** Initialisation of predicate stack */
    public PredicateStack() {
        super();
    }

    /**
     * This method is used to push predicatess into the stack
     * 
     * @param predicate
     *            Token is the item to be placed in the stack
     */
    public void pushPredicate(Token predicate) {
        super.push(predicate);
    }

    /**
     * This method is used to pop a predicate from the stack
     * 
     * @return Token is the item being popped
     */
    public Token popPredicate() {
        return (Token) super.pop();
    }

    /**
     * This method can be used to peek the first predicate in the stack
     * 
     * @return Token is the predicate being peeked
     */
    public Token peekPredicate() {
        return (Token) super.peek();
    }
}
