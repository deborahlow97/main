package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ThemeSwitchRequestEvent;
import seedu.address.logic.commands.exceptions.CommandException;

//@@author deborahlow97
/**
 * Changes the CollegeZone colour theme to either dark or light.
 */
public class ThemeCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_SUCCESS = "Theme successfully changed!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the theme to the theme word entered.\n"
            + "Parameters: COLOUR THEME\n"
            + "(Colour theme words: dark, light)\n"
            + "Example: " + COMMAND_WORD + " dark\n";
    public static final String VIEW_PATH = "/view/";
    public static final String MESSAGE_INVALID_THEME_COLOUR = "Theme colour entered is invalid."
            + "Possible theme colours:\n"
            + "(Colour theme words: dark, light)\n";
    public static final String MESSAGE_ALREADY_IN_CURRENT_THEME = "CollegeZone is already in the theme colour.";
    private final String themeColour;
    private String themeToChangeTo;

    /**
     * Creates a ThemeCommand based on the specified themeColour.
     */
    public ThemeCommand (String themeColour) {
        this.themeColour = themeColour;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);

        String currentTheme = model.getCurrentTheme();
        HashMap<String, String> themes = model.getThemeHashMap();
        if (themes.containsKey(themeColour.toLowerCase())) {
            themeToChangeTo = themes.get(themeColour.toLowerCase());
        } else {
            throw new CommandException(MESSAGE_INVALID_THEME_COLOUR);
        }

        if (currentTheme.equals(VIEW_PATH + themeToChangeTo)) {
            throw new CommandException(MESSAGE_ALREADY_IN_CURRENT_THEME);
        }

        EventsCenter.getInstance().post(new ThemeSwitchRequestEvent(themeToChangeTo));
        return new CommandResult(String.format(MESSAGE_SUCCESS, themeToChangeTo));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThemeCommand // instanceof handles nulls
                && themeColour.equals(((ThemeCommand) other).themeColour));
    }
}
