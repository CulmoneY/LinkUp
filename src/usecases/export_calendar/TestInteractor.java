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
                LocalDateTime.of(2024, 11, 20, 10, 0),
                LocalDateTime.of(2024, 11, 20, 11, 0), false);
        User user = userFactory.create("Test User", "Test Password", "English");

        calendar.addEvent(event);
        user.setUserCalendar(calendar);

        ExportCalendarOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        // Act
        interactor.execute(inputData);

        // Assert
        TestOutputBoundary testBoundary = (TestOutputBoundary) outputBoundary;
        assertTrue(testBoundary.isSuccessful, "Export should be successful.");
        assertNotNull(testBoundary.filePath, "File path should not be null for successful export.");
    }

    @Test
    public void testExecuteWithEmptyCalendar() {
        // Arrange
        Calendar calendar = new Calendar(Collections.emptyList());
        User user = new User("Test User", calendar);
        ExportCalendarOutputBoundary outputBoundary = new TestOutputBoundary();
        ExportCalendarInteractor interactor = new ExportCalendarInteractor(outputBoundary);
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        // Act
        interactor.execute(inputData);

        // Assert
        TestOutputBoundary testBoundary = (TestOutputBoundary) outputBoundary;
        assertFalse(testBoundary.isSuccessful, "Export should fail due to empty calendar.");
        assertEquals("No events to export.", testBoundary.message, "Correct failure message should be returned.");
    }

    // Helper class to capture output for assertions
    private static class TestOutputBoundary implements ExportCalendarOutputBoundary {
        boolean isSuccessful = false;
        String message = null;
        String filePath = null;

        @Override
        public void exportSuccess(ExportCalendarOutputData data) {
            this.isSuccessful = true;
            this.filePath = data.getFilePath();
        }

        @Override
        public void exportFail(String error) {
            this.isSuccessful = false;
            this.message = error;
        }
    }
}