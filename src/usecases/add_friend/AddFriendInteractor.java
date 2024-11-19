package usecases.add_friend;

import entity.User;

public class AddFriendInteractor implements AddFriendInputBoundary {
    private AddFriendOutputBoundary outputPresenter;
    private AddFriendDataAccessInterface dataAccess;

    public AddFriendInteractor(AddFriendOutputBoundary outputPresenter, AddFriendDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void executeAddFriend(AddFriendInputData inputData) {
        if (missingFields(inputData)) {
            outputPresenter.setFailViewData("Please Fill in all Fields!");
        } else if (!accountExists(inputData)) {
            outputPresenter.setFailViewData("Account does not exist!");
        } else if (isFriend(inputData)) {
            outputPresenter.setFailViewData("You are already friends with " + inputData.getFriendUsername());
        } else {
            User friend = dataAccess.addFriend(inputData.getUser().getName(), inputData.getFriendUsername());
            inputData.getUser().addFriend(friend);
            outputPresenter.setPassViewData(new AddFriendOutputData(inputData.getUser().getName(), inputData.getFriendUsername()));
        }
    }

    private boolean accountExists(AddFriendInputData inputData) {
        return dataAccess.accountExists(inputData.getFriendUsername());
    }

    private boolean isFriend(AddFriendInputData inputData) {
        return dataAccess.isFriend(inputData.getUser().getName(), inputData.getFriendUsername());
    }

    private boolean missingFields(AddFriendInputData inputData) {
        return inputData.getFriendUsername().isEmpty();
    }
}
