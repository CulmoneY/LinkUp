package usecases.export_calendar;

import entity.CommonCalendarFactory;
import entity.CommonEventFactory;
import entity.CommonUserFactory;
import entity.Calendar;
import entity.Event;
import entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class TestInteractor {

    @Test
    public void testExecuteWithEvents() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonEventFactory eventFactory = new CommonEventFactory();
        CommonUserFactory userFactory = new CommonUserFactory();

        Calendar calendar = calendarFactory.create("Test Calendar");
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
    public void testExecuteWithEmptyCalendar() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonUserFactory userFactory = new CommonUserFactory();

        Calendar calendar = calendarFactory.create("Test Calendar");
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