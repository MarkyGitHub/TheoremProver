package propositional.sequent;

import java.util.ArrayList;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The SequentQueue class is used to hold individual sequents during the
 * application of proof mehtods. It allows to deal with each sequent
 * individually in the queue one after the other.
 ******************************************************************************/
public class SequentQueue extends ArrayList<Sequent> {
    /** The index of the head of the queue */
    private int index = 0;

    /** Initialising the SequentQueue */
    public SequentQueue() {
        super();
    }

    /**
     * Is used to add a sequent to the queue
     * 
     * @param sequent
     *            Sequent is the item to be added
     */
    public void addSequent(Sequent sequent) {
        super.add(index, sequent);
        ++index;
    }

    /**
     * This method can be used to remove a sequent from the queue
     * 
     * @return Sequent is the sequent being removed
     */
    public Sequent removeSequent() {
        Sequent s = super.remove(0);
        super.trimToSize();
        --index;
        return s;
    }

    /**
     * The method can be used to peek a sequent in the queue
     * 
     * @return Sequent is the sequent being peeked
     */
    public Sequent peekSequent() {
        return super.get(0);
    }

    /** Can be used to clear the queue of its contents */
    public void clearQueue() {
        super.clear();
        index = 0;
    }
}
