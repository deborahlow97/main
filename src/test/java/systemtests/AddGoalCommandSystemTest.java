package systemtests;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.GoalCommandTestUtil.GOAL_TEXT_DESC_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.GOAL_TEXT_DESC_B;
import static seedu.address.logic.commands.GoalCommandTestUtil.INVALID_GOAL_TEXT_DESC;
import static seedu.address.logic.commands.GoalCommandTestUtil.INVALID_IMPORTANCE_DESC;
import static seedu.address.logic.commands.GoalCommandTestUtil.GOAL_IMPORTANCE_DESC_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.GOAL_IMPORTANCE_DESC_B;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_COMPLETION_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_COMPLETION_B;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_END_DATE_TIME_STRING_B;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_IMPORTANCE_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_START_DATE_TIME_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_START_DATE_TIME_B;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_END_DATE_TIME_STRING_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_START_DATE_TIME_STRING_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_START_DATE_TIME_STRING_B;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_TEXT_A;
import static seedu.address.logic.commands.GoalCommandTestUtil.VALID_GOAL_TEXT_B;
import static seedu.address.testutil.TypicalGoals.GOAL_G;
import static seedu.address.testutil.TypicalGoals.GOAL_A;
import static seedu.address.testutil.TypicalGoals.GOAL_B;
import static seedu.address.testutil.TypicalGoals.GOAL_C;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddGoalCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.goal.Completion;
import seedu.address.model.goal.Goal;
import seedu.address.model.goal.Importance;
import seedu.address.model.goal.EndDateTime;
import seedu.address.model.goal.StartDateTime;
import seedu.address.model.goal.GoalText;
import seedu.address.model.goal.exceptions.DuplicateGoalException;
import seedu.address.testutil.GoalBuilder;
import seedu.address.testutil.GoalUtil;

public class AddGoalCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void add() throws Exception {
        Model model = getModel();

        /* ------------------------ Perform add operations on the shown unfiltered list ----------------------------- */

        /* Case: add a goal to a non-empty CollegeZone, command with leading spaces and trailing spaces
         * -> added
         */
        Goal toAdd = GOAL_A;
        String command = "   " + AddGoalCommand.COMMAND_WORD + "  " + GOAL_TEXT_DESC_A + "  "
                + GOAL_IMPORTANCE_DESC_A + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: undo adding Goal A to the list -> Goal A deleted */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: redo adding Goal A to the list -> Goal A added again */
        command = RedoCommand.COMMAND_WORD;
        model.addGoal(toAdd);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        //BUG TODO
        /* Case: add a goal with all fields same as another goal in the CollegeZone except name -> added */
        toAdd = new GoalBuilder().withGoalText(VALID_GOAL_TEXT_B).withImportance(VALID_GOAL_IMPORTANCE_A)
                .withCompletion(VALID_GOAL_COMPLETION_A).withStartDateTime(VALID_GOAL_START_DATE_TIME_STRING_A)
                .withEndDateTime(VALID_GOAL_END_DATE_TIME_STRING_B).build();
        command = AddGoalCommand.COMMAND_WORD + GOAL_TEXT_DESC_B + GOAL_IMPORTANCE_DESC_A;
        assertCommandSuccess(command, toAdd);
//
//        /* Case: add a goal with all fields same as another goal in the CollegeZone except phone -> added */
//        toAdd = new GoalBuilder().withName(VALID_GOAL_END_DATE_TIME_A).withPhone(VALID_PHONE_GOAL_B).withBirthday(VALID_BIRTHDAY_GOAL_A)
//                .withLevelOfFriendship(VALID_GOAL_START_DATE_TIME).withUnitNumber(VALID_UNIT_NUMBER_GOAL_A)
//                .withCcas(VALID_CCA_DANCE).withTags(VALID_TAG_FRIEND).build();
//        command = AddGoalCommand.COMMAND_WORD + NAME_DESC_GOAL_A + PHONE_DESC_GOAL_B + BIRTHDAY_DESC_GOAL_A
//                + LEVEL_OF_FRIENDSHIP_DESC_GOAL_A + UNIT_NUMBER_DESC_GOAL_A + CCA_DESC_DANCE
//                + TAG_DESC_FRIEND;
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: add a goal with all fields same as another goal in the CollegeZone except birthday -> added */
//        toAdd = new GoalBuilder().withName(VALID_GOAL_END_DATE_TIME_A).withPhone(VALID_PHONE_GOAL_A).withBirthday(VALID_GOAL_COMPLETION_B)
//                .withLevelOfFriendship(VALID_GOAL_START_DATE_TIME).withUnitNumber(VALID_UNIT_NUMBER_GOAL_A)
//                .withCcas(VALID_CCA_DANCE).withTags(VALID_TAG_FRIEND).build();
//        command = AddGoalCommand.COMMAND_WORD + NAME_DESC_GOAL_A + PHONE_DESC_GOAL_A + BIRTHDAY_DESC_GOAL_B
//                + LEVEL_OF_FRIENDSHIP_DESC_GOAL_A + UNIT_NUMBER_DESC_GOAL_A + CCA_DESC_DANCE
//                + TAG_DESC_FRIEND;
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: add a goal with all fields same as another goal in the CollegeZone except level of
//           friendship -> added */
//        toAdd = new GoalBuilder().withName(VALID_GOAL_END_DATE_TIME_A).withPhone(VALID_PHONE_GOAL_A).withBirthday(VALID_BIRTHDAY_GOAL_A)
//                .withLevelOfFriendship(VALID_GOAL_START_DATE_TIME_B).withUnitNumber(VALID_UNIT_NUMBER_GOAL_A)
//                .withCcas(VALID_CCA_DANCE).withTags(VALID_TAG_FRIEND).build();
//        command = AddGoalCommand.COMMAND_WORD + NAME_DESC_GOAL_A + PHONE_DESC_GOAL_A + BIRTHDAY_DESC_GOAL_A
//                + LEVEL_OF_FRIENDSHIP_DESC_GOAL_B + UNIT_NUMBER_DESC_GOAL_A + CCA_DESC_DANCE
//                + TAG_DESC_FRIEND;
//        assertCommandSuccess(command, toAdd);
//
//        /* Case: add a goal with all fields same as another goal in the CollegeZone except unit
//           number -> added */
//        toAdd = new GoalBuilder().withName(VALID_GOAL_END_DATE_TIME_A).withPhone(VALID_PHONE_GOAL_A).withBirthday(VALID_BIRTHDAY_GOAL_A)
//                .withLevelOfFriendship(VALID_GOAL_START_DATE_TIME).withUnitNumber(VALID_UNIT_NUMBER_GOAL_B)
//                .withCcas(VALID_CCA_DANCE).withTags(VALID_TAG_FRIEND).build();
//        command = AddGoalCommand.COMMAND_WORD + NAME_DESC_GOAL_A + PHONE_DESC_GOAL_A + BIRTHDAY_DESC_GOAL_A
//                + LEVEL_OF_FRIENDSHIP_DESC_GOAL_A + UNIT_NUMBER_DESC_GOAL_B + CCA_DESC_DANCE
//                + TAG_DESC_FRIEND;
//        assertCommandSuccess(command, toAdd);

        /* Case: add to empty CollegeZone -> added */
        deleteAllGoals();
        assertCommandSuccess(GOAL_G);

        deleteAllGoals();
        //BUGGED
        /* Case: add a goal with tags, command with parameters in random order -> added */
        toAdd = GOAL_B;
        command = AddGoalCommand.COMMAND_WORD + GOAL_TEXT_DESC_B + GOAL_IMPORTANCE_DESC_B;
        assertCommandSuccess(command, toAdd);

        deleteAllGoals();

        /* -------------------------- Perform add operation on the shown filtered list ------------------------------ */

        /* Case: filters the goal list before adding -> added */

        //TODO
        /* ------------------------ Perform add operation while a goal card is selected --------------------------- */
        /* Case: selects first card in the goal list, add a goal -> added, card selection remains unchanged */
        //selectGoal(Index.fromOneBased(1));
        //assertCommandSuccess(CARL);
        assertCommandSuccess(GOAL_G);

        /* ----------------------------------- Perform invalid add operations --------------------------------------- */

        /* Case: add a duplicate goal -> rejected */
        command = GoalUtil.getAddGoalCommand(GOAL_G);
        assertCommandFailure(command, AddGoalCommand.MESSAGE_DUPLICATE_GOAL);


        /* Case: missing goal text -> rejected */
        command = AddGoalCommand.COMMAND_WORD + GOAL_IMPORTANCE_DESC_A;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGoalCommand.MESSAGE_USAGE));

        /* Case: missing goal importance -> rejected */
        command = AddGoalCommand.COMMAND_WORD + GOAL_TEXT_DESC_A;
        assertCommandFailure(command, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGoalCommand.MESSAGE_USAGE));


        /* Case: invalid keyword -> rejected */
        command = "addsgoal " + GoalUtil.getGoalDetails(toAdd);
        assertCommandFailure(command, Messages.MESSAGE_UNKNOWN_COMMAND);

        /* Case: invalid goal text -> rejected */
        command = AddGoalCommand.COMMAND_WORD + INVALID_GOAL_TEXT_DESC + GOAL_IMPORTANCE_DESC_A;
        assertCommandFailure(command, GoalText.MESSAGE_GOAL_TEXT_CONSTRAINTS);

        /* Case: invalid goal importance -> rejected */
        command = AddGoalCommand.COMMAND_WORD + GOAL_TEXT_DESC_A + INVALID_IMPORTANCE_DESC;
        assertCommandFailure(command, Importance.MESSAGE_IMPORTANCE_CONSTRAINTS);

    }


    /**
     * Executes the {@code AddGoalCommand} that adds {@code toAdd} to the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddGoalCommand} with the details of
     * {@code toAdd}.<br>
     * 4. {@code Model}, {@code Storage} and {@code GoalListPanel} equal to the corresponding components in
     * the current model added with {@code toAdd}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(Goal toAdd) {
        assertCommandSuccess(GoalUtil.getAddGoalCommand(toAdd), toAdd);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(Goal)}. Executes {@code command}
     * instead.
     * @see AddGoalCommandSystemTest#assertCommandSuccess(Goal)
     */
    private void assertCommandSuccess(String command, Goal toAdd) {
        Model expectedModel = getModel();
        try {
            expectedModel.addGoal(toAdd);
        } catch (DuplicateGoalException dpe) {
            throw new IllegalArgumentException("toAdd already exists in the model.");
        }
        String expectedResultMessage = String.format(AddGoalCommand.MESSAGE_SUCCESS, toAdd);

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Goal)} except asserts that
     * the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Model}, {@code Storage} and {@code GoalListPanel} equal to the corresponding components in
     * {@code expectedModel}.<br>
     * @see AddGoalCommandSystemTest#assertCommandSuccess(String, Goal)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpectedGoals("", expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Model}, {@code Storage} and {@code GoalListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}

