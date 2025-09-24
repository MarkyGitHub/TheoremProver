package propositional.resolution;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import propositional.common.Binary;
import propositional.common.Formula;
import propositional.common.Propositional;
import propositional.common.Unary;
import propositional.parser.Parser;
import propositional.scanner.Precedence;
import propositional.scanner.Scanner;
import propositional.scanner.Token;

/**
 * <p>Title: Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The class NormalForm Algorithm is used to normalise a propositional sentence
 * into a conjunctive normal form. It uses Unifirm Notation rules to normalise a
 * propositional formula.
 ******************************************************************************/
public class NormalFormAlgorithm {
    /** The conjunctive normal form */
    private NFList clause;
    /** The start formula is initialise using a disjunction */
    private NormalForm start;
    /** A temporary disjunction for this method */
    private NormalForm tempNF;

    /** Iterator is declared global to allow access to it form differnt methods */
    // private ListIterator iterator;
    /**
     * Initialise the instance variables
     * 
     * @param formula
     *            is the formula for normalisation
     */
    public NormalFormAlgorithm(Formula formula) {
        clause = new NFList();
        start = new NormalForm();
        start.addFormula(formula);
        clause.addClause(start);
    }

    /**
     * This method is used to determine if a formula is a literal. A literal is
     * a propositional symbol or the negation of a propositional symbol.
     * 
     * @param formula
     *            is the item to be validated
     * @return boolean: true if the formula is a literal, false otherwise
     */
    private boolean isLiteral(Formula formula) {
        if (formula instanceof Propositional) {
            return true;
        } else if (formula instanceof Unary) {
            if (formula.getRightPredicate() instanceof Propositional) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * This method is used to implement the resolution expansion rule ¬ ¬ Z.
     * 
     * @param formula
     *            is the item for verification
     * @return boolean: true is the formula is the negation of a negation, false
     *         otherwise
     */
    private boolean negationOfUnary(Formula formula) {
        if (formula instanceof Unary && formula.getRightPredicate() instanceof Unary) {
            return true;
        }
        return false;
    }

    /**
     * This method is used to implement resolution expansion rules. It uses the
     * rules of uniform notation to do this.
     * 
     * @param formula
     *            is the formula being normalised
     * @return boolean indicates whether the normalisation has caused a
     *         branching of the formula. True if it has false otherwise
     */
    private boolean normalise(Formula formula) {
        // apply the rules for negation formulas
        if (formula instanceof Unary && formula.getRightPredicate() instanceof Binary) {
            Binary temp = (Binary) formula.getRightPredicate();
            // normalise if and only if
            if (temp.getToken().getType() == Precedence.IFF) {
                NormalForm list1 = this.tempNF.cloneNormalForm();
                NormalForm list2 = this.tempNF.cloneNormalForm();
                list1.add(new Unary(new Token("¬", Precedence.NOT), temp.getleftPredicate()));
                list2.add(temp.getRightPredicate());
                this.clause.add(list1);
                this.clause.add(list2);
                return true;
                // normalise implication
            } else if (temp.getToken().getType() == Precedence.IMPLY) {
                NormalForm list1 = this.tempNF.cloneNormalForm();
                NormalForm list2 = this.tempNF.cloneNormalForm();
                list1.add(temp.getleftPredicate());
                list2.add(new Unary(new Token("¬", Precedence.NOT), temp.getRightPredicate()));
                this.clause.add(list1);
                this.clause.add(list2);
                return true;
                // normalise or connective
            } else if (temp.getToken().getType() == Precedence.OR) {
                NormalForm list1 = this.tempNF.cloneNormalForm();
                NormalForm list2 = this.tempNF.cloneNormalForm();
                list1.add(new Unary(new Token("¬", Precedence.NOT), temp.getleftPredicate()));
                list2.add(new Unary(new Token("¬", Precedence.NOT), temp.getRightPredicate()));
                this.clause.add(list1);
                this.clause.add(list2);
                return true;
                // normalise and connective
            } else if (temp.getToken().getType() == Precedence.AND) {
                this.tempNF.add(new Unary(new Token("¬", Precedence.NOT), temp.getleftPredicate()));
                this.tempNF.add(new Unary(new Token("¬", Precedence.NOT), temp.getRightPredicate()));
                return false;
            }
            // apply the rules for all the other kinds of formulas
        } else {
            // normalise if and only if
            if (formula.getToken().getType() == Precedence.IFF) {
                this.tempNF.add(formula.getleftPredicate());
                this.tempNF.add(new Unary(new Token("¬", Precedence.NOT), formula.getRightPredicate()));
                return false;
                // normalise implication
            } else if (formula.getToken().getType() == Precedence.IMPLY) {
                this.tempNF.add(new Unary(new Token("¬", Precedence.NOT), formula.getleftPredicate()));
                this.tempNF.add(formula.getRightPredicate());
                return false;
                // normalise or connective
            } else if (formula.getToken().getType() == Precedence.OR) {
                this.tempNF.add(formula.getleftPredicate());
                this.tempNF.add(formula.getRightPredicate());
                return false;
                // normalise and connective
            } else if (formula.getToken().getType() == Precedence.AND) {
                NormalForm list1 = this.tempNF.cloneNormalForm();
                NormalForm list2 = this.tempNF.cloneNormalForm();
                list1.add(formula.getleftPredicate());
                list2.add(formula.getRightPredicate());
                this.clause.add(list1);
                this.clause.add(list2);
                return true;
            }
        }
        return false;
    }

    /**
     * This method applies the resolution expansion rules to a propositional
     * formula.
     */
    private void expandFormula() {
        int i;
        ListIterator iterator = clause.listIterator();
        while (iterator.hasNext()) {
            tempNF = (NormalForm) iterator.next();
            i = iterator.nextIndex();
            Iterator it2 = tempNF.iterator();
            while (it2.hasNext()) {
                Formula f = (Formula) it2.next();
                if (!isLiteral(f) && !negationOfUnary(f)) {
                    it2.remove();
                    if (this.normalise(f)) {
                        it2 = tempNF.iterator();
                        clause.remove((i - 1));
                        iterator = clause.listIterator();
                    } else {
                        it2 = tempNF.iterator();
                        iterator = clause.listIterator();
                    }
                } else if (negationOfUnary(f)) {
                    it2.remove();
                    Formula f1 = f.getRightPredicate().getRightPredicate();
                    tempNF.add(f1);
                    it2 = tempNF.iterator();
                }
            }
        }
    }

    /**
     * This method can be called to normalise a formula into clause normal form.
     * 
     * @return NFList is the conjunctive normal form
     */
    public NFList getNormalForm() {
        if (clause != null) {
            this.expandFormula();
        }
        System.out.println("The conjunctive normal form of the proposition: " + clause);
        return clause;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        NormalFormAlgorithm cf = null;
        InputReader ir = new InputReader();
        String s = ir.getInput();
        System.out.println(s);
        Scanner sc = new Scanner(s);
        ArrayList l = sc.getTokens();
        Parser parser = new Parser(l);
        Formula f = parser.parse();
        System.out.println(f);
        if (f != null) {
            cf = new NormalFormAlgorithm(f);
            cf.expandFormula();
            System.out.println(cf.clause);
        }
        cf.getNormalForm();
    }
}
