package usecases.timeslot_selection;

import entity.Event;

public class TimeslotSelectionOutputData {
    private Event event;

    public TimeslotSelectionOutputData(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
