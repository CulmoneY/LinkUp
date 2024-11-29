package entities;

import entity.CommonCalendar;
import entity.CommonGroupEvent;
import entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonCalendarTest {

    private CommonCalendar calendar;
    private List<Event> events;

    @BeforeEach
    void setUp() {
        // Setup initial data before each test
        events = new ArrayList<>();
        calendar = new CommonCalendar("Test Calendar", events);
    }

    @Test
    void testGetName() {
        assertEquals("Test Calendar", calendar.getName(), "The calendar name should be 'Test Calendar'");
    }

    @Test
    void testSetName() {
        calendar.setName("Updated Calendar");
        assertEquals("Updated Calendar", calendar.getName(), "The calendar name should be updated to 'Updated Calendar'");
    }

    @Test
    void testGetEvents() {
        assertEquals(events, calendar.getEvents(), "The events list should match the one in the calendar");
    }

    @Test
    void testSetEvents() {
        List<Event> newEvents = new ArrayList<>();
        calendar.setEvents(newEvents);
        assertEquals(newEvents, calendar.getEvents(), "The events list should be updated to the new list");
    }

    @Test
    void testAddEvent() {
        Event event = new CommonGroupEvent("Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        calendar.addEvent(event);
        assertTrue(calendar.getEvents().contains(event), "The event should be added to the calendar");
    }

    @Test
    void testRemoveEvent() {
        Event event = new CommonGroupEvent("Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        calendar.addEvent(event);
        calendar.removeEvent(event);
        assertFalse(calendar.getEvents().contains(event), "The event should be removed from the calendar");
    }
}
