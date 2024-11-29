package entities;

import entity.CommonCalendar;
import entity.CommonCalendarFactory;
import entity.Calendar;
import entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonCalendarFactoryTest {

    private CommonCalendarFactory calendarFactory;

    @BeforeEach
    void setUp() {
        // Setup before each test
        calendarFactory = new CommonCalendarFactory();
    }

    @Test
    void testCreate() {
        String calendarName = "Test Calendar";
        Calendar calendar = calendarFactory.create(calendarName);

        // Verify that the created calendar is not null
        assertNotNull(calendar, "The created calendar should not be null");

        // Verify that the calendar has the correct name
        assertEquals(calendarName, calendar.getName(), "The calendar name should match the name passed to the factory");

        // Verify that the calendar's events list is initialized and empty
        assertNotNull(calendar.getEvents(), "The events list should not be null");
        assertTrue(calendar.getEvents().isEmpty(), "The events list should be empty initially");
    }
}
