package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

/**
 *
 */
public class CalendarApp extends UiPart<Region> {

    private static final String FXML = "BrowserPanel.fxml";

    @FXML
    private CalendarView calendarView;

    public CalendarApp() {
        super(FXML);

        CalendarView calendarView = new CalendarView();



        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        //disableViews();
    }

    /**
     * Remove clutter from interface
     */
    private void disableViews() {
        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowSearchResultsTray(false);
        calendarView.setShowPrintButton(false);
        calendarView.showDayPage();
    }

    /**
     * Changes calendar view accordingly
     */
    private void showPage(Character c) {
        switch (c) {
        case ('d'):
            calendarView.showDayPage();
            return;
        case ('w'):
            calendarView.showWeekPage();
            return;
        case ('m'):
            calendarView.showMonthPage();
            return;
        case ('y'):
            calendarView.showYearPage();
            return;
        default:
            assert (false);
        }
    }

    private void updateReminderCalendar() {
        setTime();
        CalendarSource calendarSource = new CalendarSource("Reminders");
        Calendar birthdayss = new Calendar("Birthdays");
        Calendar holidays = new Calendar("Holidays");

        holidays.setStyle(Style.STYLE2);
    }

    private void updateBirthdayCalendar() {
        setTime();
        CalendarSource birthdays = new CalendarSource("Birthdays");
    }

    private void setTime() {
        calendarView.setToday(LocalDate.now());
        calendarView.setTime(LocalTime.now());
        calendarView.getCalendarSources().clear();
    }

}
