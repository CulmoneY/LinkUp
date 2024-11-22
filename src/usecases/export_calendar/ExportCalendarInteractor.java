package usecases.export_calendar;

import entity.Calendar;
import entity.User;
import entity.Group;

import java.io.IOException;

// add to AppBuilder
// add DataAccessInterface
public class ExportCalendarInteractor implements ExportCalendarInputBoundary {
    final ExportCalendarDataAccessInterface calendarDataAccess;
    final ExportCalendarOutputBoundary outputBoundary;

    public ExportCalendarInteractor(ExportCalendarDataAccessInterface calendarDataAccess,
                                    ExportCalendarOutputBoundary outputBoundary) {
        this.calendarDataAccess = calendarDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ExportCalendarInputData inputData) {
        try {
            Calendar calendar = null;
            String name = null;

            if (inputData.getUser() != null) {
                User user = inputData.getUser();
                calendar = user.getUserCalendar();
                calendarDataAccess.saveCalendar(calendar);
                if (calendar != null) {
                    name = calendar.getName() + "_calendar.ics";
                }
            }

            if (inputData.getGroup() != null) {
                Group group = inputData.getGroup();
                calendar = group.getGroupCalendar();
                calendarDataAccess.saveCalendar(calendar);
                if (calendar != null) {
                    name = calendar.getName() + "_calendar.ics";
                }
            }

            if (calendar == null || calendar.getEvents().isEmpty()) {
                    outputBoundary.exportFail("Calendar has no events to export.");
            } else {
                String icsCalendar = ICSFormatter.format(calendar);
                String filePath = FileStorage.saveToFile(icsCalendar, name);

                outputBoundary.exportSuccess(new
                        ExportCalendarOutputData(true, filePath, "Export successful."));
            }

            } catch(IOException error) {
                outputBoundary.exportFail("Failed to save calendar: " + error.getMessage());
            } catch(Exception error) {
                outputBoundary.exportFail("An unexpected error occurred: " + error.getMessage());
            }

    }
}