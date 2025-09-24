package inputoutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** <p>Title:Propositional Theorem Prover</p>
 * <p>Description: A theorem prover for propositional logic.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Department: Computer Science</p>
 * @author Mark Schlichtmann
 * @version 1.0*/
/*******************************************************************************
 * The InputReader class is used to display a prompt to the user that is used to
 * input string data into the program. The InpurReader class gets the user input
 * from the console using the System.in. This method displays the prompt in a
 * loop, so that unless data has been entered into the system the program will
 * continue to display the prompt. The program will exit when zero '0' is
 * entered.
 ******************************************************************************/
public class InputReader {
    /** The input string that is the data input for the program */
    private String input;
    /**
     * A buffered reader is used to read the characters from the console using
     * InputStreamReader and System.in
     */
    private BufferedReader buffer;
    /** This class displays a prompt to the user for data input */
    private Prompt prompt;

    /** Initialisation of private data members */
    public InputReader() {
        input = new String("");
        buffer = new BufferedReader(new InputStreamReader(System.in));
        prompt = new Prompt();
    }

    /**
     * This private method displays a prompt in a loop and gets the input string
     * from the console. It uses prompt and buffer to do this.
     * 
     * @return String is the user input at the console
     */
    private String read() {
        do {
            prompt.promptSymbols();
            try {
                input = buffer.readLine().trim();
            } catch (IOException ex) {
                OutputWriter.displayMessage(ex.getMessage().toString());
                prompt.promptSymbols();
            }
            /**
             * This method will cause the program to exit whenever zero '0' is
             * entered
             */
        } while (input.length() == 0 && input != "0");
        if (input.compareTo("0") == 0) {
            OutputWriter.displayMessage("Thank you for choosing this program. Goodbye.");
            System.exit(0);
        } else {
            String s = input + ".";
            input = s;
        }
        return input;
    }

    /**
     * This method is used to get user input form the console and return it to
     * the calling program.
     * 
     * @return String is the user input at the console
     */
    public String getInput() {
        return read();
    }

    public static void main(String[] args) {
        InputReader ir = new InputReader();
        System.out.println(ir.getInput());
    }
}
