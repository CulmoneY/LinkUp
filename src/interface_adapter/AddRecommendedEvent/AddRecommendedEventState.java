package interface_adapter.AddRecommendedEvent;

import entity.Event;

public class AddRecommendedEventState {
    private Event event;
    private String groupName;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
