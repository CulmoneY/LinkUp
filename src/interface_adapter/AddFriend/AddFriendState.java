package interface_adapter.AddFriend;

public class AddFriendState {
    String username;
    String friendUsername;
    String errorMessage;

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
