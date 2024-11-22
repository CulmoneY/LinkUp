package usecases.export_calendar;

import entity.User;
import entity.Group;

// add Group (need for interactor as well, execute, and tests for both)
public class ExportCalendarInputData {
    private User user;
    //private Group group;

    public ExportCalendarInputData(User user) {
        this.user = user;
        // this.group = group;
    }

    public User getUser() {return this.user;}
    //public Group getGroup() {return this.group;}

}
