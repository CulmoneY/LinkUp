package usecases.export_calendar;

import entity.User;
import entity.Group;

public class ExportCalendarInputData {
    private User user;
    private final Group group;

    public ExportCalendarInputData(User user) {
        this.user = user;
        this.group = null;
    }

    public ExportCalendarInputData(Group group) {
        this.user = null;
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public Group getGroup() {
        return group;
    }

}
