package interface_adapter.AddPersonalEvent;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import usecases.add_personal_event.AddPersonalEventOutputBoundary;
import usecases.add_personal_event.AddPersonalEventOutputData;

public class AddPersonalEventPresenter implements AddPersonalEventOutputBoundary {
    private final AddPersonalEventViewModel addPersonalEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddPersonalEventPresenter(ViewManagerModel viewManagerModel, AddPersonalEventViewModel addPersonalEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.addPersonalEventViewModel = addPersonalEventViewModel;
    }

    @Override
    public void setPassView(AddPersonalEventOutputData outputData) {
        AddPersonalEventState addPersonalEventState = this.addPersonalEventViewModel.getState();
        addPersonalEventState.setEventName(outputData.getEventName());
        addPersonalEventState.setStartTime(outputData.getStartTime());
        addPersonalEventState.setEndTime(outputData.getEndTime());
        addPersonalEventViewModel.firePropertyChanged("eventSuccess");
    }

    @Override
    public void setFailView(String error) {
        AddPersonalEventState addPersonalEventState = this.addPersonalEventViewModel.getState();
        addPersonalEventState.setErrorMessage(error);
        addPersonalEventViewModel.firePropertyChanged("eventFailure");
    }
}
