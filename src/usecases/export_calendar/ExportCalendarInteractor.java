package usecases.export_calendar;

import entity.Calendar;
import entity.User;

public class ExportCalendarInteractor implements ExportCalendarInputBoundary {
    final ExportCalendarOutputBoundary outputBoundary;

    public ExportCalendarInteractor(ExportCalendarOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(ExportCalendarInputData inputData) {
        User user = inputData.getUser();
        Calendar calendar = user.getUserCalendar();



    }
}
