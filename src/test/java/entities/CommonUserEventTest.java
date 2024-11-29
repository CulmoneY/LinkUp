package entities;

import entity.CommonUserEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserEventTest {

    private CommonUserEvent event;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String eventName;

    @BeforeEach
    public void setUp() {
        // Initialize common data for the tests
        eventName = "Sample Event";
        startTime = LocalDateTime.of(2024, 10, 1, 14, 0, 0, 0);  // October 1, 2024, 14:00
        endTime = LocalDateTime.of(2024, 10, 1, 16, 0, 0, 0);    // October 1, 2024, 16:00

        // Create a CommonUserEvent instance
        event = new CommonUserEvent(eventName, startTime, endTime);
    }

    @Test
    public void testGetEventName() {
        // Test that the event name is correctly set and retrieved
        assertEquals(eventName, event.getEventName(), "Event name should match.");
    }

    @Test
    public void testSetEventName() {
        // Set a new event name and verify that it was updated
        String newEventName = "Updated Event Name";
        event.setEventName(newEventName);
        assertEquals(newEventName, event.getEventName(), "Event name should be updated.");
    }

    @Test
    public void testGetStartTime() {
        // Test that the start time is correctly retrieved
        assertEquals(startTime, event.getStartTime(), "Start time should match.");
    }

    @Test
    public void testSetStartTime() {
        // Set a new start time and verify that it was updated
        LocalDateTime newStartTime = LocalDateTime.of(2024, 10, 2, 10, 0, 0, 0);  // October 2, 2024, 10:00
        event.setStartTime(newStartTime);
        assertEquals(newStartTime, event.getStartTime(), "Start time should be updated.");
    }

    @Test
    public void testGetEndTime() {
        // Test that the end time is correctly retrieved
        assertEquals(endTime, event.getEndTime(), "End time should match.");
    }

    @Test
    public void testSetEndTime() {
        // Set a new end time and verify that it was updated
        LocalDateTime newEndTime = LocalDateTime.of(2024, 10, 1, 18, 0, 0, 0);  // October 1, 2024, 18:00
        event.setEndTime(newEndTime);
        assertEquals(newEndTime, event.getEndTime(), "End time should be updated.");
    }

    @Test
    public void testToString() {
        // Test that the toString() method formats the event details correctly
        String expectedToString = "CommonUserEvent{" +
                "eventName='" + eventName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                "}";
        assertEquals(expectedToString, event.toString(), "The string representation should match.");
    }
}
