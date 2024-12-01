package interface_adapter.AddGroupMember;

import entity.Group;
import usecases.add_group_member.AddGroupMemberInputBoundary;
import usecases.add_group_member.AddGroupMemberInputData;
import usecases.add_group_member.AddGroupMemberOutputData;

public class AddGroupMemberController {
    private final AddGroupMemberInputBoundary addGroupMemberInteractor;

    public AddGroupMemberController(AddGroupMemberInputBoundary addGroupMemberInteractor) {
        this.addGroupMemberInteractor = addGroupMemberInteractor;
    }

    public void execute(String groupname, String username) {
        addGroupMemberInteractor.executeAddGroupMember( new AddGroupMemberInputData(groupname, username));
    }
}
