package interface_adapter.CreateGroup;

import interface_adapter.ViewModel;

public class CreateGroupViewModel extends ViewModel<CreateGroupState> {

    public CreateGroupViewModel() {
        super("createGroupView");
        setState(new CreateGroupState());
    }
}
