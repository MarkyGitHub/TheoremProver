package predicate.resolution;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import predicate.common.Formula;
import predicate.common.Predicate;
import predicate.common.Unary;
import predicate.common.WFExpression;
import predicate.parser.FreeTermsTable;
import predicate.parser.Parser;
import predicate.scanner.Scanner;

public class ResolutionMethod {
    /** Allows to normalise a formula into conjunctive normal form */
    private NormalFormAlgorithm normalise;
    /** The conjunctive normal form of the input formula */
    private NFList cnForm;
    /** This stack is used in the resolve method to hold literals */
    private ArrayList literal;
    /**
     * This stack is used in the resolve method to hold literals, that are
     * negation of predicates.
     */
    private ArrayList negLiteral;

    public ResolutionMethod(WFExpression formula) {
        // normalise = new NormalFormAlgorithm(formula);
        cnForm = new NFList();
        normalise = new NormalFormAlgorithm(formula, new FreeTermsTable());
    }

    private void findLiterals(Formula formula) {
        ListIterator it = cnForm.listIterator();
        NormalForm temp;
        while (it.hasNext()) {
            temp = (NormalForm) it.next();
            Iterator it2 = temp.iterator();
            while (it2.hasNext()) {
                Formula f = (Formula) it2.next();
                if (formula instanceof Predicate) {
                    if (f instanceof Predicate) {
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
                    if (f instanceof Predicate) {
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

    public boolean resolve() {
        cnForm = normalise.getNormalForm();
        System.out.println(cnForm);
        ListIterator it = cnForm.listIterator();
        NormalForm temp;
        int i = 0;
        while (it.hasNext()) {
            temp = (NormalForm) it.next();
            i = it.nextIndex();
            Iterator it2 = temp.iterator();
            while (it2.hasNext()) {
                Formula f = (Formula) it2.next();
                if (f instanceof Predicate) {
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
        } else {
            literal.clear();
            negLiteral.clear();
            System.out.println(this.cnForm.size());
            return true;
        }
    }

    public void cnForm() {
        System.out.println(this.cnForm);
    }

    public static void main(String[] args) {
        ResolutionMethod rm;
        InputReader ir = new InputReader();
        String s = ir.getInput();
        System.out.println(s);
        Scanner sc = new Scanner(s);
        ArrayList l = sc.getScannedTokens();
        Parser parser = new Parser(l);
        System.out.println(l);
        Formula f = (Formula) parser.accept();
        System.out.println(f);
        if (f != null) {
            rm = new ResolutionMethod(f);
            System.out.println(rm.cnForm);
            /*
             * if (rm.resolve()) { System.out.println("yes"); } else {
             * System.out.println("no"); } System.out.println(rm.cnForm);
             * System.out.println(rm.literal);
             * System.out.println(rm.negLiteral);
             */
        }
    }
}
