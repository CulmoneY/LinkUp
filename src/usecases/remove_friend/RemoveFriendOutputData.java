package usecases.remove_friend;

public class RemoveFriendOutputData {
    String friendID;

    public RemoveFriendOutputData(String friendID) {
        this.friendID = friendID;
    }

    public String getFriendID() {
        return friendID;
    }
}
