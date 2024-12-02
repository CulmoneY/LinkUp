package interface_adapter.ModifyGroupName;

import interface_adapter.AddPersonalEvent.AddPersonalEventState;
import usecases.modify_group_name.ModifyGroupNameOutputBoundary;
import usecases.modify_group_name.ModifyGroupNameOutputData;

// This is for the Modify Group Name usecase

// this is the presenter for it.

// needs to implement its output boundary

// with 2 methods :

// setPassViewData and

// setFailViewData


public class ModifyGroupNamePresenter implements ModifyGroupNameOutputBoundary {

    // view model for the Modify GRoup
    private ModifyGroupNameViewModel modifyGroupNameViewModel;

    // constructor for the presenter
    // one argument : the View Model

    public ModifyGroupNamePresenter(ModifyGroupNameViewModel modifyGroupNameViewModel) {
        // assigns its viewmodel.

        this.modifyGroupNameViewModel = modifyGroupNameViewModel;
    }


    // The set pass view method
    // takes the outputData
    // fills the state.
    // updates the viewmodel.
    @Override
    public void setPassViewData(ModifyGroupNameOutputData outputData) {
        ModifyGroupNameState modifyGroupNameState = this.modifyGroupNameViewModel.getState();
        modifyGroupNameState.setNewGroupName(outputData.getNewGroupName());
        modifyGroupNameState.setOldGroupName(outputData.getOldGroupName());
        modifyGroupNameViewModel.firePropertyChanged("groupnameSuccess");

    }

    // The set fail view method
    // takes the outputData
    // fills the state.
    // updates the viewmodel.


    @Override
    public void setFailViewData(String error) {
        // first get the state

        ModifyGroupNameState modifyGroupNameState = this.modifyGroupNameViewModel.getState();

        // sets the error
        modifyGroupNameState.setErrorMessage(error);

        // name of the propertyfire.
        modifyGroupNameViewModel.firePropertyChanged("groupnameFailure");
    }
}
