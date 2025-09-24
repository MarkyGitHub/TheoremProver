package propositional.scanner;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Token class is used to represent the various propositional logical and
 * non-logical symbols. It has various methods for accessing and handling the
 * symbol.
 ******************************************************************************/
public class Token {
    /** The data value of the actual propositional symbol* */
    private String data;
    /** The precedence associated with the propositional symbol* */
    private Precedence type;

    /** Initialisation of private data members* */
    public Token() {
        data = null;
        type = null;
    }

    /**
     * This constructor can be used to initialise a token with its data members,
     * when the token is created.
     * 
     * @param data
     *            String is the data value of the symbol
     * @param type
     *            int is the precedence of the symbol
     */
    public Token(String data, Precedence type) {
        this.data = data;
        this.type = type;
    }

    public Token(Token token) {
        this.data = token.getData();
        this.type = token.getType();
    }

    /**
     * Data access method for the data value
     * 
     * @return String is the string representation of the symbol
     */
    public String getData() {
        return this.data;
    }

    /**
     * Data access method for the type value
     * 
     * @return int is the precedence of the symbol
     */
    public Precedence getType() {
        return this.type;
    }

    /**
     * Data resetting method for the data field
     * 
     * @param data
     *            String is the new value of the symbol
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Data resetting method for the type field
     * 
     * @param type
     *            int is the new value for the precedence
     */
    public void setType(Precedence type) {
        this.type = type;
    }

    /**
     * Method will return reference to itself
     * 
     * @return Token is a reference to this token
     */
    public Token getToken() {
        return this;
    }

    /**
     * Can be used to reset the token to a new token
     * 
     * @param token
     *            is a new token that the current token is to be reset to
     */
    public void setToken(Token token) {
        this.setData(token.getData());
        this.setType(token.getType());
    }

    /**
     * Used to determine whether the token is a predicate
     * 
     * @return boolean: true if the token is predicate, false otherwise
     */
    public boolean isPredicate() {
        return this.type == Precedence.PREDICATE;
    }

    /**
     * Used to determine whether the token is a connective
     * 
     * @return boolean: true if the token is a connective, false otherwise
     */
    public boolean isConnective() {
        return this.type == Precedence.IFF || this.type == Precedence.IMPLY || this.type == Precedence.OR || this.type == Precedence.AND || this.type == Precedence.NOT;
    }

    /**
     * Used to determine whether the token is a bracket
     * 
     * @return boolean: true if the token is predicate, false otherwise
     */
    public boolean isBracket() {
        return this.type == Precedence.BRACKETS;
    }

    /**
     * Used to determine whether the token is a binary connective
     * 
     * @return boolean: true if the token is predicate, false otherwise
     */
    public boolean isBinary() {
        return this.type == Precedence.IFF || this.type == Precedence.IMPLY || this.type == Precedence.OR || this.type == Precedence.AND;
    }

    /**
     * Used to determine whether the token is a unary connective
     * 
     * @return boolean: true if the token is predicate, false otherwise
     */
    public boolean isUnary() {
        return this.type == Precedence.NOT;
    }

    /**
     * Method can is used to compare the input strings with the token's data
     * 
     * @param s
     *            is the input String to be compared with the token data
     * @return int is the value indicating if the String is equal to the data
     */
    public int compareTo(String s) {
        return new String(this.data).compareTo(s);
    }

    /**
     * Method returns the data value representing the symbol
     * 
     * @return String is the data value of the token
     */
    @Override
    public String toString() {
        return new String(this.data);
    }
}
