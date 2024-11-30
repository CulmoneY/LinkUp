package usecases.add_personal_event;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;
import usecases.add_personal_event.AddPersonalEventOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class AddPersonalEventOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English"; // New input data
        User user = new CommonUser("john_doe", "password123", language); // Initializing CommonUser with language

        // Creating the output data
        AddPersonalEventOutputData outputData = new AddPersonalEventOutputData(eventName, startTime, endTime);

        // Validate that the constructor correctly sets the fields
        assertEquals(eventName, outputData.getEventName(), "Event name should match the one passed in the constructor.");
        assertEquals(startTime, outputData.getStartTime(), "Start time should match the one passed in the constructor.");
        assertEquals(endTime, outputData.getEndTime(), "End time should match the one passed in the constructor.");
    }

    @Test
    public void testGetEventName() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the output data
        AddPersonalEventOutputData outputData = new AddPersonalEventOutputData(eventName, startTime, endTime);

        // Validate that getEventName returns the correct event name
        assertEquals(eventName, outputData.getEventName(), "getEventName should return the correct event name.");
    }

    @Test
    public void testGetStartTime() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the output data
        AddPersonalEventOutputData outputData = new AddPersonalEventOutputData(eventName, startTime, endTime);

        // Validate that getStartTime returns the correct start time
        assertEquals(startTime, outputData.getStartTime(), "getStartTime should return the correct start time.");
    }

    @Test
    public void testGetEndTime() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the output data
        AddPersonalEventOutputData outputData = new AddPersonalEventOutputData(eventName, startTime, endTime);

        // Validate that getEndTime returns the correct end time
        assertEquals(endTime, outputData.getEndTime(), "getEndTime should return the correct end time.");
    }

    @Test
    public void testGetSuccess() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 10:00";
        String endTime = "2024-11-30 11:00";
        String language = "English";
        User user = new CommonUser("john_doe", "password123", language);

        // Creating the output data
        AddPersonalEventOutputData outputData = new AddPersonalEventOutputData(eventName, startTime, endTime);

        // Since success isn't set in the constructor, assume a default state of false
        assertFalse(outputData.getSuccess(), "getSuccess should return the default value of false.");
    }
}
