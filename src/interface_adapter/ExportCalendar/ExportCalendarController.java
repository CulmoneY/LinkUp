package interface_adapter.ExportCalendar;

import entity.User;
import usecases.export_calendar.ExportCalendarInputBoundary;
import usecases.export_calendar.ExportCalendarInputData;

import java.io.IOException;

public class ExportCalendarController {
    private final ExportCalendarInputBoundary exportCalendarInteractor;

    public ExportCalendarController(ExportCalendarInputBoundary exportCalendarInteractor) {
        this.exportCalendarInteractor = exportCalendarInteractor;
    }

    public void execute(User user) throws IOException {
        ExportCalendarInputData inputData = new ExportCalendarInputData(user);
        exportCalendarInteractor.execute(inputData);
    }
}
