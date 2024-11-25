package interface_adapter.DeletePersonalEvent;

import interface_adapter.AddPersonalEvent.AddPersonalEventViewModel;
import interface_adapter.ViewManagerModel;
import usecases.delete_personal_event.DeletePersonalEventOutputBoundary;
import usecases.delete_personal_event.DeletePersonalEventOutputData;

public class DeletePersonalEventPresenter implements DeletePersonalEventOutputBoundary {
    private final DeletePersonalEventViewModel deletePersonalEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeletePersonalEventPresenter(ViewManagerModel viewManagerModel, DeletePersonalEventViewModel deletePersonalEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.deletePersonalEventViewModel = deletePersonalEventViewModel;
    }

    @Override
    public void setPassView(DeletePersonalEventOutputData outputData) {
        DeletePersonalEventState deletePersonalEventState = this.deletePersonalEventViewModel.getState();
        deletePersonalEventState.setEventName(outputData.getEventName());
        deletePersonalEventViewModel.firePropertyChanged("eventDeleteSuccess");
    }
}
