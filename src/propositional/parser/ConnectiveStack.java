package propositional.parser;

import java.util.Stack;
import propositional.scanner.Token;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The ConnectiveStack is used to hold connectives during parsing of tokens. It
 * is a sub-class of the Stack class.
 */
public class ConnectiveStack extends Stack<Token> {
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
        return super.pop();
    }

    /**
     * This method can be used to peek the first item in the stack
     * 
     * @return Token is the item being peeked
     */
    public Token peekOp() {
        return super.peek();
    }
}
