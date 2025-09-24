package propositional.scanner;

public enum Precedence {
    /** The precedence of any predicate */
    PREDICATE(0),
    /** The precedence of any bracket */
    BRACKETS(1),
    /** The precedence of any iff connective */
    IFF(2),
    /** The precedence of any implies connective */
    IMPLY(3),
    /** The precedence of any or conective */
    OR(4),
    /** The precedence of any and conective */
    AND(5),
    /** The precedence of any not connective */
    NOT(6);
    private int precedence;

    Precedence(int aPrecedence) {
        this.precedence = aPrecedence;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    public Precedence setPrecedence(int aPrecedence) {
        this.precedence = aPrecedence;
        return this;
    }
}
