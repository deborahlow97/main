package guitests.guihandles;

import javafx.stage.Stage;

/**
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {

    private final PersonListPanelHandle personListPanel;
    private final GoalListPanelHandle goalListPanel;
    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;

    public MainWindowHandle(Stage stage) {
        super(stage);

        personListPanel = new PersonListPanelHandle(getChildNode(PersonListPanelHandle.PERSON_LIST_VIEW_ID));
        goalListPanel = new GoalListPanelHandle(getChildNode(GoalListPanelHandle.GOAL_LIST_VIEW_ID));
        resultDisplay = new ResultDisplayHandle(getChildNode(ResultDisplayHandle.RESULT_DISPLAY_ID));
        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        statusBarFooter = new StatusBarFooterHandle(getChildNode(StatusBarFooterHandle.STATUS_BAR_PLACEHOLDER));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_ID));
    }

    public PersonListPanelHandle getPersonListPanel() {
        return personListPanel;
    }
    public GoalListPanelHandle getGoalListPanel() {
        return goalListPanel;
    }
    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }
}
