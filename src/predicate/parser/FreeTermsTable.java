package predicate.parser;

import java.util.Vector;
import predicate.common.Term;
import predicate.common.Token;
import predicate.common.Variable;
import predicate.resolution.FPTable;
import predicate.resolution.FVTable;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The FreeTermsTable class is used to allocate free variables and function
 * symbols during substitution.
 ******************************************************************************/
public class FreeTermsTable {
    /** A vector of variables that records all the variables in the sentence */
    private Vector variables = new Vector();
    /** A table that contains a list of free variables for substitution */
    private FVTable freeTable = new FVTable();
    /** A table that contains a list of free parameters for substitution */
    private FPTable freePars = new FPTable();

    /** Initialise instance variables */
    public FreeTermsTable() {
        super();
    }

    /**
     * This method allows to add a variable in the table for recording as not
     * free
     * 
     * @param var
     *            Term is the term to be recorded as not free
     */
    public void addVariable(Term var) {
        variables.add(var);
    }

    /**
     * Finds if a given term is free or not
     * 
     * @param var
     *            String is the term symbol to compare
     * @return boolean: is true if the variable is free, false otherwise
     */
    private boolean isFree(String var) {
        for (int i = 0; i < variables.size(); i++) {
            Term t = (Term) variables.get(i);
            if (t.getToken().compareTo(var) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is used to get a free variable for substitution
     * 
     * @return Variable is the free variable
     */
    public Variable getFreeVariable() {
        return this.freeTable.getVariable();
    }

    /**
     * Is used to get a free parameter for substitution
     * 
     * @return Term is the free parameter
     */
    public Term getFreeParameter() {
        return this.freePars.getParameter();
    }

    /** Is used to initialise the free variable table */
    public void initialiseVars() {
        int count = 0;
        String[] s = { "u", "v", "w", "x", "y", "z" };
        for (int i = 0; i < s.length; i++) {
            if (this.isFree(s[i])) {
                Token t = new Token(s[i], 2);
                Variable v = new Variable(t, null, true);
                this.freeTable.addVariable(v);
                ++count;
            }
        }
        if (count < 4) {
            Token t = new Token("u990", 2);
            Variable v = new Variable(t, null, true);
            this.freeTable.addVariable(v);
            t = new Token("v990", 2);
            v = new Variable(t, null, true);
            this.freeTable.addVariable(v);
            t = new Token("w990", 2);
            v = new Variable(t, null, true);
            this.freeTable.addVariable(v);
        }
    }

    /** Is used to initialise the free parameter table */
    public void initialisePars() {
        int count = 0;
        String[] s = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t" };
        for (int i = 0; i < s.length; i++) {
            if (this.isFree(s[i])) {
                Token t = new Token(s[i], 2);
                Term n = new Term(t, null);
                this.freePars.addParameter(n);
                ++count;
            }
        }
        if (count < 4) {
            Token t = new Token("a990", 2);
            Variable v = new Variable(t, null, true);
            this.freeTable.addVariable(v);
            t = new Token("f990", 2);
            v = new Variable(t, null, true);
            this.freeTable.addVariable(v);
            t = new Token("j990", 2);
            v = new Variable(t, null, true);
            this.freeTable.addVariable(v);
        }
    }

    public void printVars() {
        System.out.println(this.variables);
    }
}
