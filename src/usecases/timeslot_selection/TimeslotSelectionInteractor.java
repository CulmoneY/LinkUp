package usecases.timeslot_selection;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

import database.MongoDBConnection;
import entity.Calendar;
import entity.Event;
import entity.EventFactory;
import entity.Group;
// import entities.Timeslot;

public class TimeslotSelectionInteractor implements TimeslotSelectionInputBoundary {
   final TimeslotSelectionDataAccessInterface timeslotdataAccess;
   final TimeslotSelectionOutputBoundary timeslotpresenter;
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
        TemporalAmount duration_mins = Duration.ofMinutes(120);  //
        // Get current datetime
        LocalDateTime now = LocalDateTime.now();
        // Initialize the closest timeslot found
        LocalDateTime closest = null;
        // Search for the closest valid timeslot
        for (LocalDateTime time = now.with(startTime);
             time.plus(duration_mins).toLocalTime().isBefore(endTime);
             time = time.plusMinutes(15)) {  // Increment in 15-minute intervals

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
                if (closest == null || time.isAfter(now) && time.isBefore(closest)) {
                    closest = time;
                }
            }
        }
    }

    @Override
    public void execute(TimeslotSelectionInputData inputdata) {
        Group group = inputdata.getGroup();
        Calendar calendar = group.getGroupCalendar();
        List<Event> events = calendar.getEvents();
        selectTimeslot(events);
    }

}
