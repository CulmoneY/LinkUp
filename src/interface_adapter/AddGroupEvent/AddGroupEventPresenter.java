package interface_adapter.AddGroupEvent;
import interface_adapter.ViewManagerModel;
import usecases.add_group_event.AddGroupEventOutputBoundary;
import usecases.add_group_event.AddGroupEventOutputData;

public class AddGroupEventPresenter implements AddGroupEventOutputBoundary {
    private final AddGroupEventViewModel addGroupEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddGroupEventPresenter(ViewManagerModel viewManagerModel, AddGroupEventViewModel addGroupEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.addGroupEventViewModel = addGroupEventViewModel;
    }

    @Override
    public void setPassView(AddGroupEventOutputData outputData) {
        AddGroupEventState addGroupEventState = this.addGroupEventViewModel.getState();
        addGroupEventState.setEventName(outputData.getEventName());
        addGroupEventState.setStartTime(outputData.getStartTime());
        addGroupEventState.setEndTime(outputData.getEndTime());
        addGroupEventViewModel.firePropertyChanged("eventSuccess");
    }

    @Override
    public void setFailView(String error) {
        AddGroupEventState addGroupEventState = this.addGroupEventViewModel.getState();
        addGroupEventState.setErrorMessage(error);
        addGroupEventViewModel.firePropertyChanged("eventFailure");
    }
}
