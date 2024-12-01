package interface_adapter.AddRecommendedEvent;

import interface_adapter.AddPersonalEvent.AddPersonalEventViewModel;
import interface_adapter.ViewManagerModel;
import usecases.add_recommended_event.AddRecommendedEventOutputBoundary;
import usecases.add_recommended_event.AddRecommendedEventOutputData;

public class AddRecommendedEventPresenter implements AddRecommendedEventOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final AddRecommendedEventViewModel addRecommendedEventViewModel;

    public AddRecommendedEventPresenter(ViewManagerModel viewManagerModel, AddRecommendedEventViewModel addRecommendedEventViewModel) {
        this.addRecommendedEventViewModel = addRecommendedEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void setPassView(AddRecommendedEventOutputData response) {
        AddRecommendedEventState addRecommendedEventState = addRecommendedEventViewModel.getState();
        addRecommendedEventState.setEvent(response.getEventName());
        addRecommendedEventViewModel.firePropertyChanged("addRecommendedSuccess");
    }
}
