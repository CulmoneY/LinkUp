package interface_adapter.AddFriend;

import entity.User;
import usecases.add_friend.AddFriendInputBoundary;
import usecases.add_friend.AddFriendInputData;

public class AddFriendController {
    private final AddFriendInputBoundary addFriendInteractor;

    public AddFriendController(AddFriendInputBoundary addFriendInteractor) {
        this.addFriendInteractor = addFriendInteractor;
    }

    public void execute(User user, String friendUsername) {
        addFriendInteractor.executeAddFriend(new AddFriendInputData(user, friendUsername));
    }
}
