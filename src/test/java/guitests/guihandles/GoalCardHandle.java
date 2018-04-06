package guitests.guihandles;

import static seedu.address.ui.GoalCard.changeImportanceToStar;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

//@@author deborahlow97
/**
 * Provides a handle to a goal card in the goal list panel.
 */
public class GoalCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String GOAL_TEXT_FIELD_ID = "#goalText";
    private static final String IMPORTANCE_FIELD_ID = "#importance";
    private static final String GOAL_START_DATE_TIME_FIELD_ID = "#startDateTime";
    private static final String GOAL_END_DATE_TIME_FIELD_ID = "#endDateTime";
    private static final String COMPLETION_FIELD_ID = "#completion";

    private final Label idLabel;
    private final Label goalTextLabel;
    private final Label importanceLabel;
    private final Label startDateTimeLabel;
    private final Label endDateTimeLabel;
    private final Label completionLabel;

    public GoalCardHandle(Node cardNode) {
        super(cardNode);

        this.idLabel = getChildNode(ID_FIELD_ID);
        this.goalTextLabel = getChildNode(GOAL_TEXT_FIELD_ID);
        this.startDateTimeLabel = getChildNode(GOAL_START_DATE_TIME_FIELD_ID);
        this.importanceLabel = getChildNode(IMPORTANCE_FIELD_ID);
        this.endDateTimeLabel = getChildNode(GOAL_END_DATE_TIME_FIELD_ID);
        this.completionLabel = getChildNode(COMPLETION_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getGoalText() {
        return goalTextLabel.getText();
    }

    public String getImportance() {
        return importanceLabel.getText();
    }

    public String getGoalStartDateTime() {
        return startDateTimeLabel.getText();
    }

    public String getGoalEndDateTime() {
        return endDateTimeLabel.getText();
    }

    public String getCompletion() {
        return completionLabel.getText();
    }

    /**
     * Takes in @param value representing the goal importance value
     * @return a number of stars string.
     */
    public static String getGoalCompletionStarSymbol(String value) {
        String starString = changeImportanceToStar(value);
        return starString;
    }
}
