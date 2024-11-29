package usecase.add_friend;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.add_friend.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddFriendInteractorTest {

    private AddFriendInteractor interactor;
    private AddFriendOutputBoundary outputPresenter;
    private AddFriendDataAccessInterface dataAccess;
    private User user;
    private AddFriendInputData inputData;

    @BeforeEach
    public void setUp() {
        outputPresenter = new AddFriendOutputBoundaryStub();
        dataAccess = new AddFriendDataAccessStub();
        user = new CommonUser("john_doe", "password123", "English");
        inputData = new AddFriendInputData(user, "jane_doe");

        interactor = new AddFriendInteractor(outputPresenter, dataAccess);
    }

    @Test
    public void testExecuteAddFriend_MissingFields() {
        AddFriendInputData inputDataWithEmptyUsername = new AddFriendInputData(user, "");
        interactor.executeAddFriend(inputDataWithEmptyUsername);
        assertEquals("Please Fill in all Fields!", ((AddFriendOutputBoundaryStub) outputPresenter).getFailMessage());
    }

    @Test
    public void testExecuteAddFriend_AccountDoesNotExist() {
        ((AddFriendDataAccessStub) dataAccess).setAccountExists(false);
        interactor.executeAddFriend(inputData);
        assertEquals("Account does not exist!", ((AddFriendOutputBoundaryStub) outputPresenter).getFailMessage());
    }

    @Test
    public void testExecuteAddFriend_Success() {
        // Account exists and they're not already friends
        ((AddFriendDataAccessStub) dataAccess).setAccountExists(true);
        ((AddFriendDataAccessStub) dataAccess).setIsFriend(false);

        User friend = new CommonUser("jane_doe", "password456", "English");
        ((AddFriendDataAccessStub) dataAccess).setFriend(friend);

        interactor.executeAddFriend(inputData);

        assertTrue(user.getFriends().contains(friend));  // Verify the user is now friends with jane_doe
        assertNotNull(((AddFriendOutputBoundaryStub) outputPresenter).getSuccessData());  // Verify success data was passed
    }

    private static class AddFriendOutputBoundaryStub implements AddFriendOutputBoundary {
        private String failMessage;
        private AddFriendOutputData successData;

        @Override
        public void setFailViewData(String message) {
            this.failMessage = message;
        }

        @Override
        public void setPassViewData(AddFriendOutputData outputData) {
            this.successData = outputData;
        }

        public String getFailMessage() {
            return failMessage;
        }

        public AddFriendOutputData getSuccessData() {
            return successData;
        }
    }

    private static class AddFriendDataAccessStub implements AddFriendDataAccessInterface {
        private boolean accountExists;
        private boolean isFriend;
        private User friend;

        @Override
        public boolean accountExists(String username) {
            return accountExists;
        }

        @Override
        public boolean isFriend(String username, String friendUsername) {
            return isFriend;  // Return the value set by setIsFriend()
        }

        @Override
        public User addFriend(String username, String friendUsername) {
            return friend;
        }

        public void setAccountExists(boolean accountExists) {
            this.accountExists = accountExists;
        }

        public void setIsFriend(boolean isFriend) {
            this.isFriend = isFriend;
        }

        public void setFriend(User friend) {
            this.friend = friend;
        }
    }
}
