package usecases.add_recommended_event;

import usecases.add_recommended_event.AddRecommendedEventInputData;

public interface AddRecommendedEventInputBoundary {

    void execute(AddRecommendedEventInputData inputData);
}
