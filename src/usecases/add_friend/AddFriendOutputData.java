package usecases.add_friend;

public class AddFriendOutputData {
    private String username;
    private String friendUsername;

    public AddFriendOutputData(String username, String friendUsername) {
        this.username = username;
        this.friendUsername = friendUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}
