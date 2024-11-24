package interface_adapter.CreateGroup;

import interface_adapter.AccountCreation.AccountCreationViewModel;
import interface_adapter.ViewManagerModel;
import usecases.create_group.CreateGroupOutputBoundary;
import usecases.create_group.CreateGroupOutputData;

public class CreateGroupPresenter  implements CreateGroupOutputBoundary {

    private final CreateGroupViewModel createGroupViewModel;
    private final ViewManagerModel viewManagerModel;


    public CreateGroupPresenter(CreateGroupViewModel createGroupViewModel, ViewManagerModel viewManagerModel) {
        this.createGroupViewModel = createGroupViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void setPassView(CreateGroupOutputData group) {
        CreateGroupState createGroupState = createGroupViewModel.getState();
        createGroupViewModel.firePropertyChanged("createSuccess");

    }

    @Override
    public void setFailView(String error) {

    }
}
