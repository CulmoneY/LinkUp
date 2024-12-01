package usecases.remove_group_member;

public class RemoveGroupMemberInteractor implements RemoveGroupMemberInputBoundary{
    private RemoveGroupMemberOutputBoundary outputPresenter;
    private RemoveGroupMemberDataAccessInterface dataAccess;

    public RemoveGroupMemberInteractor(RemoveGroupMemberOutputBoundary outputPresenter, RemoveGroupMemberDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;

        this.dataAccess = dataAccess;
    }


    @Override
    public void executeRemoveGroupMember(RemoveGroupMemberInputData inputData) {
        String groupname = inputData.getGroupname();

        String username = inputData.getUsername();

        dataAccess.removeGroupMember(groupname, username);

        outputPresenter.setPassViewData(new RemoveGroupMemberOutputData(groupname, username));

    }
}
