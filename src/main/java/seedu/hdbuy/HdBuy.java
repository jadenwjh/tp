package seedu.hdbuy;

import seedu.hdbuy.command.Command;
import seedu.hdbuy.common.HdBuyLogger;
import seedu.hdbuy.common.exception.EmptyInputException;
import seedu.hdbuy.data.UserInput;
import seedu.hdbuy.parser.Parser;
import seedu.hdbuy.ui.TextUi;

public class HdBuy {

    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) {
        HdBuyLogger.enableLogger(false);
        HdBuyLogger.info("Starting process");
        TextUi.showWelcome();
        receiveCommand(false);
    }

    /**
     * Returns a command object that will follow up with respective procedures.
     * This is a recursive method that will only exit when isExit is set to true.
     *
     * @param isExit boolean check if the command object set exit value to 1
     */
    private static void receiveCommand(boolean isExit) {
        if (!isExit) {
            String fullCommand = TextUi.readCommand();
            TextUi.showSeparator();
            try {
                Command command = Parser.parse(fullCommand);
                assert UserInput.getInputs() != null : "Input is not initiated";
                command.execute();
                TextUi.showSeparator();
                receiveCommand(command.isExit());
            } catch (EmptyInputException e) {
                TextUi.showExceptionMessage(e);
                TextUi.showSeparator();
                receiveCommand(false);
            }
        }
    }
}
