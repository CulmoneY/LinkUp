package entities;

import entity.CommonGroupEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommonGroupEventTest {

    private CommonGroupEvent event;

    @BeforeEach
    void setUp() {
        // Setting up the event for testing
        event = new CommonGroupEvent(
                "Team Meeting",
                LocalDateTime.of(2024, 10, 1, 10, 0),
                LocalDateTime.of(2024, 10, 1, 11, 0)
        );
    }

    @Test
    void testEventName() {
        // Testing the event name
        assertEquals("Team Meeting", event.getEventName(), "The event name should be 'Team Meeting'.");
    }

    @Test
    void testEventStartTime() {
        // Testing the event start time
        LocalDateTime expectedStartTime = LocalDateTime.of(2024, 10, 1, 10, 0);
        assertEquals(expectedStartTime, event.getStartTime(), "The start time should be 2024-10-01T10:00.");
    }

    @Test
    void testEventEndTime() {
        // Testing the event end time
        LocalDateTime expectedEndTime = LocalDateTime.of(2024, 10, 1, 11, 0);
        assertEquals(expectedEndTime, event.getEndTime(), "The end time should be 2024-10-01T11:00.");
    }

    @Test
    void testSetEventName() {
        // Testing the setter for event name
        event.setEventName("Project Review");
        assertEquals("Project Review", event.getEventName(), "The event name should be updated to 'Project Review'.");
    }

    @Test
    void testSetStartTime() {
        // Testing the setter for start time
        LocalDateTime newStartTime = LocalDateTime.of(2024, 10, 2, 9, 0);
        event.setStartTime(newStartTime);
        assertEquals(newStartTime, event.getStartTime(), "The start time should be updated to 2024-10-02T09:00.");
    }

    @Test
    void testSetEndTime() {
        // Testing the setter for end time
        LocalDateTime newEndTime = LocalDateTime.of(2024, 10, 2, 10, 0);
        event.setEndTime(newEndTime);
        assertEquals(newEndTime, event.getEndTime(), "The end time should be updated to 2024-10-02T10:00.");
    }

    @Test
    void testToString() {
        // Testing the toString method
        String expectedString = "CommonUserEvent{eventName='Team Meeting', startTime=2024-10-01T10:00, endTime=2024-10-01T11:00}";
        assertEquals(expectedString, event.toString(), "The string representation of the event should match the expected format.");
    }

}
