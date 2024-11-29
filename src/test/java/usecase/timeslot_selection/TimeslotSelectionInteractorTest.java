package usecase.timeslot_selection;

import entity.Calendar;
import entity.Event;
import entity.EventFactory;
import entity.Group;
import entity.CommonGroupEvent;
import entity.CommonEventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.timeslot_selection.TimeslotSelectionDataAccessInterface;
import usecases.timeslot_selection.TimeslotSelectionInteractor;
import usecases.timeslot_selection.TimeslotSelectionOutputBoundary;
import usecases.timeslot_selection.TimeslotSelectionOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeslotSelectionInteractorTest {

    private TimeslotSelectionInteractor interactor;
    private TimeslotSelectionDataAccessInterface timeslotDataAccess;
    private TimeslotSelectionOutputBoundary timeslotPresenter;
    private EventFactory eventFactory;

    @BeforeEach
    public void setUp() {
        timeslotDataAccess = new TimeslotSelectionDataAccessStub();
        timeslotPresenter = new TimeslotSelectionOutputBoundaryStub();
        eventFactory = new CommonEventFactory(); // Using CommonEventFactory for event creation
        interactor = new TimeslotSelectionInteractor(timeslotDataAccess, timeslotPresenter, eventFactory);
    }

    @Test
    public void testSelectTimeslot_SameDayValidSlot() {
        // Create a list of events for the current day
        List<Event> events = new ArrayList<>();
        LocalDateTime eventStart = LocalDateTime.of(2024, 11, 29, 10, 0); // Existing event from 10:00 to 12:00
        LocalDateTime eventEnd = eventStart.plusHours(2);

        // Create event using the EventFactory
        events.add(eventFactory.create("Existing Event", eventStart, eventEnd, true));

        // Call the selectTimeslot method
        interactor.selectTimeslot(events);

        // Verify that the presenter received the correct event data
        assertTrue(((TimeslotSelectionOutputBoundaryStub) timeslotPresenter).isEventSet());
    }

    @Test
    public void testSelectTimeslot_AfterCurrentEventOnSameDay() {
        // Create a list of events for the current day
        List<Event> events = new ArrayList<>();
        LocalDateTime eventStart = LocalDateTime.of(2024, 11, 29, 10, 0); // Existing event from 10:00 to 12:00
        LocalDateTime eventEnd = eventStart.plusHours(2);

        // Create event using the EventFactory
        events.add(eventFactory.create("Existing Event", eventStart, eventEnd, true));

        // Try to find a timeslot after the existing event ends
        LocalDateTime newEventStart = eventEnd.plusMinutes(15); // Event starts after existing event
        LocalDateTime newEventEnd = newEventStart.plusHours(2);

        // Create new event using the EventFactory
        events.add(eventFactory.create("New Event", newEventStart, newEventEnd, true));

        // Call the selectTimeslot method
        interactor.selectTimeslot(events);

        // Verify that the presenter was called with the correct event data
        assertTrue(((TimeslotSelectionOutputBoundaryStub) timeslotPresenter).isEventSet());
    }

    @Test
    public void testSelectTimeslot_NextDaySlot() {
        // Create a list of events for today (No available slot)
        List<Event> events = new ArrayList<>();
        LocalDateTime eventStart = LocalDateTime.of(2024, 11, 29, 10, 0); // Existing event from 10:00 to 12:00
        LocalDateTime eventEnd = eventStart.plusHours(2);

        // Create event using the EventFactory
        events.add(eventFactory.create("Existing Event", eventStart, eventEnd, true));

        // Call the selectTimeslot method and simulate moving to the next day
        interactor.selectTimeslot(events);

        // Verify that the presenter was called with the new event on the next day
        assertTrue(((TimeslotSelectionOutputBoundaryStub) timeslotPresenter).isEventSet());
    }

    @Test
    public void testSelectTimeslot_NoAvailableSlot() {
        // Create a list of events that take up the entire available time (9:00 AM to 10:00 PM)
        List<Event> events = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(2024, 11, 29, 9, 0);
        LocalDateTime end = start.plusHours(13); // Event takes the entire day

        // Create event using the EventFactory
        events.add(eventFactory.create("Event 1", start, end, true));

        // Call the selectTimeslot method
        interactor.selectTimeslot(events);

        // Verify that the presenter received the "No Available Slot" event
        assertTrue(((TimeslotSelectionOutputBoundaryStub) timeslotPresenter).isEventSet());
    }

    @Test
    public void testSelectTimeslot_MultipleEvents_Success() {
        // Create multiple events that have gaps
        List<Event> events = new ArrayList<>();
        LocalDateTime eventStart1 = LocalDateTime.of(2024, 11, 29, 9, 0);  // Event from 9:00 to 11:00
        LocalDateTime eventEnd1 = eventStart1.plusHours(2);

        // Create event using the EventFactory
        events.add(eventFactory.create("Event 1", eventStart1, eventEnd1, true));

        LocalDateTime eventStart2 = LocalDateTime.of(2024, 11, 29, 14, 0); // Event from 14:00 to 16:00
        LocalDateTime eventEnd2 = eventStart2.plusHours(2);

        // Create event using the EventFactory
        events.add(eventFactory.create("Event 2", eventStart2, eventEnd2, true));

        // Try to find a slot between 11:00 and 14:00
        interactor.selectTimeslot(events);

        // Verify that the presenter was called with the new event
        assertTrue(((TimeslotSelectionOutputBoundaryStub) timeslotPresenter).isEventSet());
    }

    // Stub implementation of TimeslotSelectionDataAccessInterface
    private static class TimeslotSelectionDataAccessStub implements TimeslotSelectionDataAccessInterface {
        @Override
        public List<Event> getGroupEvents(String groupName) {
            // For the stub, we are returning an empty list to simulate no events being found
            return new ArrayList<>();
        }
    }


    // Stub implementation of TimeslotSelectionOutputBoundary
    private static class TimeslotSelectionOutputBoundaryStub implements TimeslotSelectionOutputBoundary {
        private boolean eventSet = false;

        @Override
        public void setPassView(TimeslotSelectionOutputData outputData) {
            this.eventSet = true;
        }

        public boolean isEventSet() {
            return eventSet;
        }
    }
}
