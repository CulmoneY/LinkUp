package interface_adapter.AddRecommendedEvent;

import interface_adapter.AddPersonalEvent.AddPersonalEventState;
import interface_adapter.ViewModel;

public class AddRecommendedEventViewModel extends ViewModel<AddRecommendedEventState> {
    public AddRecommendedEventViewModel() {
        super("addRecommendedEventView");
        setState(new AddRecommendedEventState());
    }
}
