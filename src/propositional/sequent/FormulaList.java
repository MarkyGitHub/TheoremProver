package propositional.sequent;

import java.util.ArrayList;
import propositional.common.Formula;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The FormulaList class is used to represent the antecedent and the succedent
 * parts of the a sequent. They are a list of formulas.
 ******************************************************************************/
public class FormulaList extends ArrayList<Formula> {
    /** Initialising the FormulaList */
    public FormulaList() {
        super();
    }

    /**
     * Initialising the FormulaList with a start formula
     * 
     * @param formula
     *            is the initial formula
     */
    public FormulaList(Formula formula) {
        super();
        super.add(formula);
    }

    /**
     * This method can be used to add a formula to the list
     * 
     * @param formula
     *            Formula is the item to add
     */
    public void addFormula(Formula formula) {
        super.add(formula);
    }

    /**
     * The method can be used to get a formula from the list
     * 
     * @param index
     *            int is the index of the location of the item in the list
     * @return Formula is the item returned
     */
    public Formula getFormula(int index) {
        return super.get(index);
    }

    /**
     * Is used to remove a formula from the list
     * 
     * @param index
     *            int is the index of the location of the formula to remove
     * @return Formula is the formula removed
     */
    public Formula removeFormula(int index) {
        Formula f = super.remove(index);
        this.trimToSize();
        return f;
    }

    /**
     * Can be used to clone the FormulaList
     * 
     * @return FormulaList is the cloned FormulaList
     */
    public FormulaList cloneList() {
        return (FormulaList) super.clone();
    }
}
