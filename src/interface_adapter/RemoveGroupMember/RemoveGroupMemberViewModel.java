package interface_adapter.RemoveGroupMember;

import interface_adapter.ViewModel;

public class RemoveGroupMemberViewModel extends ViewModel<RemoveGroupMemberState> {
    public RemoveGroupMemberViewModel() {
        super("removeGroupMember");
        setState(new RemoveGroupMemberState());
    }
}
