package interface_adapter.AddFriend;

import usecases.add_friend.AddFriendInputData;
import usecases.add_friend.AddFriendOutputBoundary;
import usecases.add_friend.AddFriendOutputData;

public class AddFriendPresenter implements AddFriendOutputBoundary {
    private final AddFriendViewModel addFriendViewModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel) {
        this.addFriendViewModel = addFriendViewModel;
    }

    @Override
    public void setPassViewData(AddFriendOutputData outputData) {
        AddFriendState addFriendState = addFriendViewModel.getState();
        addFriendState.setUsername(outputData.getUsername());
        addFriendState.setFriendUsername(outputData.getFriendUsername());
        addFriendViewModel.firePropertyChanged("addFriendSuccess");
    }

    @Override
    public void setFailViewData(String error) {
        AddFriendState addFriendState = addFriendViewModel.getState();
        addFriendState.setErrorMessage(error);
        addFriendViewModel.firePropertyChanged("addFriendFailure");
    }
}
