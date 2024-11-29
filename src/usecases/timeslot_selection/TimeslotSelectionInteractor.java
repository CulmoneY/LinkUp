package usecases.timeslot_selection;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

import entity.Calendar;
import entity.Event;
import entity.EventFactory;
import entity.Group;
import entity.CommonGroupEvent;

public class TimeslotSelectionInteractor implements TimeslotSelectionInputBoundary {

    final TimeslotSelectionDataAccessInterface timeslotdataAccess;
    public final TimeslotSelectionOutputBoundary timeslotpresenter;
    final EventFactory eventfactory;

    public TimeslotSelectionInteractor(TimeslotSelectionDataAccessInterface timeslotdataAccess,
                                       TimeslotSelectionOutputBoundary timeslotpresenter, EventFactory eventfactory) {
        this.timeslotdataAccess = timeslotdataAccess;
        this.timeslotpresenter = timeslotpresenter;
        this.eventfactory = eventfactory;
    }

    public void selectTimeslot(List<Event> events) {
        LocalTime startTime = LocalTime.of(9, 0);  // 9:00 AM
        LocalTime endTime = LocalTime.of(22, 0);   // 10:00 PM
        TemporalAmount duration_mins = Duration.ofMinutes(120);  // 2-hour event duration

        // Get current datetime
        LocalDateTime now = LocalDateTime.now();

        // Start checking for available timeslots from the current day
        LocalDateTime dayStart = now.with(startTime);
        LocalDateTime dayEnd = now.with(endTime);

        // Search for the closest available timeslot
        LocalDateTime closest = findAvailableSlotForDay(events, dayStart, dayEnd, duration_mins);

        // Keep checking subsequent days until an available slot is found
        while (closest == null) {
            now = now.plusDays(1);  // Move to the next day
            dayStart = now.with(startTime);
            dayEnd = now.with(endTime);

            closest = findAvailableSlotForDay(events, dayStart, dayEnd, duration_mins);
        }

        // After finding the closest available slot, create an event and pass it to the presenter
        if (closest != null) {
            // Create the event using the closest timeslot found
            LocalDateTime eventEndTime = closest.plus(duration_mins);
            Event newEvent = new CommonGroupEvent("New Event", closest, eventEndTime);

            // Pass the new event to the presenter (TimeslotSelectionOutputBoundary)
            timeslotpresenter.setPassView(new TimeslotSelectionOutputData(newEvent));
        } else {
            // If no timeslot found (should not happen per the new logic), return a "no timeslot available" event
            Event noSlotEvent = new CommonGroupEvent("No Available Slot", now, now);
            timeslotpresenter.setPassView(new TimeslotSelectionOutputData(noSlotEvent));
        }
    }

    private LocalDateTime findAvailableSlotForDay(List<Event> events, LocalDateTime dayStart, LocalDateTime dayEnd, TemporalAmount duration_mins) {
        LocalDateTime closest = null;

        // Search through available timeslots on the given day
        for (LocalDateTime time = dayStart; time.plus(duration_mins).isBefore(dayEnd); time = time.plusMinutes(15)) {
            // Check if the proposed timeslot overlaps with any event
            boolean overlaps = false;
            for (Event event : events) {
                if (time.isBefore(event.getEndTime()) && time.plus(duration_mins).isAfter(event.getStartTime())) {
                    overlaps = true;
                    break;
                }
            }

            // If no overlap, consider this timeslot
            if (!overlaps) {
                if (closest == null || (time.isAfter(LocalDateTime.now()) && time.isBefore(closest))) {
                    closest = time;
                }
            }
        }

        return closest;  // Return the closest valid slot found
    }

    @Override
    public void execute(TimeslotSelectionInputData inputdata) {
        Group group = inputdata.getGroup();
        Calendar calendar = group.getGroupCalendar();
        List<Event> events = calendar.getEvents();
        selectTimeslot(events);
    }
}
