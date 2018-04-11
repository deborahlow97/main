package guitests.guihandles;

import java.util.List;
import java.util.Optional;

import javafx.scene.control.ListView;
import seedu.address.model.goal.Goal;
import seedu.address.ui.GoalCard;

//@@author deborahlow97
/**
 * Provides a handle for {@code GoalListPanel} containing the list of {@code GoalCard}.
 */
public class GoalListPanelHandle extends NodeHandle<ListView<GoalCard>> {
    public static final String GOAL_LIST_VIEW_ID = "#goalListView";

    private Optional<GoalCard> lastRememberedSelectedGoalCard;

    public GoalListPanelHandle(ListView<GoalCard> goalListPanelNode) {
        super(goalListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code PersonCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     */
    public GoalCardHandle getHandleToSelectedCard() {
        List<GoalCard> goalList = getRootNode().getSelectionModel().getSelectedItems();

        if (goalList.size() != 1) {
            throw new AssertionError("Goal list size expected 1.");
        }

        return new GoalCardHandle(goalList.get(0).getRoot());
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<GoalCard> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display and select the goal.
     */
    public void navigateToCard(Goal goal) {
        List<GoalCard> cards = getRootNode().getItems();
        Optional<GoalCard> matchingCard = cards.stream().filter(card -> card.goal.equals(goal)).findFirst();

        if (!matchingCard.isPresent()) {
            throw new IllegalArgumentException("Goal does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(matchingCard.get());
            getRootNode().getSelectionModel().select(matchingCard.get());
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code GoalCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Remembers the selected {@code GoalCard} in the list.
     */
    public void rememberSelectedGoalCard() {
        List<GoalCard> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedGoalCard = Optional.empty();
        } else {
            lastRememberedSelectedGoalCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code GoalCard} is different from the value remembered by the most recent
     * {@code rememberSelectedGoalCard()} call.
     */
    public boolean isSelectedGoalCardChanged() {
        List<GoalCard> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedGoalCard.isPresent();
        } else {
            return !lastRememberedSelectedGoalCard.isPresent()
                    || !lastRememberedSelectedGoalCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }

    //@@author deborahlow97
    /**
     * Returns the goal card handle of a goal associated with the {@code index} in the list.
     */
    public GoalCardHandle getGoalCardHandle(int index) {
        return getGoalCardHandle(getRootNode().getItems().get(index).goal);
    }

    /**
     * Returns the {@code GoalCardHandle} of the specified {@code goal} in the list.
     */
    public GoalCardHandle getGoalCardHandle(Goal goal) {
        Optional<GoalCardHandle> handle = getRootNode().getItems().stream()
                .filter(card -> card.goal.equals(goal))
                .map(card -> new GoalCardHandle(card.getRoot()))
                .findFirst();
        return handle.orElseThrow(() -> new IllegalArgumentException("Goal does not exist."));
    }
}
