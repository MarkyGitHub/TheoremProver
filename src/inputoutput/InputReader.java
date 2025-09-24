package inputoutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Input reader for handling user input from the console.
 *
 * <p>
 * This class manages user input for the theorem prover application. It displays
 * prompts to the user and reads input from the console using System.in. The
 * class handles input validation and provides a clean interface for getting
 * user data.</p>
 *
 * <p>
 * Features:
 * <ul>
 * <li>Interactive prompt display</li>
 * <li>Input validation and error handling</li>
 * <li>Graceful handling of end-of-input conditions</li>
 * <li>Loop-based input collection until valid input or exit</li>
 * </ul></p>
 *
 * @author Mark Schlichtmann
 * @version 1.0
 * @since 2005
 */
public class InputReader {

    /**
     * The input string collected from the user
     */
    private String input;
    /**
     * Buffered reader for console input
     */
    private BufferedReader buffer;
    /**
     * Prompt handler for displaying messages to the user
     */
    private Prompt prompt;

    /**
     * Constructs a new InputReader instance. Initializes input handling and
     * prompt system.
     */
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
                String line = buffer.readLine();
                if (line == null) {
                    // Handle end of input stream (e.g., when using pipes)
                    input = "0";
                    break;
                }
                input = line.trim();
            } catch (IOException ex) {
                OutputWriter.displayMessage(ex.getMessage().toString());
                prompt.promptSymbols();
            }
            /**
             * This method will cause the program to exit whenever zero '0' is
             * entered
             */
        } while (input.length() == 0 && !input.equals("0"));
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
