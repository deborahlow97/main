package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalGoals.getTypicalGoals;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysGoal;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.GoalCardHandle;
import guitests.guihandles.GoalListPanelHandle;
import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.goal.Goal;
import seedu.address.model.person.Person;

public class ListPanelTest extends GuiUnitTest {
    private static final ObservableList<Person> TYPICAL_PERSONS =
            FXCollections.observableList(getTypicalPersons());
    private static final ObservableList<Goal> TYPICAL_GOALS =
            FXCollections.observableList(getTypicalGoals());
    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND_PERSON);

    private PersonListPanelHandle personListPanelHandle;
    private GoalListPanelHandle goalListPanelHandle;

    @Before
    public void setUp() {
        ListPanel listPanel = new ListPanel(TYPICAL_PERSONS, TYPICAL_GOALS);
        uiPartRule.setUiPart(listPanel);

        personListPanelHandle = new PersonListPanelHandle(getChildNode(listPanel.getRoot(),
                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
        goalListPanelHandle = new GoalListPanelHandle(getChildNode(listPanel.getRoot(),
                GoalListPanelHandle.GOAL_LIST_VIEW_ID));
    }

    @Test
    public void display() {
        for (int i = 0; i < TYPICAL_PERSONS.size(); i++) {
            personListPanelHandle.navigateToCard(TYPICAL_PERSONS.get(i));
            Person expectedPerson = TYPICAL_PERSONS.get(i);
            PersonCardHandle actualPersonCard = personListPanelHandle.getPersonCardHandle(i);

            assertCardDisplaysPerson(expectedPerson, actualPersonCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualPersonCard.getId());
        }

//        for (int i = 0; i < TYPICAL_GOALS.size(); i++) {
//            goalListPanelHandle.navigateToCard(TYPICAL_GOALS.get(i));
//            Goal expectedGoal = TYPICAL_GOALS.get(i);
//            GoalCardHandle actualGoalCard = goalListPanelHandle.getGoalCardHandle(i);
//            assertCardDisplaysGoal(expectedGoal, actualGoalCard);
//            assertEquals(Integer.toString(i + 1) + ". ", actualGoalCard.getId());
//        }
    }

    @Test
    public void handleJumpToListRequestEvent() {
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        PersonCardHandle expectedCard = personListPanelHandle.getPersonCardHandle(INDEX_SECOND_PERSON.getZeroBased());
        PersonCardHandle selectedCard = personListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedCard, selectedCard);
    }
}
