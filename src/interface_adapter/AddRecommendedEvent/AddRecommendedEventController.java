package interface_adapter.AddRecommendedEvent;

import entity.Event;
import usecases.add_recommended_event.AddRecommendedEventInputBoundary;
import usecases.add_recommended_event.AddRecommendedEventInputData;

public class AddRecommendedEventController {
    private AddRecommendedEventInputBoundary inputBoundary;

    public AddRecommendedEventController(AddRecommendedEventInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void excute(Event event, String groupName) {
        AddRecommendedEventInputData inputData = new AddRecommendedEventInputData(event, groupName);
        inputBoundary.execute(inputData);

    }
}
