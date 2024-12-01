package interface_adapter.DeleteGroupEvent;

import usecases.delete_group_event.DeleteGroupEventOutputBoundary;
import usecases.delete_group_event.DeleteGroupEventOutputData;
import usecases.delete_personal_event.DeletePersonalEventOutputData;

public class DeleteGroupEventPresenter implements DeleteGroupEventOutputBoundary {
    private final DeleteGroupEventViewModel deleteGroupEventViewModel;

    public DeleteGroupEventPresenter(DeleteGroupEventViewModel deleteGroupEventViewModel) {
        this.deleteGroupEventViewModel = deleteGroupEventViewModel;
    }

    @Override
    public void setPassView(DeleteGroupEventOutputData outputData) {
        DeleteGroupEventState deleteGroupEventState = deleteGroupEventViewModel.getState();
        deleteGroupEventState.setEventName(outputData.getEventName());
        deleteGroupEventViewModel.firePropertyChanged("deleteGroupEventSuccess");
    }
}
