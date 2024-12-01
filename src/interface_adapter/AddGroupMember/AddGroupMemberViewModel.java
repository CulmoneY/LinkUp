package interface_adapter.AddGroupMember;

import interface_adapter.ViewModel;

public class AddGroupMemberViewModel extends ViewModel<AddGroupMemberState> {
    public AddGroupMemberViewModel() {
        super("addGroupMember");
        setState(new AddGroupMemberState());
    }
}
