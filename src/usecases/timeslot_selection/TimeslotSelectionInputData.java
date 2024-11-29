package usecases.timeslot_selection;

import entity.Event;
import entity.Group;

import java.time.LocalDateTime;

public class TimeslotSelectionInputData {
    private Group group;
    private int duration;

    private LocalDateTime time;

    public TimeslotSelectionInputData(Group group, int duration, LocalDateTime time) {

        this.group = group;
        this.duration = duration;
        this.time = time;
    }

    public Group getGroup() {
        return group;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
