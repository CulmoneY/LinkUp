package usecases.add_recommended_event;

import entity.Event;

public class AddRecommendedEventOutputData {
    private String eventName;

    public AddRecommendedEventOutputData(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
