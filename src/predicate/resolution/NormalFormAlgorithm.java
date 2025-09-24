package predicate.resolution;

import inputoutput.InputReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import predicate.common.Binary;
import predicate.common.Formula;
import predicate.common.Function;
import predicate.common.Predicate;
import predicate.common.Quantifier;
import predicate.common.Term;
import predicate.common.Token;
import predicate.common.Unary;
import predicate.common.Variable;
import predicate.common.WFExpression;
import predicate.parser.FormulaStack;
import predicate.parser.FreeTermsTable;
import predicate.parser.Parser;
import predicate.scanner.Scanner;
import predicate.scanner.SyntaxAnalyser;

public class NormalFormAlgorithm {
    /** A temporary disjunction for this method */
    private NormalForm temp;
    /** Iterator is declared global to allow access to it form differnt methods */
    private ListIterator it;
    /** The start formula is initialise using a disjunction */
    private NormalForm start = new NormalForm();
    /** The conjunctive normal form */
    private NFList clause = new NFList();
    /** A stack for keeping formulas during normalisation */
    private FormulaStack formStack = new FormulaStack();
    /** A stack for keeping variables during normalisation */
    private FormulaStack varStack = new FormulaStack();
    /**
     * A table that allows to get new free variables and parameters for
     * substitution
     */
    private FreeTermsTable freeTerms = new FreeTermsTable();

    /**
     * Initialise the instance variables
     * 
     * @param inputFormula
     *            WFExpression is the input formula for normalisation
     */
    public NormalFormAlgorithm(WFExpression inputFormula, FreeTermsTable vars) {
        start.addFormula(inputFormula);
        clause.addClause(start);
        this.freeTerms = vars;
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
        } else {
            return false;
        }
    }

    /**
     * A method that is used to get free variables
     * 
     * @param count
     *            int
     * @param temp
     *            Term
     * @return int
     */
    private int findFreeVariables(Term terms) {
        int count = 0;
        while (terms != null) {
            if (terms instanceof Variable) {
                if (terms.isFree()) {
                    ++count;
                    varStack.pushFormula(terms.getPartExpression());
                }
                Term temp = (Term) terms.getRightExpression();
                terms = temp;
            } else if (terms instanceof Function) {
                Function f = (Function) terms;
                findFreeVariables(f.getArguments());
            } else {
                Term temp = (Term) terms.getRightExpression();
                terms = temp;
            }
        }
        return count;
    }

    private WFExpression createFunction() {
        Term temp = null;
        while (!varStack.isEmpty()) {
            Term term = (Term) this.varStack.popFormula();
            term.setRightExpression(temp);
            temp = term;
        }
        WFExpression func = this.freeTerms.getFreeParameter();
        func.setRightExpression(temp);
        return (Term) func;
    }

    private void addFormula(Formula u1, Formula u2) {
        this.temp.add(u1);
        this.temp.add(u2);
    }

    private void addClause(NormalForm list1, NormalForm list2) {
        this.clause.add(list1);
        this.clause.add(list2);
    }

    private void subFunctionGamma(Term function, Term bindingVar) {
        this.formStack.pushFormula(function.getPartExpression());
        Term arguments = (Term) function.getRightExpression();
        Term temp = null;
        while (arguments != null) {
            if (arguments instanceof Variable) {
                if (arguments.getToken().compareTo(bindingVar.getToken().getData()) == 0) {
                    // substitute bound variable
                    variableSubstitution();
                } else {
                    formStack.pushFormula(new Term(arguments.getToken(), null));
                }
            } else if (arguments instanceof Function) {
                subFunctionGamma(arguments, bindingVar);
            } else {
                this.formStack.pushFormula(arguments.getPartExpression());
            }
            temp = (Term) arguments.getRightExpression();
            arguments = temp;
        }
        makeFunction();
    }

    private void variableSubstitution() {
        Variable v = freeTerms.getFreeVariable();
        this.formStack.pushFormula(v);
    }

    private void makeFunction() {
        Term tempA = (Term) formStack.popFormula();
        Term tempB = null;
        while (!(tempA.getToken().isFunction())) {
            tempA.setRightExpression(tempB);
            tempB = tempA;
            tempA = (Term) formStack.popFormula();
        }
        formStack.push(tempB);
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
        if (formula instanceof Unary && formula.getRightPredicate() instanceof Binary) {
            Binary temp = (Binary) formula.getRightPredicate();
            if (temp.getToken().getType() == 7) {
                NormalForm list1 = this.temp.cloneNormalForm();
                NormalForm list2 = this.temp.cloneNormalForm();
                list2.add(new Unary(new Token("¬", 11), temp.getleftPredicate()));
                list1.add(temp.getRightPredicate());
                addClause(list1, list2);
                return true;
            } else if (temp.getToken().getType() == 8) {
                NormalForm list1 = this.temp.cloneNormalForm();
                NormalForm list2 = this.temp.cloneNormalForm();
                list1.addFormula(temp.getleftPredicate());
                list2.addFormula(new Unary(new Token("¬", 11), temp.getRightPredicate()));
                addClause(list1, list2);
                return true;
            } else if (temp.getToken().getType() == 9) {
                NormalForm list1 = this.temp.cloneNormalForm();
                NormalForm list2 = this.temp.cloneNormalForm();
                list1.add(new Unary(new Token("¬", 11), temp.getleftPredicate()));
                list2.add(new Unary(new Token("¬", 11), temp.getRightPredicate()));
                addClause(list1, list2);
                return true;
            } else if (temp.getToken().getType() == 10) {
                addFormula(new Unary(new Token("¬", 11), temp.getleftPredicate()), new Unary(new Token("¬", 11), temp.getRightPredicate()));
                return false;
            }
        } else if (formula instanceof Unary && formula.getRightPredicate() instanceof Quantifier) {
            Quantifier quantifier = (Quantifier) formula.getRightPredicate();
            if (quantifier.getToken().getType() == 3) {
                deltaRule(quantifier, true);
                return false;
            }
            if (quantifier.getToken().getType() == 4) {
                gammaRule(quantifier, true);
                return false;
            }
        } else if (formula instanceof Binary) {
            if (formula.getToken().getType() == 7) {
                addFormula(formula.getleftPredicate(), new Unary(new Token("¬", 11), formula.getRightPredicate()));
                return false;
            } else if (formula.getToken().getType() == 8) {
                addFormula(new Unary(new Token("¬", 11), formula.getleftPredicate()), formula.getRightPredicate());
                return false;
            } else if (formula.getToken().getType() == 9) { // wrong
                addFormula(formula.getleftPredicate(), formula.getRightPredicate());
                return false;
            } else if (formula.getToken().getType() == 10) {
                NormalForm list1 = this.temp.cloneNormalForm();
                NormalForm list2 = this.temp.cloneNormalForm();
                list1.add(formula.getleftPredicate());
                list2.add(formula.getRightPredicate());
                addClause(list1, list2);
                return true;
            }
        } else if (formula instanceof Quantifier) {
            Quantifier quantifier = (Quantifier) formula;
            if (quantifier.getToken().getType() == 3) {
                gammaRule(quantifier, false);
                return false;
            } else if (formula.getToken().getType() == 4) {
                deltaRule(quantifier, false);
                return false;
            }
        }
        return false;
    }

    private void substituteVariable(Term bindingVar, Term terms, WFExpression freeFunc) {
        // substitute free variables
        Term temp = null;
        while (terms != null) {
            if (terms instanceof Variable) {
                if (terms.getToken().compareTo(bindingVar.getToken().getData()) == 0) {
                    // substitute
                    this.formStack.pushFormula(freeFunc);
                } else {
                    formStack.pushFormula(new Term(terms.getToken(), null));
                }
            } else if (terms instanceof Function) {
                subFunctionDelta(terms, bindingVar, (Function) freeFunc);
            } else {
                this.formStack.pushFormula(new Term(terms.getToken(), null));
            }
            temp = (Term) terms.getRightExpression();
            terms = temp;
        }
    }

    private void substituteParameters(Term bindingVar, Term arguments) {
        Term temp = null;
        while (arguments != null) {
            if (arguments instanceof Variable) {
                if (arguments.getToken().compareTo(bindingVar.getToken().getData()) == 0) {
                    parameterSubstitution();
                } else {
                    formStack.pushFormula(new Term(arguments.getToken(), null));
                }
            } else if (arguments instanceof Function) {
                subFunctionDeltaPar(arguments, bindingVar);
            } else {
                this.formStack.pushFormula(new Term(arguments.getToken(), null));
            }
            temp = (Term) arguments.getRightExpression();
            arguments = temp;
        }
    }

    private void parameterSubstitution() {
        // substitute bound variable with free parameter
        WFExpression v = freeTerms.getFreeParameter();
        this.formStack.pushFormula(v);
    }

    private void deltaRule(Quantifier quantifier, boolean negation) {
        Term bindingVar = quantifier.getVariable();
        // throw away initial quantifier
        Formula formula = quantifier.getRightPredicate();
        Formula tempA = null;
        while (!(formula.getToken().isPredicate())) {
            // keep quantifiers without the right expression
            WFExpression f = formula.getPartExpression();
            this.formStack.pushFormula(f);
            tempA = formula.getRightPredicate();
            formula = tempA;
        }
        this.formStack.pushFormula(new Predicate(formula.getToken(), null));
        Term arguments = (Term) formula.getRightExpression();
        int freeVarsCount = findFreeVariables(arguments);
        if (freeVarsCount == 0) {
            substituteParameters(bindingVar, arguments);
        } else {
            WFExpression freeVarFunction = createFunction();
            substituteVariable(bindingVar, arguments, freeVarFunction);
        }
        makeFormula(negation);
    }

    private void gammaRule(Quantifier quantifier, boolean negation) {
        Term bindingVar = quantifier.getVariable();
        // throw away initial quantifier
        Formula formula = quantifier.getRightPredicate();
        Formula tempA = null;
        while (!(formula.getToken().isPredicate())) {
            // keep quantifiers without the right expression
            WFExpression f = formula.getPartExpression();
            this.formStack.pushFormula(f);
            tempA = formula.getRightPredicate();
            formula = tempA;
        }
        // keep predicate without the right expression
        this.formStack.pushFormula(new Predicate(formula.getToken(), null));
        Term arguments = (Term) formula.getRightExpression();
        Term tempTerm = null;
        // find bound variables in predicates arguments
        while (arguments != null) {
            if (arguments.getToken().isVariable()) {
                if (arguments.getToken().compareTo(bindingVar.getToken().getData()) == 0) {
                    // substitute bound with free variable
                    variableSubstitution();
                } else {
                    formStack.pushFormula(new Term(arguments.getToken(), null));
                }
            } else if (arguments.getToken().isFunction()) {
                // find bound variables inside function
                subFunctionGamma(arguments, bindingVar);
            } else {
                this.formStack.pushFormula(arguments.getPartExpression());
            }
            tempTerm = (Term) arguments.getRightExpression();
            arguments = tempTerm;
        }
        makeFormula(negation);
    }

    private void makeFormula(boolean negation) {
        WFExpression temp = null;
        while (!formStack.isEmpty()) {
            WFExpression e = formStack.popFormula();
            if (!(e.getToken().isQuantifier()) && !(e.getToken().isPredicate())) {
                while (!(e.getToken().isPredicate())) {
                    e.setRightExpression(temp);
                    WFExpression ex = e;
                    temp = ex;
                    e = formStack.popFormula();
                }
                e.setRightExpression(temp);
                WFExpression ex = e;
                temp = ex;
            } else {
                e.setRightExpression(temp);
                WFExpression ex = e;
                temp = ex;
            }
        }
        if (negation) {
            Token t = new Token("¬", 11);
            Unary u = new Unary(t, (Formula) temp);
            this.temp.addFormula(u);
        } else {
            this.temp.addFormula(temp);
        }
    }

    private void subFunctionDeltaPar(Term function, Term bindingVar) {
        this.formStack.pushFormula(function.getPartExpression());
        Term arguments = (Term) function.getRightExpression();
        Term temp = null;
        while (arguments != null) {
            if (arguments instanceof Variable) {
                if (arguments.getToken().compareTo(bindingVar.getToken().getData()) == 0) {
                    parameterSubstitution();
                } else {
                    formStack.pushFormula(new Term(arguments.getToken(), null));
                }
            } else if (arguments instanceof Function) {
                subFunctionDeltaPar(arguments, bindingVar);
            } else {
                this.formStack.pushFormula(arguments.getPartExpression());
            }
            temp = (Term) arguments.getRightExpression();
            arguments = temp;
        }
        makeFunction();
    }

    private void subFunctionDelta(Term function, Term bindingVar, Function freeFunc) {
        this.formStack.pushFormula(function.getPartExpression());
        Term terms = (Term) function.getRightExpression();
        Term temp = null;
        while (terms != null) {
            if (terms instanceof Variable) {
                if (terms.getToken().compareTo(bindingVar.getToken().getData()) == 0) {
                    // substitute
                    this.formStack.pushFormula(freeFunc);
                } else {
                    formStack.pushFormula(new Term(terms.getToken(), null));
                }
            } else if (terms instanceof Function) {
                subFunctionDelta(terms, bindingVar, freeFunc);
            } else {
                this.formStack.pushFormula(new Term(terms.getToken(), null));
            }
            temp = (Term) terms.getRightExpression();
            terms = temp;
        }
        makeFunction();
    }

    /**
     * This method applies the resolution expansion rules to a propositional
     * formula.
     */
    private void expandFormula() {
        int i = 0;
        it = clause.listIterator();
        while (it.hasNext()) {
            temp = (NormalForm) it.next();
            i = it.nextIndex();
            Iterator it2 = temp.iterator();
            while (it2.hasNext()) {
                Formula formula = (Formula) it2.next();
                if (!formula.isLiteral() && !negationOfUnary(formula)) {
                    it2.remove();
                    if (this.normalise(formula)) {
                        it2 = temp.iterator();
                        clause.remove((i - 1));
                        it = clause.listIterator();
                    } else {
                        it2 = temp.iterator();
                        it = clause.listIterator();
                    }
                } else if (negationOfUnary(formula)) {
                    it2.remove();
                    Formula f1 = formula.getRightPredicate().getRightPredicate();
                    temp.addFormula(f1);
                    it2 = temp.iterator();
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
            return clause;
        } else {
            return clause;
        }
    }

    public static void main(String[] args) {
        InputReader ir = new InputReader();
        String s = ir.getInput();
        Scanner sc = new Scanner(s);
        System.out.println(s);
        ArrayList l = sc.getScannedTokens();
        System.out.println(l);
        SyntaxAnalyser sa = new SyntaxAnalyser(l);
        if (sa.getValidatedTokens()) {
            System.out.println(l);
            WFExpression f = null;
            FreeTermsTable tab = null;
            if (l != null) {
                Parser sm = new Parser(l);
                f = sm.accept();
                tab = sm.getReferenceTable();
                System.out.println(f);
                tab.printVars();
            }
            Formula formula = (Formula) f;
            Formula unary = new Unary(new Token("¬", 6), formula);
            System.out.println("Input " + unary);
            NormalFormAlgorithm nrf = new NormalFormAlgorithm(unary, tab);
            NFList k = nrf.getNormalForm();
            System.out.println("Normalised " + k);
        }
    }
}
