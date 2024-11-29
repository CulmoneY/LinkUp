package interface_adapter.ExportCalendar;

import entity.User;
import entity.Group;
import usecases.export_calendar.ExportCalendarInputBoundary;
import usecases.export_calendar.ExportCalendarInputData;

public class ExportCalendarController {
    private final ExportCalendarInputBoundary exportCalendarInteractor;

    public ExportCalendarController(ExportCalendarInputBoundary exportCalendarInteractor) {
        this.exportCalendarInteractor = exportCalendarInteractor;
    }

    public void exportUserCalendar(User user) {
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);
        exportCalendarInteractor.execute(inputData);
    }

    // add to AppBuilder
    public void exportGroupCalendar(Group group) {
        ExportCalendarInputData inputData = new ExportCalendarInputData(group);
        exportCalendarInteractor.execute(inputData);
    }

}
