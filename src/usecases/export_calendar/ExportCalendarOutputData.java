package usecases.export_calendar;

public class ExportCalendarOutputData {
    private boolean success;

    public ExportCalendarOutputData(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {return this.success;}
}
