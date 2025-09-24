package inputoutput;

/* <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The OutputWriter class is used to display any outputs from the program to
 * user at the console.
 ******************************************************************************/
public class OutputWriter {
    /** The class has no data members and an empty constructor */
    public OutputWriter() {
        super();
    }

    /**
     * This method displays any error messages to the console
     * 
     * @param error
     *            String is the error message to be displayed
     */
    public static void displayError(String error) {
        System.out.println(error);
    }

    /**
     * This method displays proof to the console
     * 
     * @param proof
     *            String is the to be displayed
     */
    public static void displayProof(String proof) {
        System.out.println(proof);
    }

    /**
     * This method displays any messages to the console
     * 
     * @param mesg
     *            String is to be displayed
     */
    public static void displayMessage(String mesg) {
        System.out.println(mesg);
    }

    public static void displayPrompt(String mesg) {
        System.out.print(mesg);
    }
}
