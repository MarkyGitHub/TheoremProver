package predicate.common;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Token class is used to represent the various predicate logical and
 * non-logical symbols. It has various methods for accessing and handling the
 * symbol.
 ******************************************************************************/
public class Token {
    /** The data value of the actual predicate logic symbol* */
    private String data;
    /** The precedence associated with the symbol* */
    private int type;

    /** Initialisation of class* */
    public Token() {
        data = null;
        type = -1;
    }

    /**
     * This constructor can be used to initialise a token with using data
     * members
     * 
     * @param data
     *            String is the data value of the symbol
     * @param type
     *            int is the precedence of the symbol
     */
    public Token(String data, int type) {
        this.data = data;
        this.type = type;
    }

    /**
     * Used to access the data field of this class
     * 
     * @return String is the string representing the symbol
     */
    public String getData() {
        return this.data;
    }

    /**
     * The method is can be used to the type field of this class
     * 
     * @return int is the precedence of the symbol
     */
    public int getType() {
        return this.type;
    }

    /**
     * Data resetting method for the data field
     * 
     * @param data
     *            String is the new value of the symbol to set
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
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Method will return reference to this class instance
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
    public void setToken(Token t) {
        this.setData(t.getData());
        this.setType(t.getType());
    }

    /**
     * Used to determine whether the token is a predicate
     * 
     * @return boolean: true if the token is predicate, false otherwise
     */
    public boolean isPredicate() {
        return this.type == 5;
    }

    /**
     * Used to determine whether the token is a binary connective
     * 
     * @return boolean: true if the token is a binary connective, false
     *         otherwise
     */
    public boolean isBinary() {
        return this.type == 7 || this.type == 8 || this.type == 9 || this.type == 10;
    }

    /**
     * Used to determine whether the token is a unary connective
     * 
     * @return boolean: true if the token is a unary connective, false otherwise
     */
    public boolean isUnary() {
        return this.type == 11;
    }

    /**
     * Used to determine whether the token is a variable
     * 
     * @return boolean: true if the token is a variable, false otherwise
     */
    public boolean isVariable() {
        return this.type == 2;
    }

    /**
     * Used to determine whether the token is a function
     * 
     * @return boolean: true if the token is a function, false otherwise
     */
    public boolean isFunction() {
        return this.type == 0;
    }

    /**
     * Used to determine whether the token is a quantifier
     * 
     * @return boolean: true if the token is a quantifier, false otherwise
     */
    public boolean isQuantifier() {
        return this.type == 3 || this.type == 4;
    }

    /**
     * Used to determine whether the token is a constant
     * 
     * @return boolean: true if the token is a constant, false otherwise
     */
    public boolean isConstant() {
        return this.type == 1;
    }

    /**
     * Used to determine whether the token is a bracket
     * 
     * @return boolean: true if the token is a bracket, false otherwise
     */
    public boolean isBracket() {
        return this.type == 6;
    }

    /**
     * Method can is used to compare the input strings with the token's data
     * 
     * @param s
     *            is the input String to be compared with the token data
     * @return int is the value indicating if the String is equal or not
     */
    public int compareTo(String s) {
        return new String(this.data).compareTo(s);
    }

    /**
     * Method returns the data value representing the symbol
     * 
     * @return String is the data value of the token
     */
    public String toString() {
        return new String(this.data);
    }
}
