package usecases.timeslot_selection;

import entity.Event;
import entity.Group;
import entity.User;

import java.time.LocalDateTime;

public class TimeslotSelectionInputData {
    private String group;
    private User user;

    public TimeslotSelectionInputData(String group, User user) {

        this.group = group;
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public User getUser() {
        return user;
    }
}
