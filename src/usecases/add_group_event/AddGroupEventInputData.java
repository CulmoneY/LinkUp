package usecases.add_group_event;
import entity.Group;

public class AddGroupEventInputData {
    private String eventName;
    private String startTime;
    private String endTime;
    private Group group;

    public AddGroupEventInputData(String eventName, String startTime, String endTime, Group group) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }
}
