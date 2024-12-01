package interface_adapter.AddGroupMember;

import interface_adapter.AddFriend.AddFriendState;
import usecases.add_group_member.AddGroupMemberOutputBoundary;
import usecases.add_group_member.AddGroupMemberOutputData;

public class AddGroupMemberPresenter implements AddGroupMemberOutputBoundary {
    private final AddGroupMemberViewModel addGroupMemberViewModel;

    public AddGroupMemberPresenter(AddGroupMemberViewModel addGroupMemberViewModel) {
        this.addGroupMemberViewModel = addGroupMemberViewModel;
    }

    @Override
    public void setPassViewData(AddGroupMemberOutputData outputData) {
        AddGroupMemberState addGroupMemberState = addGroupMemberViewModel.getState();
        addGroupMemberState.setUsername(outputData.getUsername());
        addGroupMemberState.setGroupName(outputData.getGroupname());
        addGroupMemberViewModel.firePropertyChanged("addGroupMemberSuccess");
    }

}
