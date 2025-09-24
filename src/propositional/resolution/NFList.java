package propositional.resolution;

import java.util.ArrayList;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The NFList class is used to represent a conjunctive normal form. It has as
 * its items NormalForm, which are disjuctions of propositional literals, P, Q,
 * R, and so on. This class is used to store and retrieve disjunctions. It has
 * no other significant methods.
 ******************************************************************************/
public class NFList extends ArrayList<NormalForm> {
    /** Initialising the NFList */
    public NFList() {
        super();
    }

    /**
     * A method that allows to add a new NormalForm (disjunction) to the NFList
     * 
     * @param list
     *            NormalForm is the disjunction added to the NFList
     */
    public void addClause(NormalForm list) {
        super.add(list);
    }

    /**
     * This method can be used to clone this NFLIst
     * 
     * @return NFList is the cloned NFList
     */
    public NFList cloneList() {
        return (NFList) super.clone();
    }

    /**
     * The method is used to get a NormalForm
     * 
     * @param index
     *            int is the index of the location of the NormalForm to get
     * @return NormalForm is the item returned by this method call
     */
    public NormalForm getClause(int index) {
        return super.get(index);
    }
}
