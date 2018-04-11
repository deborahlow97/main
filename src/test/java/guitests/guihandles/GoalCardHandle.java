package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;

//@@author deborahlow97
/**
 * Provides a handle to a goal card in the person list panel.
 */
public class GoalCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String GOAL_TEXT_FIELD_ID = "#goalText";
    private static final String IMPORTANCE_FIELD_ID = "#importance";
    private static final String COMPLETION_FIELD_ID = "#completion";
    private static final String START_DATE_TIME_FIELD_ID = "#startDateTime";
    private static final String END_DATE_TIME_FIELD_ID = "#endDateTime";

    private final Label idLabel;
    private final Label goalTextLabel;
    private final Label importanceLabel;
    private final Label completionLabel;
    private final Label startDateTimeLabel;
    private final Label endDateTimeLabel;

    public GoalCardHandle(Node cardNode) {
        super(cardNode);

        this.idLabel = getChildNode(ID_FIELD_ID);
        this.goalTextLabel = getChildNode(GOAL_TEXT_FIELD_ID);
        this.completionLabel = getChildNode(COMPLETION_FIELD_ID);
        this.importanceLabel = getChildNode(IMPORTANCE_FIELD_ID);
        this.startDateTimeLabel = getChildNode(START_DATE_TIME_FIELD_ID);
        this.endDateTimeLabel = getChildNode(END_DATE_TIME_FIELD_ID);
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

    public String getCompletion() {
        return completionLabel.getText();
    }

    public String getStartDateTime() {
        return startDateTimeLabel.getText();
    }

    public String getEndDateTime() {
        return endDateTimeLabel.getText();
    }
}
