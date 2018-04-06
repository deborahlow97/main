package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysGoal;

import org.junit.Test;

import guitests.guihandles.GoalCardHandle;
import seedu.address.model.goal.Goal;
import seedu.address.testutil.GoalBuilder;

public class GoalCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Goal goal = new GoalBuilder().build();
        GoalCard goalCard = new GoalCard(goal, 1);
        uiPartRule.setUiPart(goalCard);
        assertCardDisplay(goalCard, goal, 1);

//        // with tags
//        Goal goalWithTags = new GoalBuilder().build();
//        goalCard = new GoalCard(goalWithTags, 2);
//        uiPartRule.setUiPart(goalCard);
//        assertCardDisplay(goalCard, goalWithTags, 2);
    }

    @Test
    public void equals() {
        Goal goal = new GoalBuilder().build();
        GoalCard goalCard = new GoalCard(goal, 0);

        // same goal, same index -> returns true
        GoalCard copy = new GoalCard(goal, 0);
        assertTrue(goalCard.equals(copy));

        // same object -> returns true
        assertTrue(goalCard.equals(goalCard));

        // null -> returns false
        assertFalse(goalCard.equals(null));

        // different types -> returns false
        assertFalse(goalCard.equals(0));

        // different goal, same index -> returns false
        Goal differentGoal = new GoalBuilder().withGoalText("differentGoalText").build();
        assertFalse(goalCard.equals(new GoalCard(differentGoal, 0)));

        // same goal, different index -> returns false
        assertFalse(goalCard.equals(new GoalCard(goal, 1)));
    }

    /**
     * Asserts that {@code goalCard} displays the details of {@code expectedGoal} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(GoalCard goalCard, Goal expectedGoal, int expectedId) {
        guiRobot.pauseForHuman();

        GoalCardHandle goalCardHandle = new GoalCardHandle(goalCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", goalCardHandle.getId());

        // verify goal details are displayed correctly
        assertCardDisplaysGoal(expectedGoal, goalCardHandle);
    }
}
