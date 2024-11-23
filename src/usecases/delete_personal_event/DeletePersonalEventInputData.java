package usecases.delete_personal_event;

import entity.User;

public class DeletePersonalEventInputData {
    User user;
    String eventName;
    String startTime;
    String endTime;

    public DeletePersonalEventInputData(User user, String eventName, String startTime, String endTime) {
        this.user = user;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
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
}
