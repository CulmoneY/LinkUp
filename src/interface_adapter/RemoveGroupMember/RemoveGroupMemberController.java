package interface_adapter.RemoveGroupMember;

import usecases.remove_group_member.RemoveGroupMemberInputBoundary;
import usecases.remove_group_member.RemoveGroupMemberInputData;

public class RemoveGroupMemberController {
    private final RemoveGroupMemberInputBoundary removeGroupMemberInteractor;

    public RemoveGroupMemberController(RemoveGroupMemberInputBoundary removeGroupMemberInteractor) {
        this.removeGroupMemberInteractor = removeGroupMemberInteractor;
    }

    public void execute(String groupname, String username) {
        removeGroupMemberInteractor.executeRemoveGroupMember(new RemoveGroupMemberInputData(groupname, username));
    }
}
