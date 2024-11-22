package usecases.export_calendar;

import entity.User;
import entity.Group;

public class ExportCalendarInputData {
    private User user;

    public ExportCalendarInputData(User user) {
        this.user = user;
    }

    public User getUser() {return this.user;}

}
