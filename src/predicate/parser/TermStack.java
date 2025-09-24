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
 * The TermStack class extends the Stack class and represents a stack for terms.
 * It has methods to push and pop terms to and from the stack.
 ******************************************************************************/
public class TermStack extends Stack {
    /** Initialisation of term stack */
    public TermStack() {
        super();
    }

    /**
     * This method is used to push terms into the stack
     * 
     * @param term
     *            Token is the item to be placed in the stack
     */
    public void pushTerm(Token term) {
        super.push(term);
    }

    /**
     * This method is used to pop a terms from the stack
     * 
     * @return Token is the term being popped
     */
    public Token popTerm() {
        return (Token) super.pop();
    }

    /**
     * This method can be used to peek the first item in the stack
     * 
     * @return Token is the term being peeked
     */
    public Token peekTerm() {
        return (Token) super.peek();
    }
}
