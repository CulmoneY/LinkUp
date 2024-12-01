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

    public void exportCalendar(User user, String groupName) {
        if (user != null && groupName == null) {
            ExportCalendarInputData inputData = new ExportCalendarInputData(user);
            exportCalendarInteractor.execute(inputData);
        }
        if (user == null && groupName != null) {
            ExportCalendarInputData inputData = new ExportCalendarInputData(groupName);
            exportCalendarInteractor.execute(inputData);
        }
    }
}
