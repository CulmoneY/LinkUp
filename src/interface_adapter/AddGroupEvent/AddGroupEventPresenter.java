package interface_adapter.AddGroupEvent;


import usecases.add_group_event.AddGroupEventOutputBoundary;
import usecases.add_group_event.AddGroupOutputData;

public class AddGroupEventPresenter implements AddGroupEventOutputBoundary {
    private AddGroupEventViewModel viewModel;

    public AddGroupEventPresenter(AddGroupEventViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    public void setPassView(AddGroupOutputData outputData) {
        AddGroupEventState addGroupEventState = viewModel.getState();
        addGroupEventState.setGroupname(outputData.getGroupName());
        addGroupEventState.setEventname(outputData.getEventName());
        viewModel.firePropertyChanged("addGroupEventSuccess");
    }

    @Override
    public void setFailView(String error) {
        AddGroupEventState addGroupEventState = viewModel.getState();
        addGroupEventState.setError(error);
        viewModel.firePropertyChanged("addGroupEventError");
    }
}
