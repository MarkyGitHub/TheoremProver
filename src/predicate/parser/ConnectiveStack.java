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
 * The ConnectiveStack class extends the Stack class and is used to represent a
 * connective stack. It has a set of methods to push and pop Tokens in and out
 * of the stack
 ******************************************************************************/
public class ConnectiveStack extends Stack {
    /** Initialisation of connective stack */
    public ConnectiveStack() {
        super();
    }

    /**
     * This method is used to push tokens into the stack
     * 
     * @param op
     *            Token is the item to be placed in the stack
     */
    public void pushOp(Token op) {
        super.push(op);
    }

    /**
     * This method is used to pop a token from the stack
     * 
     * @return Token is the item being popped
     */
    public Token popOp() {
        return ((Token) super.pop());
    }

    /**
     * This method can be used to peek the first item in the stack
     * 
     * @return Token is the item being peeked
     */
    public Token peekOp() {
        return ((Token) super.peek());
    }
}
