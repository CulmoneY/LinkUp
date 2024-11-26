package interface_adapter.RemoveFriend;

import entity.User;
import usecases.remove_friend.RemoveFriendInputBoundary;
import usecases.remove_friend.RemoveFriendInputData;

public class RemoveFriendController {
    private final RemoveFriendInputBoundary removeFriendInteractor;

    public RemoveFriendController(RemoveFriendInputBoundary removeFriendInteractor) {
        this.removeFriendInteractor = removeFriendInteractor;
    }

    public void execute(User user, String friendID) {
        removeFriendInteractor.executeRemoveFriend(new RemoveFriendInputData(user, friendID));
    }
}
