package usecases.add_personal_event;

import entity.Event;
import entity.User;
import entity.CommonUser;
import entity.CommonCalendar;
import entity.EventFactory;
import entity.CommonEventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import usecases.add_personal_event.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class AddPersonalEventInteractorTest {

    private AddPersonalEventInteractor interactor;
    private AddPersonalEventDataAccessInterface dataAccess;
    private AddPersonalEventOutputBoundary outputBoundary;
    private EventFactory eventFactory;

    // Mock-like classes to simulate real dependencies
    class MockAddPersonalEventDataAccess implements AddPersonalEventDataAccessInterface {
        @Override
        public void addEvent(User user, Event event) {
            // Simulate adding the event (No real DB)
        }

        @Override
        public void addGroupEvent(String groupname, Event event) {
            // Simulate adding the event to a group (No real DB)
        }
    }

    class MockAddPersonalEventOutput implements AddPersonalEventOutputBoundary {
        private String failView;
        private AddPersonalEventOutputData outputData;

        @Override
        public void setFailView(String errorType) {
            this.failView = errorType;
        }

        @Override
        public void setPassView(AddPersonalEventOutputData outputData) {
            this.outputData = outputData;
        }

        public String getFailView() {
            return failView;
        }

        public AddPersonalEventOutputData getPassView() {
            return outputData;
        }
    }

    @BeforeEach
    void setUp() {
        // Initialize the mock dependencies
        dataAccess = new MockAddPersonalEventDataAccess();
        outputBoundary = new MockAddPersonalEventOutput();
        eventFactory = new CommonEventFactory();  // Use a real event factory

        // Initialize the interactor with mock dependencies
        interactor = new AddPersonalEventInteractor(dataAccess, outputBoundary, eventFactory);
    }

    private User createTestUser() {
        // Create a new user for testing purposes
        User user = new CommonUser("user1", "password123", "English");

        // Create an empty list of events
        List<Event> events = new ArrayList<>();
        // Create a CommonCalendar with an empty list of events
        CommonCalendar calendar = new CommonCalendar(user.getName() + "'s Calendar", events);
        user.setUserCalendar(calendar); // Set the user's calendar

        return user;
    }

    @Nested
    class AddPersonalEventTests {

        @Test
        void testMissingFields_EndTimeEmpty() {
            // Prepare input data with an empty end time
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "2024-12-12 10:00", "", user);

            // Execute the interactor
            interactor.executeCreate(inputData);

            // Verify the fail view is set correctly
            assertEquals("Fill in all Fields!", ((MockAddPersonalEventOutput) outputBoundary).getFailView());
        }

        @Test
        void testValidTime_ReturnsFalseWhenNull() {
            // Prepare input data with invalid time format that results in null parsed times
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "invalid_start_time", "invalid_end_time", user);

            // Execute the interactor
            interactor.executeCreate(inputData);

            // Verify the fail view is set correctly for invalid time
            assertEquals("Invalid Time Format!", ((MockAddPersonalEventOutput) outputBoundary).getFailView());
        }

        @Test
        void testParseDateTime_CatchesException() {
            // Prepare input data with an invalid date format to trigger the exception
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "invalid_date", "2024-12-12 12:00", user);

            // Execute the interactor
            interactor.executeCreate(inputData);

            // Verify the fail view is set for the invalid start time
            assertEquals("Invalid Time Format!", ((MockAddPersonalEventOutput) outputBoundary).getFailView());
        }

        @Test
        void testSuccessfulEventCreation() {
            // Prepare input data for a valid event creation
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "2024-12-12 10:00", "2024-12-12 12:00", user);

            // Execute the interactor
            interactor.executeCreate(inputData);

            // Verify that the pass view is set correctly
            AddPersonalEventOutputData outputData = ((MockAddPersonalEventOutput) outputBoundary).getPassView();
            assertNotNull(outputData);
            assertEquals("Meeting", outputData.getEventName());
            assertEquals("2024-12-12T10:00", outputData.getStartTime());
            assertEquals("2024-12-12T12:00", outputData.getEndTime());
        }
    }
}
