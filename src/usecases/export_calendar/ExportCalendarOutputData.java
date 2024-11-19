package usecases.export_calendar;

public class ExportCalendarOutputData {
    private boolean success;
    private String filePath;
    private String message;

    public ExportCalendarOutputData(boolean success, String filePath, String message) {
        this.success = success;
        this.filePath = filePath;
        this.message = message;
    }

    public boolean getSuccess() {return this.success;}
    public String getFilePath() {return this.filePath;}
    public String getMessage() {return this.message;}
}
