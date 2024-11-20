package usecases.export_calendar;

public interface ExportCalendarOutputBoundary {

    void exportSuccess(ExportCalendarOutputData calendar);

    void exportFail(String error);

}
