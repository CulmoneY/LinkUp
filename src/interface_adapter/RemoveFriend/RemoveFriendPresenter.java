package interface_adapter.RemoveFriend;

import interface_adapter.ViewManagerModel;
import usecases.remove_friend.RemoveFriendOutputBoundary;
import usecases.remove_friend.RemoveFriendOutputData;

public class RemoveFriendPresenter implements RemoveFriendOutputBoundary {
    private final RemoveFriendViewModel removeFriendViewModel;
    private final ViewManagerModel viewManagerModel;

    public RemoveFriendPresenter(RemoveFriendViewModel removeFriendViewModel, ViewManagerModel viewManagerModel) {
        this.removeFriendViewModel = removeFriendViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(RemoveFriendOutputData outputData) {
        RemoveFriendState removeFriendState = removeFriendViewModel.getState();
        removeFriendState.setFriendName(outputData.getFriendID());
        removeFriendViewModel.firePropertyChanged("removeFriendSuccess");
    }
}
