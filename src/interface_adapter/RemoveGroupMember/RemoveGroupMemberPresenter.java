package interface_adapter.RemoveGroupMember;

import usecases.remove_group_member.RemoveGroupMemberOutputBoundary;
import usecases.remove_group_member.RemoveGroupMemberOutputData;

public class RemoveGroupMemberPresenter implements RemoveGroupMemberOutputBoundary {
    private final RemoveGroupMemberViewModel removeGroupMemberViewModel;

    public RemoveGroupMemberPresenter(RemoveGroupMemberViewModel removeGroupMemberViewModel) {
        this.removeGroupMemberViewModel = removeGroupMemberViewModel;
    }


    @Override
    public void setPassViewData(RemoveGroupMemberOutputData outputData) {
        RemoveGroupMemberState removeGroupMemberState = removeGroupMemberViewModel.getState();
        removeGroupMemberState.setUsername(outputData.getUsername());
        removeGroupMemberState.setGroupname(outputData.getGroupname());
        removeGroupMemberViewModel.firePropertyChanged("removeGroupMemberSuccess");

    }
}
