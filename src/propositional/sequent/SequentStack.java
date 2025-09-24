package propositional.sequent;

import java.util.ArrayList;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The SequentSrack class is a stack for sequents. It is used to hold the proof
 * steps during proof generation.
 */
public class SequentStack extends ArrayList<Sequent> {
    /** Is the top of the sequent stack */
    private int index = 0;

    /** Initialising the sequent stack */
    public SequentStack() {
        super();
    }

    /**
     * This method is used to push a sequent iinto the queue
     * 
     * @param sequent
     *            Sequent is the item to be pushed inside the stack
     */
    public void pushSequent(Sequent sequent) {
        super.add(index, sequent);
        ++index;
    }

    /**
     * The method can be used to pop a sequent from the stack
     * 
     * @return Sequent is the item being popped
     */
    public Sequent popSequent() {
        Sequent s = super.remove(index - 1);
        this.trimToSize();
        --index;
        return s;
    }

    /** This method allows to clear the sequent stack */
    public void clearStack() {
        super.clear();
        index = 0;
    }
}
