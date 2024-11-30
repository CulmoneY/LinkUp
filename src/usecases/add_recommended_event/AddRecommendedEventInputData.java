package usecases.add_recommended_event;

import entity.Event;
import entity.User;

public class AddRecommendedEventInputData {
    private Event event;
    private String groupName;

    public AddRecommendedEventInputData(Event event, String groupName) {
        this.event = event;
        this.groupName = groupName;
    }

    public Event getEvent() {
        return event;
    }

    public String getGroupName() {
        return groupName;
    }
}
