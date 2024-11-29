package usecase.add_personal_event;

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
        void testMissingFields() {
            // Prepare input data with missing fields (event name is empty)
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("", "2024-12-12 10:00", "2024-12-12 12:00", user);

            // Execute the interactor
            interactor.executeCreate(inputData);

            // Verify the fail view is set correctly
            assertEquals("Fill in all Fields!", ((MockAddPersonalEventOutput) outputBoundary).getFailView());
        }

        @Test
        void testInvalidTimeFormat() {
            // Prepare input data with invalid time (end time before start time)
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "2024-12-12 10:00", "2024-12-12 09:00", user);

            // Execute the interactor
            interactor.executeCreate(inputData);

            // Verify the fail view is set correctly for invalid time
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
