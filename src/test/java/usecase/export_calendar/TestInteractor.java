package usecase.export_calendar;

import entity.CommonCalendarFactory;
import entity.CommonEventFactory;
import entity.CommonUserFactory;
import entity.CommonGroupFactory;
import entity.Calendar;
import entity.Event;
import entity.User;
import entity.Group;
import org.junit.jupiter.api.Test;
import usecases.export_calendar.ExportCalendarInputData;
import usecases.export_calendar.ExportCalendarInteractor;
import usecases.export_calendar.ExportCalendarOutputBoundary;
import usecases.export_calendar.ExportCalendarOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestInteractor {

    @Test
    public void testExecuteUserWithEvents() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonEventFactory eventFactory = new CommonEventFactory();
        CommonUserFactory userFactory = new CommonUserFactory();

        Calendar calendar = calendarFactory.create("Test User Calendar");
        Event event = eventFactory.create("Meeting",
                LocalDateTime.of(2024, 11, 21, 10, 0),
                LocalDateTime.of(2024, 11, 21, 11, 0), false);
        User user = userFactory.create("Test User", "Test Password", "English");

        calendar.addEvent(event);
        user.setUserCalendar(calendar);

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        interactor.execute(inputData);

        assertTrue(outputBoundary.isSuccessful, "Export successful.");
        assertNotNull(outputBoundary.filePath, "File path should not be null for successful export.");
    }

    @Test
    public void testExecuteUserWithEmptyCalendar() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonUserFactory userFactory = new CommonUserFactory();

        Calendar calendar = calendarFactory.create("Test User Calendar");
        User user = userFactory.create("Test User", "Test Password", "English");

        user.setUserCalendar(calendar);

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        interactor.execute(inputData);

        assertFalse(outputBoundary.isSuccessful, "Calendar has no events to export.");
        assertEquals("Calendar has no events to export.", outputBoundary.message,
                "Correct failure message should be returned.");
    }

    @Test
    public void testExecuteUserWithNoCalendar() {
        CommonUserFactory userFactory = new CommonUserFactory();

        User user = userFactory.create("Test User", "Test Password", "English");

        user.setUserCalendar(null);

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        interactor.execute(inputData);

        assertFalse(outputBoundary.isSuccessful, "Calendar has no events to export.");
        assertEquals("Calendar has no events to export.", outputBoundary.message,
                "Correct failure message should be returned.");
    }

    @Test
    public void testExecuteGroupWithEvents() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonEventFactory eventFactory = new CommonEventFactory();
        CommonGroupFactory groupFactory = new CommonGroupFactory();

        Calendar calendar = calendarFactory.create("Test Group Calendar");
        Event event = eventFactory.create("Meeting",
                LocalDateTime.of(2024, 11, 21, 10, 0),
                LocalDateTime.of(2024, 11, 21, 11, 0), true);
        Group group = groupFactory.create("Test Group", new ArrayList<>());

        calendar.addEvent(event);
        group.setGroupCalendar(calendar);

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(group);

        interactor.execute(inputData);

        assertTrue(outputBoundary.isSuccessful, "Export successful.");
        assertNotNull(outputBoundary.filePath, "File path should not be null for successful export.");
    }

    @Test
    public void testExecuteGroupWithEmptyCalendar() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonGroupFactory groupFactory = new CommonGroupFactory();

        Calendar calendar = calendarFactory.create("Test Group Calendar");
        Group group = groupFactory.create("Test Group", new ArrayList<>());

        group.setGroupCalendar(calendar);

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(group);

        interactor.execute(inputData);

        assertFalse(outputBoundary.isSuccessful, "Calendar has no events to export.");
        assertEquals("Calendar has no events to export.", outputBoundary.message,
                "Correct failure message should be returned.");
    }

    @Test
    public void testExecuteGroupWithNoCalendar() {
        CommonGroupFactory groupFactory = new CommonGroupFactory();

        Group group = groupFactory.create("Test Group", new ArrayList<>());

        group.setGroupCalendar(null);

        TestOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(group);

        interactor.execute(inputData);
        assertFalse(outputBoundary.isSuccessful, "Calendar has no events to export.");
        assertEquals("Calendar has no events to export.", outputBoundary.message,
                "Correct failure message should be returned.");
    }

    // Helper class to capture output for assertions
    private static class TestOutputBoundary implements ExportCalendarOutputBoundary {
        boolean isSuccessful = false;
        String message = null;
        String filePath = null;

        @Override
        public void exportSuccess(ExportCalendarOutputData calendar) {
            this.isSuccessful = true;
            this.filePath = calendar.getFilePath();
        }

        @Override
        public void exportFail(String error) {
            this.isSuccessful = false;
            this.message = error;
        }
    }
}