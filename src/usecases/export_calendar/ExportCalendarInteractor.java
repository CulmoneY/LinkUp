package usecases.export_calendar;

import entity.Calendar;
import entity.User;

import java.io.IOException;

public class ExportCalendarInteractor implements ExportCalendarInputBoundary {
    final ExportCalendarOutputBoundary outputBoundary;

    public ExportCalendarInteractor(ExportCalendarOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ExportCalendarInputData inputData) throws IOException {
        User user = inputData.getUser();
        Calendar calendar = user.getUserCalendar();

        if (calendar.getEvents().size() == 0) {
            outputBoundary.exportFail("No events to export.");
            return;
        }

        String icsContent = IcsFormatter.format(calendar);
        String filePath = FileStorage.saveToFile(icsContent, user.getName() + "_calendar.ics");

        outputBoundary.exportSuccess(new
                ExportCalendarOutputData(true, filePath, "Export successful."));

    }
}
