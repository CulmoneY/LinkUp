package usecase.add_friend;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.add_friend.AddFriendInputData;

import static org.junit.jupiter.api.Assertions.*;

public class AddFriendInputDataTest {

    private User user;
    private String friendUsername;
    private AddFriendInputData addFriendInputData;

    @BeforeEach
    public void setUp() {
        // Create a CommonUser object for testing
        user = new CommonUser("john_doe", "password123", "English");
        friendUsername = "jane_doe"; // A sample username for the friend

        // Create an instance of AddFriendInputData with the user and friendUsername
        addFriendInputData = new AddFriendInputData(user, friendUsername);
    }

    @Test
    public void testConstructorAndGetters() {
        // Verify that the user and friendUsername are properly set
        assertEquals(user, addFriendInputData.getUser(), "The user should be correctly initialized.");
        assertEquals(friendUsername, addFriendInputData.getFriendUsername(), "The friend username should be correctly initialized.");
    }

    @Test
    public void testGetUser() {
        // Ensure that the getUser method returns the correct user
        assertSame(user, addFriendInputData.getUser(), "The getUser method should return the correct user.");
    }

    @Test
    public void testGetFriendUsername() {
        // Ensure that the getFriendUsername method returns the correct friend username
        assertEquals(friendUsername, addFriendInputData.getFriendUsername(), "The getFriendUsername method should return the correct friend username.");
    }
}
