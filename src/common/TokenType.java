package common;

/**
 * Unified token type enumeration for both propositional and predicate logic.
 *
 * <p>
 * This enum modernizes the token system by providing a single, type-safe
 * interface that replaces the separate precedence systems used in the original
 * implementation. It defines all possible token types with their precedence
 * levels and provides utility methods for type checking.</p>
 *
 * <p>
 * The precedence hierarchy follows standard logical operator precedence:
 * <ul>
 * <li>IFF (7) - lowest precedence</li>
 * <li>IMPLY (8)</li>
 * <li>OR (9)</li>
 * <li>AND (10)</li>
 * <li>NOT (11) - highest precedence</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 2.0
 * @since 2025
 */
public enum TokenType {
    // Basic symbols
    PREDICATE(0),
    CONSTANT(1),
    VARIABLE(2),
    // Quantifiers
    UNIVERSAL(3),
    EXISTENTIAL(4),
    // Functions
    FUNCTION(5),
    // Brackets
    BRACKETS(6),
    // Connectives (with precedence)
    IFF(7), // Lowest precedence
    IMPLY(8),
    OR(9),
    AND(10),
    NOT(11);     // Highest precedence

    /**
     * The precedence level of this token type
     */
    private final int precedence;

    /**
     * Constructs a token type with the specified precedence.
     *
     * @param precedence the precedence level (lower = higher precedence)
     */
    TokenType(int precedence) {
        this.precedence = precedence;
    }

    /**
     * Returns the precedence level of this token type.
     *
     * @return the precedence level
     */
    public int getPrecedence() {
        return precedence;
    }

    /**
     * Check if this token type is a connective
     */
    public boolean isConnective() {
        return this == IFF || this == IMPLY || this == OR || this == AND || this == NOT;
    }

    /**
     * Check if this token type is a binary connective
     */
    public boolean isBinaryConnective() {
        return this == IFF || this == IMPLY || this == OR || this == AND;
    }

    /**
     * Check if this token type is a unary connective
     */
    public boolean isUnaryConnective() {
        return this == NOT;
    }

    /**
     * Check if this token type is a quantifier
     */
    public boolean isQuantifier() {
        return this == UNIVERSAL || this == EXISTENTIAL;
    }

    /**
     * Check if this token type is a term (variable, constant, function)
     */
    public boolean isTerm() {
        return this == VARIABLE || this == CONSTANT || this == FUNCTION;
    }

    /**
     * Get the symbol representation for this token type
     */
    public String getSymbol() {
        switch (this) {
            case NOT:
                return "!";
            case AND:
                return "&";
            case OR:
                return "|";
            case IMPLY:
                return "=>";
            case IFF:
                return "<=>";
            case UNIVERSAL:
                return "A";
            case EXISTENTIAL:
                return "E";
            case BRACKETS:
                return "()";
            default:
                return "";
        }
    }
}
