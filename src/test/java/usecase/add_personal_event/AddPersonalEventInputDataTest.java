package usecase.add_personal_event;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;
import usecases.add_personal_event.AddPersonalEventInputData;

import static org.junit.jupiter.api.Assertions.*;

public class AddPersonalEventInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English"; // New input data
        User user = new CommonUser("john_doe", "password123", language); // Initializing CommonUser with language

        // Creating the input data
        AddPersonalEventInputData inputData = new AddPersonalEventInputData(eventName, startTime, endTime, user);

        // Validate that the constructor correctly sets the fields
        assertEquals(eventName, inputData.getEventName(), "Event name should match the one passed in the constructor.");
        assertEquals(startTime, inputData.getStartTime(), "Start time should match the one passed in the constructor.");
        assertEquals(endTime, inputData.getEndTime(), "End time should match the one passed in the constructor.");
        assertEquals(user, inputData.getUser(), "User should match the one passed in the constructor.");
    }

    @Test
    public void testGetEventName() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the input data
        AddPersonalEventInputData inputData = new AddPersonalEventInputData(eventName, startTime, endTime, user);

        // Validate that getEventName returns the correct event name
        assertEquals(eventName, inputData.getEventName(), "getEventName should return the correct event name.");
    }

    @Test
    public void testGetStartTime() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the input data
        AddPersonalEventInputData inputData = new AddPersonalEventInputData(eventName, startTime, endTime, user);

        // Validate that getStartTime returns the correct start time
        assertEquals(startTime, inputData.getStartTime(), "getStartTime should return the correct start time.");
    }

    @Test
    public void testGetEndTime() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the input data
        AddPersonalEventInputData inputData = new AddPersonalEventInputData(eventName, startTime, endTime, user);

        // Validate that getEndTime returns the correct end time
        assertEquals(endTime, inputData.getEndTime(), "getEndTime should return the correct end time.");
    }

    @Test
    public void testGetUser() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the input data
        AddPersonalEventInputData inputData = new AddPersonalEventInputData(eventName, startTime, endTime, user);

        // Validate that getUser returns the correct user
        assertEquals(user, inputData.getUser(), "getUser should return the correct user.");
    }
}
