package usecases.add_group_member;

import entity.Group;

public class AddGroupMemberInteractor implements AddGroupMemberInputBoundary{
    private AddGroupMemberOutputBoundary outputPresenter;
    private AddGroupMemberDataAccessInterface dataAccess;

    public AddGroupMemberInteractor(AddGroupMemberOutputBoundary outputPresenter, AddGroupMemberDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }


    @Override
    public void executeAddGroupMember(AddGroupMemberInputData inputData) {
        String groupname = inputData.getGroupname();
        String username = inputData.getUsername();
        dataAccess.addGroupToUser(username, groupname);
        dataAccess.addUserToGroup(groupname, username);
        outputPresenter.setPassViewData(new AddGroupMemberOutputData(groupname, username));
    }
}
