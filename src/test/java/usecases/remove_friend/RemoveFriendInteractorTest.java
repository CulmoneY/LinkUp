package usecases.remove_friend;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RemoveFriendInteractorTest {

    private RemoveFriendDataAccessInterface dataAccess;
    private RemoveFriendOutputBoundary presenter;
    private RemoveFriendInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = mock(RemoveFriendDataAccessInterface.class);
        presenter = mock(RemoveFriendOutputBoundary.class);
        interactor = new RemoveFriendInteractor(dataAccess, presenter);
    }

    @Test
    void testExecuteRemoveFriendFriendExists() {
        // Arrange
        User user = mock(User.class);
        List<User> friends = new ArrayList<>();
        User friend = mock(User.class);

        when(user.getName()).thenReturn("TestUser");
        when(friend.getName()).thenReturn("Friend1");
        friends.add(friend);
        when(user.getFriends()).thenReturn(friends);

        RemoveFriendInputData inputData = new RemoveFriendInputData(user, "Friend1");

        // Act
        interactor.executeRemoveFriend(inputData);

        // Assert
        verify(dataAccess).removeFriend("TestUser", "Friend1");
        assertEquals(0, friends.size()); // Friend should be removed

        ArgumentCaptor<RemoveFriendOutputData> captor = ArgumentCaptor.forClass(RemoveFriendOutputData.class);
        verify(presenter).setPassView(captor.capture());
        assertEquals("Friend1", captor.getValue().getFriendID());
    }

    @Test
    void testExecuteRemoveFriendFriendDoesNotExist() {
        // Arrange
        User user = mock(User.class);
        List<User> friends = new ArrayList<>();
        User friend = mock(User.class);

        when(user.getName()).thenReturn("TestUser");
        when(friend.getName()).thenReturn("Friend1");
        friends.add(friend);
        when(user.getFriends()).thenReturn(friends);

        RemoveFriendInputData inputData = new RemoveFriendInputData(user, "NonexistentFriend");

        // Act
        interactor.executeRemoveFriend(inputData);

        // Assert
        verify(dataAccess).removeFriend("TestUser", "NonexistentFriend");
        assertEquals(1, friends.size()); // No friend should be removed

        ArgumentCaptor<RemoveFriendOutputData> captor = ArgumentCaptor.forClass(RemoveFriendOutputData.class);
        verify(presenter).setPassView(captor.capture());
        assertEquals("NonexistentFriend", captor.getValue().getFriendID());
    }
}
