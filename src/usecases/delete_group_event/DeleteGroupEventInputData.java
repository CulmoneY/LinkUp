package usecases.delete_group_event;

public class DeleteGroupEventInputData {
    String groupName;
    String eventName;
    String startTime;
    String endTime;

    public DeleteGroupEventInputData(String groupName, String eventName, String startTime, String endTime) {
        this.groupName = groupName;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getGroupName() {
        return groupName;
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
