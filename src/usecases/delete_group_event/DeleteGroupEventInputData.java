package usecases.delete_group_event;

import entity.Group;

public class DeleteGroupEventInputData {
    Group group;
    String eventName;
    String startTime;
    String endTime;

    public DeleteGroupEventInputData(Group group, String eventName, String startTime, String endTime) {
        this.group = group;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Group getGroup() {
        return group;
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
