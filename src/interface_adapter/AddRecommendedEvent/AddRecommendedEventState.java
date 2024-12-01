package interface_adapter.AddRecommendedEvent;

import entity.Event;

public class AddRecommendedEventState {
    private String eventName;
    private String groupName;

    public String getEvent() {
        return eventName;
    }

    public void setEvent(String eventName) {
        this.eventName = eventName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
