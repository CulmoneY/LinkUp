package usecases.export_calendar;

import entity.User;

public class ExportCalendarInputData {
    private User user;
    private String groupName;

    public ExportCalendarInputData(User user) {
        this.user = user;
        this.groupName = null;
    }

    public ExportCalendarInputData(String groupName) {
        this.user = null;
        this.groupName = groupName;
    }

    public User getUser() {return user;}

    public String getGroupName() {return groupName;}

}
