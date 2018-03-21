package seedu.address.logic.CalendarCommands;

import seedu.address.logic.commands.UndoableCommand;


public class AddReminderCommand extends UndoableCommand{

    public static final String COMMAND_WORD = "+";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reminder to CollegeZone calendar. "


    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;
}
