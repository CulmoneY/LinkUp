package usecases.export_calendar;

import entity.Calendar;
import entity.User;

import java.io.IOException;

public class ExportCalendarInteractor implements ExportCalendarInputBoundary {
    final ExportCalendarOutputBoundary outputBoundary;

    // add to app builder
    public ExportCalendarInteractor(ExportCalendarOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ExportCalendarInputData inputData) {
        try {
            User user = inputData.getUser();
            Calendar calendar = user.getUserCalendar();

            if (calendar == null || calendar.getEvents().isEmpty()) {
                outputBoundary.exportFail("Calendar has no events to export.");
            } else {
                String icsCalendar = ICSFormatter.format(calendar);
                String filePath = FileStorage.saveToFile(icsCalendar, user.getName() + "_calendar.ics");

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
