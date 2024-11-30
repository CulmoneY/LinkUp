package interface_adapter.AddRecommendedEvent;

import usecases.add_recommended_event.AddRecommendedEventOutputBoundary;
import usecases.add_recommended_event.AddRecommendedEventOutputData;

public class AddRecommendedEventPresenter implements AddRecommendedEventOutputBoundary {
    private AddRecommendedEventOutputBoundary outputBoundary;

    public AddRecommendedEventPresenter(AddRecommendedEventOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void setPassView(AddRecommendedEventOutputData response) {
        outputBoundary.setPassView(response);
    }
}
