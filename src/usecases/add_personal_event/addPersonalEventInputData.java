package usecases.add_personal_event;
import entity.User;

public class addPersonalEventInputData {
    private String eventName;
    private String startTime;
    private String endTime;
    private User user;

    public addPersonalEventInputData(String eventName, String startTime, String endTime, User user) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
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

    public User getUser() {
        return user;
    }
}
