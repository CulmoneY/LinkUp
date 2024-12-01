package usecases.export_calendar;

import entity.Calendar;
import entity.User;
import entity.Group;

import java.io.IOException;

public class ExportCalendarInteractor implements ExportCalendarInputBoundary {
    String exportMessage;
    final ExportCalendarOutputBoundary outputBoundary;

    public ExportCalendarInteractor(ExportCalendarOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ExportCalendarInputData inputData) {
        try {
            Calendar calendar = null;
            String name = null;

            if (inputData.getUser() != null && inputData.getGroup() == null) {
                exportMessage = "User Export successful.";
                User user = inputData.getUser();
                calendar = user.getUserCalendar();
                if (calendar != null) {
                    name = calendar.getName() + "_calendar.ics";
                }
            }

            if (inputData.getUser() == null && inputData.getGroup() != null) {
                exportMessage = "Group Export successful.";
                Group group = inputData.getGroup();
                calendar = group.getGroupCalendar();
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
                        ExportCalendarOutputData(true, filePath, exportMessage));
            }

            } catch(IOException error) {
                outputBoundary.exportFail("Failed to save calendar: " + error.getMessage());
            } catch(Exception error) {
                outputBoundary.exportFail("An unexpected error occurred: " + error.getMessage());
            }

    }
}