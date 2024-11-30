package usecases.remove_friend;

import entity.User;

public class RemoveFriendInputData {
    User user;
    String friendID;

    public RemoveFriendInputData(User user, String friendID) {
        this.user = user;
        this.friendID = friendID;
    }

    public User getUser() {
        return user;
    }

    public String getFriendID() {
        return friendID;
    }

}