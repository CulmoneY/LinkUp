package usecases.export_calendar;

import entity.User;

public class ExportCalendarInputData {
    private User user;

    public ExportCalendarInputData(User user) {
        this.user = user;
    }

    public User getUser() {return this.user;}

}
