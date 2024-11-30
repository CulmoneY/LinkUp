
package usecases.remove_friend;

import entity.User;

public class RemoveFriendInteractor implements RemoveFriendInputBoundary {
    private RemoveFriendDataAccessInterface dataAccess;
    private RemoveFriendOutputBoundary presenter;

    public RemoveFriendInteractor(RemoveFriendDataAccessInterface dataAccess, RemoveFriendOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void executeRemoveFriend(RemoveFriendInputData input) {
        dataAccess.removeFriend(input.getUser().getName(), input.getFriendID());
        for (User friend : input.getUser().getFriends()) {
            if (friend.getName().equals(input.getFriendID())) {
                input.getUser().getFriends().remove(friend);
                break;
            }
        }
        presenter.setPassView(new RemoveFriendOutputData(input.getFriendID()));
    }
}