package interface_adapter.ModifyGroupName;

import interface_adapter.ViewModel;

public class ModifyGroupNameViewModel extends ViewModel<ModifyGroupNameState> {
    public ModifyGroupNameViewModel() {
        super("groupnameView");
        setState(new ModifyGroupNameState());
    }
}
