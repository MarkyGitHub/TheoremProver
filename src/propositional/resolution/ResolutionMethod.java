package propositional.resolution;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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
 * The ResolutionMethod class is used to carry out resolution proof on a given
 * propositional formula. It uses NormalFormAlgorithm and other classes to
 * achive its task.
 ******************************************************************************/
public class ResolutionMethod {
    /** Allows to normalise a formula into conjunctive normal form */
    private NormalFormAlgorithm normalise;
    /** The conjunctive normal form of the input formula */
    private NFList cnForm;
    /** This stack is used in the resolve method to hold literals */
    private ArrayList<Formula> literal;
    /**
     * This stack is used in the resolve method to hold literals, that are
     * negation of predicates.
     */
    private ArrayList<Formula> negLiteral;

    /**
     * Initialise the instance variables
     * 
     * @param formula
     *            is the formula for proof verification
     */
    public ResolutionMethod(Formula formula) {
        Formula unary = new Unary(new Token("!", Precedence.NOT), formula);
        normalise = new NormalFormAlgorithm(unary);
        cnForm = new NFList();
        literal = new ArrayList<Formula>();
        negLiteral = new ArrayList<Formula>();
    }

    /**
     * This method is used by the resolve method to find all literals that might
     * be resolved.
     * 
     * @param formula
     *            is the item to be selected from within the conjunctive normal
     *            form for resolving.
     */
    private void findLiterals(Formula formula) {
        ListIterator it = cnForm.listIterator();
        NormalForm temp;
        while (it.hasNext()) {
            temp = (NormalForm) it.next();
            Iterator it2 = temp.iterator();
            while (it2.hasNext()) {
                Formula f = (Formula) it2.next();
                if (formula instanceof Propositional) {
                    if (f instanceof Propositional) {
                        if (f.getToken().compareTo(formula.getToken().getData()) == 0) {
                            literal.add(f);
                            it2.remove();
                        }
                    } else if (f instanceof Unary) {
                        if ((f.getRightPredicate()).getToken().compareTo(formula.getToken().getData()) == 0) {
                            negLiteral.add(f);
                            it2.remove();
                        }
                    }
                } else if (formula instanceof Unary) {
                    if (f instanceof Propositional) {
                        if ((formula.getRightPredicate()).getToken().compareTo(f.getToken().getData()) == 0) {
                            literal.add(f);
                            it2.remove();
                        }
                    } else if (f instanceof Unary) { // some problem
                        if (formula.getRightPredicate().getToken().compareTo(f.getRightPredicate().getToken().getData()) == 0) {
                            negLiteral.add(f);
                            it2.remove();
                        }
                    }
                }
            }
            if (temp.isEmpty()) {
                it.remove();
                it = cnForm.listIterator();
            }
        }
    }

    /**
     * This method resolves the conjunctive normal form, to see if the
     * proposition is valid. It uses two methods to compare literals from the
     * conjunctive normal form.
     * 
     * @return boolean: true is the formula is a tautology, false otherwise
     */
    public boolean resolve() {
        cnForm = normalise.getNormalForm();
        ListIterator it = cnForm.listIterator();
        NormalForm temp;
        // int i = 0;
        while (it.hasNext()) {
            temp = (NormalForm) it.next();
            // i = it.nextIndex();
            Iterator it2 = temp.iterator();
            while (it2.hasNext()) {
                Formula f = (Formula) it2.next();
                if (f instanceof Propositional) {
                    literal.add(f);
                    it2.remove();
                    this.findLiterals(f);
                    it2 = temp.iterator(); // is this ok
                    it = cnForm.listIterator();
                } else if (f instanceof Unary) {
                    negLiteral.add(f);
                    it2.remove();
                    this.findLiterals(f);
                    it2 = temp.iterator(); // is this ok
                    it = cnForm.listIterator();
                }
            }
        }
        if (literal.isEmpty() || negLiteral.isEmpty()) {
            return false;
        }
        this.matchLiterals();
        if (literal.isEmpty() && negLiteral.isEmpty()) {
            return true;
        }
        compareLiterals();
        compareNegLiterals();
        if (literal.isEmpty() && negLiteral.isEmpty()) {
            return true;
        }
        return false;
    }

    /** This method is used to try and resolve the literals within themselves */
    private void compareLiterals() {
        int count = 0;
        ArrayList<Formula> tempList = new ArrayList<Formula>();
        if (literal.size() > 1) {
            Formula temp;
            Iterator it = literal.iterator();
            while (it.hasNext()) {
                count = 0;
                temp = (Formula) it.next();
                while (it.hasNext()) {
                    Formula g = (Formula) it.next();
                    if (temp.getToken().compareTo(g.getToken().getData()) == 0) {
                        it.remove();
                        ++count;
                    }
                }
                if (count != 0) {
                    literal.remove(0);
                    this.literal.trimToSize();
                    it = literal.listIterator();
                } else {
                    tempList.add(temp);
                    literal.remove(0);
                    this.literal.trimToSize();
                    it = literal.listIterator();
                }
            }
            this.literal = tempList;
        }
    }

    /** This method is used to try and resolve the literals within themselves */
    private void compareNegLiterals() {
        int count = 0;
        ArrayList<Formula> tempList = new ArrayList<Formula>();
        if (negLiteral.size() > 1) {
            Formula temp;
            ListIterator it = negLiteral.listIterator();
            while (it.hasNext()) {
                count = 0;
                temp = (Formula) it.next();
                while (it.hasNext()) {
                    Formula g = (Formula) it.next();
                    if (temp.getRightPredicate().getToken().compareTo(g.getRightPredicate().getToken().getData()) == 0) {
                        it.remove();
                        ++count;
                    }
                }
                if (count != 0) {
                    negLiteral.remove(0);
                    this.negLiteral.trimToSize();
                    it = negLiteral.listIterator();
                } else {
                    tempList.add(temp);
                    negLiteral.remove(0);
                    this.negLiteral.trimToSize();
                    it = negLiteral.listIterator();
                }
            }
            this.negLiteral = tempList;
        }
    }

    /**
     * This method is used to match literals, and resolve them when they are
     * matched
     */
    private void matchLiterals() {
        ArrayList<Formula> literals = new ArrayList<Formula>();
        int count = 0;
        Iterator i = literal.iterator();
        while (i.hasNext()) {
            Formula f = (Formula) i.next();
            if (literals.size() > 0) {
                for (Formula e : literals) {
                    if (f.getToken().compareTo(e.getToken().getData()) == 0) {
                        i.remove();
                        i = literal.iterator();
                    }
                }
            }
            count = 0;
            Iterator it = negLiteral.iterator();
            while (it.hasNext()) {
                Formula temp = (Formula) it.next();
                Formula g = temp.getRightPredicate();
                if (f.getToken().compareTo(g.getToken().getData()) == 0) {
                    it.remove();
                    negLiteral.trimToSize();
                    it = negLiteral.iterator();
                    if (count == 0) {
                        literals.add(f);
                        i.remove();
                        literal.trimToSize();
                        i = literal.iterator();
                        ++count;
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ResolutionMethod rm;
        InputReader ir = new InputReader();
        String s = ir.getInput();
        System.out.println(s);
        Scanner sc = new Scanner(s);
        ArrayList l = sc.getTokens();
        System.out.println(l);
        Parser parser = new Parser(l);
        Formula f = parser.parse();
        System.out.println(f);
        if (f != null) {
            rm = new ResolutionMethod(f);
            System.out.println(rm.cnForm);
            if (rm.resolve()) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            System.out.println(rm.cnForm);
            System.out.println(rm.literal);
            System.out.println(rm.negLiteral);
        }
    }
}
