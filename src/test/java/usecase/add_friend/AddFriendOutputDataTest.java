package usecase.add_friend;

import org.junit.jupiter.api.Test;
import usecases.add_friend.AddFriendOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class AddFriendOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String username = "john_doe";
        String friendUsername = "jane_doe";

        AddFriendOutputData outputData = new AddFriendOutputData(username, friendUsername);

        // Test that the username and friendUsername are correctly set by the constructor
        assertEquals(username, outputData.getUsername(), "Username should match the one passed in the constructor.");
        assertEquals(friendUsername, outputData.getFriendUsername(), "Friend username should match the one passed in the constructor.");
    }

    @Test
    public void testGetUsername() {
        String username = "john_doe";
        String friendUsername = "jane_doe";
        AddFriendOutputData outputData = new AddFriendOutputData(username, friendUsername);

        // Test that getUsername returns the correct username
        assertEquals(username, outputData.getUsername(), "getUsername should return the correct username.");
    }

    @Test
    public void testGetFriendUsername() {
        String username = "john_doe";
        String friendUsername = "jane_doe";
        AddFriendOutputData outputData = new AddFriendOutputData(username, friendUsername);

        // Test that getFriendUsername returns the correct friend username
        assertEquals(friendUsername, outputData.getFriendUsername(), "getFriendUsername should return the correct friend username.");
    }
}
