package entities;

import entity.CommonEventFactory;
import entity.CommonUserEvent;
import entity.CommonGroupEvent;
import entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommonEventFactoryTest {

    private final CommonEventFactory eventFactory = new CommonEventFactory();

    @Test
    void testCreateUserEvent() {
        // Prepare test data
        String eventName = "User Event";
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 10, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 10, 10, 12, 0);

        // Create a user event
        Event event = eventFactory.create(eventName, startTime, endTime, true);

        // Verify that the created event is a CommonUserEvent
        assertNotNull(event, "The event should not be null");
        assertTrue(event instanceof CommonUserEvent, "The event should be an instance of CommonUserEvent");

        // Verify event details
        assertEquals(eventName, event.getEventName(), "The event name should match");
        assertEquals(startTime, event.getStartTime(), "The start time should match");
        assertEquals(endTime, event.getEndTime(), "The end time should match");
    }

    @Test
    void testCreateGroupEvent() {
        // Prepare test data
        String eventName = "Group Event";
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 10, 14, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 10, 10, 16, 0);

        // Create a group event
        Event event = eventFactory.create(eventName, startTime, endTime, false);

        // Verify that the created event is a CommonGroupEvent
        assertNotNull(event, "The event should not be null");
        assertTrue(event instanceof CommonGroupEvent, "The event should be an instance of CommonGroupEvent");

        // Verify event details
        assertEquals(eventName, event.getEventName(), "The event name should match");
        assertEquals(startTime, event.getStartTime(), "The start time should match");
        assertEquals(endTime, event.getEndTime(), "The end time should match");
    }
}
