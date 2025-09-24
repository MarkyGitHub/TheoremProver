package propositional.sequent;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Sequent class implements a sequent for sequent proof. It has an
 * antecedentand a succedent, and a few methods.
 ******************************************************************************/
public class Sequent {
    /** The antecedent of the sequent */
    private FormulaList antecedent;
    /** The succedent of the sequent */
    private FormulaList succedent;

    /** Initialising the Sequent */
    public Sequent() {
        antecedent = new FormulaList();
        succedent = new FormulaList();
    }

    /**
     * Initialising the Sequent with a start sequent
     * 
     * @param sequent
     *            Sequent is the initial sequent
     */
    public Sequent(Sequent sequent) {
        this.antecedent = sequent.getAntecedent();
        this.succedent = sequent.getSuccedent();
    }

    /**
     * Initialising the Sequent with a start antecedent and succedent
     * 
     * @param antece
     *            FormulaList is the initial antecedent
     * @param succe
     *            FormulaList is the initial succedent
     */
    public Sequent(FormulaList antece, FormulaList succe) {
        this.antecedent = antece;
        this.succedent = succe;
    }

    /**
     * Can be used to access the antecedent of the sequent
     * 
     * @return FormulaList is the antecedent
     */
    public FormulaList getAntecedent() {
        return this.antecedent;
    }

    /**
     * Is used to access the succedent of the sequent
     * 
     * @return FormulaList is the succedent
     */
    public FormulaList getSuccedent() {
        return this.succedent;
    }

    /**
     * 
     * @return boolean
     * 
     * public boolean isClosed() { return false; }
     */
    /**
     * This method is used to clone this sequent
     * 
     * @return Sequent is the cloned sequent
     */
    public Sequent cloneSequent() {
        FormulaList a = new FormulaList();
        FormulaList s = new FormulaList();
        a = this.antecedent.cloneList();
        s = this.succedent.cloneList();
        return new Sequent(a, s);
    }

    /**
     * 
     * @return String
     */
    @Override
    public String toString() {
        String s = this.antecedent + " |= " + this.succedent;
        return s;
    }
}
