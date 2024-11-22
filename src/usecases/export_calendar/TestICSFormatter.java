package usecases.export_calendar;

import entity.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestICSFormatter {

    @Test
    public void testFormatWithEvents() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonEventFactory eventFactory = new CommonEventFactory();

        Calendar calendar = calendarFactory.create("Test Calendar");
        Event event = eventFactory.create("Meeting",
                LocalDateTime.of(2024, 11, 20, 10, 0),
                LocalDateTime.of(2024, 11, 20, 11, 0), false);

        calendar.addEvent(event);

        String icsContent = ICSFormatter.format(calendar);

        assertTrue(icsContent.contains("BEGIN:VEVENT"), "ICS content should include an event.");
        assertTrue(icsContent.contains("SUMMARY:Meeting"), "ICS content should include the event summary.");
    }

    @Test
    public void testFormatWithEmptyCalendar() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();

        Calendar calendar = calendarFactory.create("Test Calendar");
        String icsContent = ICSFormatter.format(calendar);

        assertTrue(icsContent.contains("BEGIN:VCALENDAR"), "ICS content should start with a calendar.");
        assertTrue(icsContent.contains("END:VCALENDAR"), "ICS content should end with a calendar.");
    }

    @Test
    public void testFormatWithNoCalendar() {
        String icsContent = ICSFormatter.format(null);

        assertTrue(icsContent.contains("BEGIN:VCALENDAR"), "ICS content should start with a calendar.");
        assertTrue(icsContent.contains("END:VCALENDAR"), "ICS content should end with a calendar.");
        assertFalse(icsContent.contains("BEGIN:VEVENT"),
                "ICS content should not include any events for a null calendar.");
    }
}
