package inputoutput;

import java.util.Date;
Propositional Theorem Prov
/* <p>Title:er</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The Prompt class displays prompt to the user for data input into
 ******************************************************************************/
public class Prompt {
    /** The class has no data members and an empty constructor */
    public Prompt() {
        super();
    }

    /** This method displays a welcome message to the user on the console */
    public void welcome() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("\t\tWelcome to the CC301 Degree Project - " + new Date());
        OutputWriter.displayMessage("\t\tA Theorem Prover using Java Programming Language");
        OutputWriter.displayMessage("\t\tDepartment of Computer Science - 2005");
        // OutputWriter.displayMessage("");
        OutputWriter.displayMessage("\t\tStudent Name: Mark P Schlichtmann");
        OutputWriter.displayMessage("\t\tSupervisor: Dr Chris Fox");
    }

    /** This method display the prompt to the user for data input */
    public void promptSymbols() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Use Logical Symbols -- AND (&), NOT (!), OR (|), IMPLY (=>), IFF (<=>) --");
        OutputWriter.displayPrompt("Enter string: ");
    }

    /** This method display the prompt to the user for data input */
    public void prompt() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("Enter A for propositional or B for predicate logic proof.");
        OutputWriter.displayMessage("To exit at any time enter zero (0).");
        OutputWriter.displayPrompt("Enter selection (A/B): ");
    }

    public void promptHelp() {
        OutputWriter.displayMessage("");
        OutputWriter.displayMessage("help");
    }
}