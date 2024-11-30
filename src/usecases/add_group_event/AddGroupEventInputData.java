package usecases.add_group_event;
import entity.Group;

public class AddGroupEventInputData {
    private String eventName;
    private String startTime;
    private String endTime;
    private String groupName;

    public AddGroupEventInputData(String eventName, String startTime, String endTime, String groupName) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }
}
