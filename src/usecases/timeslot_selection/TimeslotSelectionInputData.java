package usecases.timeslot_selection;

import entity.Event;
import entity.Group;

public class TimeslotSelectionInputData {
    private Event event;
    private Group group;
    private int duration;

    public TimeslotSelectionInputData(Event event, Group group, int duration) {
        this.event = event;
        this.group = group;
        this.duration = duration;
    }

    public Group getGroup() {
        return group;
    }

    public int getDuration() {
        return duration;
    }

    public Event getEvent() {
        return event;
    }



}
