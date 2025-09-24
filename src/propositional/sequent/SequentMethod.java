package propositional.sequent;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import propositional.common.Binary;
import propositional.common.Formula;
import propositional.common.Propositional;
import propositional.common.Unary;
import propositional.parser.Parser;
import propositional.resolution.ResolutionMethod;
import propositional.scanner.Precedence;
import propositional.scanner.Scanner;
import propositional.scanner.Token;

/**
 * <p>
 * Title: Propositional Theorem Prover
 * </p>
 * <p>
 * Description: A theorem prover for propositional logic.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Department: Computer Science
 * </p>
 * 
 * @author Mark Schlichtmann
 * @version 1.0
 */
/*******************************************************************************
 * The SequentMethod class is used to generate a proof sequence for a valid
 * propositional sentence. It is used to display proof to the user.
 ******************************************************************************/
public class SequentMethod {
    /** The sequent for proof generation */
    private Sequent initialSequent;
    /** A queue of all the sequents used during proof generation */
    private SequentQueue sequentsList;
    /** A stack is used to hold the sequence of proof steps generated for display */
    private SequentStack displayStack;
    private Iterator it;
    private int branchingCount = 0;

    /**
     * Initialising the sequent method
     * 
     * @param Formula
     *            is the formula for proof generation
     */
    public SequentMethod(Formula formula) {
        sequentsList = new SequentQueue();
        displayStack = new SequentStack();
        initialSequent = new Sequent(new FormulaList(), new FormulaList(formula));
        this.sequentsList.addSequent(initialSequent);
        // displayStack.pushSequent(initialSequent.cloneSequent());
    }

    /**
     * An axion is a propositional non-logical symbol
     * 
     * @param Formula
     *            is the formula for verification
     * @return boolean: true if the formula is an axiom false otherwise
     */
    private boolean isAxiom(Formula formula) {
        if (formula instanceof Propositional) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if all the items in the sequent have been fully
     * expanded.
     * 
     * @param Sequent
     *            is the sequent for verification
     * @return boolean: true is the sequent is fully expanded, false otherwise
     */
    public boolean notFinished(Sequent sequent) {
        Formula formula;
        for (int i = 0; i < sequent.getAntecedent().size(); i++) {
            formula = sequent.getAntecedent().getFormula(i);
            if (!isAxiom(formula)) {
                return true;
            }
        }
        for (int j = 0; j < sequent.getSuccedent().size(); j++) {
            formula = sequent.getSuccedent().getFormula(j);
            if (!isAxiom(formula)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to apply sequent derivation rules to the succedent
     * part of the sequent.
     * 
     * @param Sequent
     *            is the sequent for derivation
     * @param Formula
     *            is the formula that has to be applied with a particular rule
     * @return Sequent is the sequent after it has been applied the rule
     */
    private boolean derivationLeft(Sequent sequent, Formula formula) {
        // derivation rule applied to an unary formula
        if (formula instanceof Unary) {
            sequent.getSuccedent().addFormula(formula.getRightPredicate());
            this.displayStack.pushSequent(sequent.cloneSequent());
            return false;
            // derivation rule applied to an iff formula
        } else if (formula.getToken().getType() == Precedence.IFF) {
            Binary b1 = new Binary(new Token("->", Precedence.IMPLY), formula.getleftPredicate(), formula.getRightPredicate());
            Binary b2 = new Binary(new Token("->", Precedence.IMPLY), formula.getRightPredicate(), formula.getleftPredicate());
            sequent.getAntecedent().addFormula(b1);
            sequent.getAntecedent().addFormula(b2);
            this.displayStack.pushSequent(sequent.cloneSequent());
            return false;
            // derivation rule applied to an implication formula
        } else if (formula.getToken().getType() == Precedence.IMPLY) {
            FormulaList succe = sequent.getSuccedent().cloneList();
            FormulaList ante = sequent.getAntecedent().cloneList();
            succe.addFormula(formula.getleftPredicate());
            ante.addFormula(formula.getRightPredicate());
            Sequent left = new Sequent(sequent.getAntecedent(), succe);
            Sequent right = new Sequent(ante, sequent.getSuccedent());
            // application of rule causes branching of sequent
            this.sequentsList.addSequent(left);
            this.sequentsList.addSequent(right);
            displayStack.pushSequent(left.cloneSequent());
            displayStack.pushSequent(right.cloneSequent());
            return true;
            // derivation rule applied to a disjunction formula
        } else if (formula.getToken().getType() == Precedence.OR) {
            FormulaList ante1 = sequent.getAntecedent();
            FormulaList ante2 = sequent.getAntecedent();
            ante1.addFormula(formula.getleftPredicate());
            ante2.addFormula(formula.getRightPredicate());
            Sequent left = new Sequent(ante1, sequent.getSuccedent());
            Sequent right = new Sequent(ante2, sequent.getSuccedent());
            // application of rule causes branching of sequent
            this.sequentsList.addSequent(left);
            this.sequentsList.addSequent(right);
            displayStack.pushSequent(left.cloneSequent());
            displayStack.pushSequent(right.cloneSequent());
            return true;
            // derivation rule applied to a conjunction formula
        } else if (formula.getToken().getType() == Precedence.OR) {
            sequent.getAntecedent().addFormula(formula.getleftPredicate());
            sequent.getAntecedent().addFormula(formula.getRightPredicate());
            this.displayStack.pushSequent(sequent.cloneSequent());
            return false;
        }
        return true;
    }

    /**
     * This method is used to apply sequent derivation rules to the antecedent
     * part of the sequent.
     * 
     * @param Sequent
     *            is the sequent for derivation
     * @param Formula
     *            is the formula that has to be applied with a particular rule
     * @return Sequent is the sequent after it has been applied the rule
     */
    private boolean derivationRight(Sequent sequent, Formula formula) {
        // derivation rule applied to an unary formula
        if (formula instanceof Unary) {
            sequent.getAntecedent().addFormula(formula.getRightPredicate());
            this.displayStack.pushSequent(sequent.cloneSequent());
            return false;
            // derivation rule applied to an iff formula
        } else if (formula.getToken().getType() == Precedence.IFF) {
            Binary b1 = new Binary(new Token("->", Precedence.IMPLY), formula.getleftPredicate(), formula.getRightPredicate());
            Binary b2 = new Binary(new Token("->", Precedence.IMPLY), formula.getRightPredicate(), formula.getleftPredicate());
            FormulaList succe1 = sequent.getSuccedent();
            FormulaList succe2 = sequent.getSuccedent();
            succe1.addFormula(b1);
            succe2.addFormula(b2);
            Sequent left = new Sequent(sequent.getAntecedent(), succe1);
            Sequent right = new Sequent(sequent.getAntecedent(), succe2);
            // application of rule causes branching of sequent
            this.sequentsList.addSequent(left);
            this.sequentsList.addSequent(right);
            displayStack.pushSequent(left.cloneSequent());
            displayStack.pushSequent(right.cloneSequent());
            return true;
            // derivation rule applied to an implication formula
        } else if (formula.getToken().getType() == Precedence.IMPLY) {
            sequent.getAntecedent().addFormula(formula.getleftPredicate());
            sequent.getSuccedent().addFormula(formula.getRightPredicate());
            this.displayStack.pushSequent(sequent.cloneSequent());
            return false;
            // derivation rule applied to a disjunction formula
        } else if (formula.getToken().getType() == Precedence.OR) {
            sequent.getSuccedent().addFormula(formula.getleftPredicate());
            sequent.getSuccedent().addFormula(formula.getRightPredicate());
            this.displayStack.pushSequent(sequent.cloneSequent());
            return false;
            // derivation rule applied to a conjunction formula
        } else if (formula.getToken().getType() == Precedence.AND) {
            FormulaList succe1 = sequent.getSuccedent();
            FormulaList succe2 = sequent.getSuccedent();
            succe1.addFormula(formula.getleftPredicate());
            succe2.addFormula(formula.getRightPredicate());
            Sequent left = new Sequent(sequent.getAntecedent(), succe1);
            Sequent right = new Sequent(sequent.getAntecedent(), succe2);
            // application of rule causes branching of sequent
            this.sequentsList.addSequent(left);
            this.sequentsList.addSequent(right);
            displayStack.pushSequent(left.cloneSequent());
            displayStack.pushSequent(right.cloneSequent());
            return true;
        }
        return true;
    }

    /**
     * This method expand the sequent by applying the corresponding right or
     * left derivation rules to its formulas.
     * 
     * @param Sequent
     *            is the sequent for expanding
     */
    private void expandSequent(Sequent sequent) {
        int i = 1;
        boolean removed = false;
        while (i != 0) {
            Iterator it1 = sequent.getAntecedent().iterator();
            i = 0;
            while (it1.hasNext()) {
                Formula formula = (Formula) it1.next();
                if (!isAxiom(formula)) {
                    ++i;
                    it1.remove();
                    if (derivationLeft(sequent, formula)) {
                        if (!removed) {
                            sequentsList.removeSequent();
                            removed = true;
                            ++this.branchingCount;
                        }
                    }
                    it1 = sequent.getAntecedent().iterator();
                }
            }
            Iterator it2 = sequent.getSuccedent().iterator();
            while (it2.hasNext()) {
                Formula formula = (Formula) it2.next();
                if (!isAxiom(formula)) {
                    ++i;
                    it2.remove();
                    if (derivationRight(sequent, formula)) {
                        if (!removed) {
                            sequentsList.removeSequent();
                            removed = true;
                            ++this.branchingCount;
                        }
                    }
                    it2 = sequent.getSuccedent().iterator();
                }
            }
        }
    }

    /**
     * This method is used to search for sequents containing formulas that have
     * to be expanded. It fully expand a formula and generates the proof
     * sequence.
     */
    public void searchSequent() {
        it = sequentsList.iterator();
        while (it.hasNext()) {
            Sequent sequent = this.sequentsList.peekSequent();
            if (notFinished(sequent)) {
                displayStack.pushSequent(sequent.cloneSequent());
                expandSequent(sequent);
                it = sequentsList.iterator();
            } else {
                // displayStack.pushSequent(sequent.cloneSequent());
                sequentsList.removeSequent();
            }
        }
    }

    /** This method displays the proof constructed by this class to the console */
    public void display() {
        System.out.println();
        while (!displayStack.isEmpty()) {
            System.out.println(this.displayStack.size() + "\t" + displayStack.popSequent());
            System.out.println("------------------------");
        }
        System.out.println("Proof verifcation: generated by Sequent proof method");
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SequentMethod pm;
        ResolutionMethod rm;
        InputReader ir = new InputReader();
        Scanner sc = new Scanner(ir.getInput());
        ArrayList l = sc.getTokens();
        Formula f = null;
        if (l != null) {
            Parser parser = new Parser(l);
            f = parser.parse();
            System.out.println(f);
        }
        if (f != null) {
            rm = new ResolutionMethod(f);
            if (rm.resolve()) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            pm = new SequentMethod(f);
            pm.searchSequent();
            pm.display();
        }
    }
}
