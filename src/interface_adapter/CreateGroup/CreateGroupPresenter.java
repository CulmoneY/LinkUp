package interface_adapter.CreateGroup;

import usecases.create_group.CreateGroupOutputBoundary;
import usecases.create_group.CreateGroupOutputData;

public class CreateGroupPresenter  implements CreateGroupOutputBoundary {
    @Override
    public void setPassView(CreateGroupOutputData group) {

    }

    @Override
    public void setFailView(String error) {

    }
}
