package interface_adapter.DeleteGroupEvent;

import interface_adapter.ViewManagerModel;
import usecases.delete_group_event.DeleteGroupEventOutputBoundary;
import usecases.delete_group_event.DeleteGroupEventOutputData;

public class DeleteGroupEventPresenter implements DeleteGroupEventOutputBoundary {
    private final DeleteGroupEventViewModel deleteGroupEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteGroupEventPresenter(ViewManagerModel viewManagerModel, DeleteGroupEventViewModel deleteGroupEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.deleteGroupEventViewModel = deleteGroupEventViewModel;
    }

    @Override
    public void setPassView(DeleteGroupEventOutputData outputData) {
        DeleteGroupEventState deleteGroupEventState = this.deleteGroupEventViewModel.getState();
        deleteGroupEventState.setEventName(outputData.getEventName());
        deleteGroupEventViewModel.firePropertyChanged("eventDeleteSuccess");
    }
}
