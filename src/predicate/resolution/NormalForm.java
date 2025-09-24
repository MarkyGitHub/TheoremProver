package predicate.resolution;

import java.util.ArrayList;
import predicate.common.WFExpression;

/**
 * <p>Title: Predicate Logic Theorem Prover</p>
 * <p>Description: A theorem prover for predicate logic</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The NormalForm class is used to represent a disjunction of literals. It is
 * used in the normalisation of propositional sentences, and is part of a
 * conjunctive normal form. Its items are of type Formula.
 ******************************************************************************/
public class NormalForm extends ArrayList {
    /** Initialising the NormalForm */
    public NormalForm() {
        super();
    }

    /**
     * Initialisation of NormalForm with a Formula
     * 
     * @param formula
     *            Formula is a start formula being added
     */
    public NormalForm(WFExpression formula) {
        super.add(formula);
    }

    /**
     * This method is used to add a formula to the NormalForm
     * 
     * @param formula
     *            Formula is the item being added
     */
    public void addFormula(WFExpression formula) {
        super.add(formula);
    }

    /**
     * The method can be used to get a Formula in the NormalForm
     * 
     * @param index
     *            int is the index of the location of the formula to get
     * @return Formula is the item being returned by this method call
     */
    public WFExpression getFormula(int index) {
        return (WFExpression) super.get(index);
    }

    /**
     * The method can be used to remove a formula from the NormalForm
     * 
     * @param index
     *            int is the index of the location of the formula
     * @return Formula is the that is returned after it is removed
     */
    public WFExpression removeFormula(int index) {
        WFExpression f = (WFExpression) super.remove(index);
        this.trimToSize();
        return f;
    }

    /**
     * This method can be used to clone this NormalForm
     * 
     * @return NormalForm is the cloned NormalForm
     */
    public NormalForm cloneNormalForm() {
        return (NormalForm) super.clone();
    }
}
