package usecases.export_calendar;

import entity.CommonEventFactory;
import entity.CommonCalendarFactory;
import entity.Calendar;
import entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestICSFormatter {

    @Test
    public void testFormatWithEvents() {
        CommonEventFactory eventFactory = new CommonEventFactory();
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();

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
}
