package usecases.export_calendar;

import entity.User;
import entity.Group;

public class ExportCalendarInputData {
    private User user;
    private Group group;

    public ExportCalendarInputData(User user, Group group) {
        this.user = user;
        this.group = group;
    }

    public User getUser() {return this.user;}
    public Group getGroup() {return this.group;}

}
