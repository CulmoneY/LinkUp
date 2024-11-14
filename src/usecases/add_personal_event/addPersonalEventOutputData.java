package usecases.add_personal_event;

public class addPersonalEventOutputData {
    private String eventName;
    private String startTime;
    private String endTime;
    private boolean success;

    public addPersonalEventOutputData(String eventName, String startTime, String endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean getSuccess() {
        return success;
    }
}