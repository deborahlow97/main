package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a RC4 resident Unit Number in CollegeZone.
 * Guarantees: immutable; is valid as declared in {@link #isUnitNumber(String)}
 */
public class UnitNumber {

    public static final String MESSAGE_UNIT_NUMBER_CONSTRAINTS =
            "Unit Number should contain #, - and alphanumerical values.";
    public static final String UNIT_NUMBER_VALIDATION_REGEX = "[a-zA-Z\\d\\s\\-\\,\\#\\.\\+]+";
    public final String value;

    /**
     * * Constructs an {@code UnitNumber}.
     *
     * @param unitNumber A valid unit number.
     */
    public UnitNumber(String unitNumber) {
        requireNonNull(unitNumber);
        checkArgument(isValidUnitNumber(unitNumber), MESSAGE_UNIT_NUMBER_CONSTRAINTS);
        this.value = unitNumber;
    }

    /**
     * Returns true if a given string is a valid unit number.
     */
    public static boolean isValidUnitNumber(String test) {

        return test.matches(UNIT_NUMBER_VALIDATION_REGEX) && isAnIntegerWithinRange(test);
    }

    /**
     * Returns if a given string is an integer and within range of level of friendship.
     */
    private static boolean isAnIntegerWithinRange(String test) {
        levelOfFriendshipInIntegerForm = Integer.parseInt(test);
        if (levelOfFriendshipInIntegerForm >= MINIMUM_LEVEL_OF_FRIENDSHIP
                && levelOfFriendshipInIntegerForm <= MAXIMUM_LEVEL_OF_FRIENDSHIP) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && this.value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
