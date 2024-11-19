package usecases.add_friend;
import entity.User;

public class AddFriendInputData {
    private User user;
    private String friendUsername;

    public AddFriendInputData(User user, String friendUsername) {
        this.user = user;
        this.friendUsername = friendUsername;
    }

    public User getUser() {
        return user;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}
