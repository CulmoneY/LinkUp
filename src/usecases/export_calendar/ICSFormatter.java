package usecases.export_calendar;

import entity.Calendar;
import entity.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ICSFormatter {

    public static String format(Calendar calendar) {

        if (calendar == null || calendar.getEvents().isEmpty()) {
            return "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//LinkUp//EN\nEND:VCALENDAR";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCALENDAR\n");
        sb.append("VERSION:2.0\n");
        sb.append("PRODID:-//LinkUp//EN\n");

        for (Event event : calendar.getEvents()) {
            sb.append("BEGIN:VEVENT\n");
            sb.append("SUMMARY:").append(event.getEventName()).append("\n");
            sb.append("DTSTART:").append(formatDateTime(event.getStartTime())).append("\n");
            sb.append("DTEND:").append(formatDateTime(event.getEndTime())).append("\n");
            sb.append("END:VEVENT\n");
        }

        sb.append("END:VCALENDAR");
        return sb.toString();
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
    }
}
