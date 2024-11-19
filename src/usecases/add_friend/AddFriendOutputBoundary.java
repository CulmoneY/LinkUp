package usecases.add_friend;

public interface AddFriendOutputBoundary {
    void setPassViewData(AddFriendOutputData outputData);

    void setFailViewData(String error);

}
