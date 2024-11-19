package interface_adapter.ExportCalendar;

public class ExportCalendarState {
    private String filePath;
    private String message;
    private boolean success;

    public String getFilePath() {return filePath;}

    public void setFilePath(String filePath) {this.filePath = filePath;}

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public boolean isSuccess() {return success;}

    public void setSuccess(boolean success) {this.success = success;}
}
