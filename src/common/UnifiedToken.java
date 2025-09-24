package common;

/**
 * Unified token class that works for both propositional and predicate logic.
 *
 * <p>
 * This class replaces the separate Token classes used in propositional and
 * predicate logic implementations, providing a modern, type-safe interface. It
 * encapsulates both the token data (string representation) and its type (from
 * TokenType enum).</p>
 *
 * <p>
 * Features:
 * <ul>
 * <li>Type-safe token classification</li>
 * <li>Unified interface for both logic types</li>
 * <li>Comprehensive type checking methods</li>
 * <li>Proper equals/hashCode implementation</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public class UnifiedToken {

    private final String data;
    private final TokenType type;

    /**
     * Creates a new token with the specified data and type.
     *
     * @param data the string representation of the token
     * @param type the type of the token
     */
    public UnifiedToken(String data, TokenType type) {
        this.data = data;
        this.type = type;
    }

    /**
     * Creates a copy of another token.
     *
     * @param other the token to copy
     */
    public UnifiedToken(UnifiedToken other) {
        this.data = other.data;
        this.type = other.type;
    }

    /**
     * Returns the string data of this token.
     *
     * @return the token data
     */
    public String getData() {
        return data;
    }

    /**
     * Returns the type of this token.
     *
     * @return the token type
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Checks if this token is a predicate symbol.
     *
     * @return true if this token is a predicate
     */
    public boolean isPredicate() {
        return type == TokenType.PREDICATE;
    }

    /**
     * Checks if this token is a variable symbol.
     *
     * @return true if this token is a variable
     */
    public boolean isVariable() {
        return type == TokenType.VARIABLE;
    }

    /**
     * Checks if this token is a constant symbol.
     *
     * @return true if this token is a constant
     */
    public boolean isConstant() {
        return type == TokenType.CONSTANT;
    }

    /**
     * Checks if this token is a function symbol.
     *
     * @return true if this token is a function
     */
    public boolean isFunction() {
        return type == TokenType.FUNCTION;
    }

    /**
     * Checks if this token is a quantifier.
     *
     * @return true if this token is a quantifier
     */
    public boolean isQuantifier() {
        return type.isQuantifier();
    }

    /**
     * Checks if this token is a connective.
     *
     * @return true if this token is a connective
     */
    public boolean isConnective() {
        return type.isConnective();
    }

    /**
     * Checks if this token is a binary connective.
     *
     * @return true if this token is a binary connective
     */
    public boolean isBinary() {
        return type.isBinaryConnective();
    }

    /**
     * Checks if this token is a unary connective.
     *
     * @return true if this token is a unary connective
     */
    public boolean isUnary() {
        return type.isUnaryConnective();
    }

    /**
     * Checks if this token is a bracket.
     *
     * @return true if this token is a bracket
     */
    public boolean isBracket() {
        return type == TokenType.BRACKETS;
    }

    /**
     * Checks if this token is a term (variable, constant, or function).
     *
     * @return true if this token is a term
     */
    public boolean isTerm() {
        return type.isTerm();
    }

    /**
     * Compares this token's data with a string.
     *
     * @param s the string to compare with
     * @return comparison result (negative, zero, or positive)
     */
    public int compareTo(String s) {
        return data.compareTo(s);
    }

    /**
     * Checks if this token's data equals a string.
     *
     * @param s the string to compare with
     * @return true if the data equals the string
     */
    public boolean equals(String s) {
        return data.equals(s);
    }

    /**
     * Gets the precedence of this token.
     *
     * @return the precedence level
     */
    public int getPrecedence() {
        return type.getPrecedence();
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnifiedToken that = (UnifiedToken) obj;
        return data.equals(that.data) && type == that.type;
    }

    @Override
    public int hashCode() {
        return data.hashCode() * 31 + type.hashCode();
    }
}
