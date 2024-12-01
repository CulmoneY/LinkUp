package usecases.login;

import entity.CommonUser;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import usecases.login.LoginOutputData;

class LoginOutputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Given a CommonUser and username
        CommonUser user = new CommonUser("testUser", "password123", "English");
        String username = "testUser";

        // When creating an instance of LoginOutputData
        LoginOutputData outputData = new LoginOutputData(user, username);

        // Then the user and username should be correctly set and retrievable
        assertEquals(user, outputData.getUser(), "User should be set correctly");
        assertEquals(username, outputData.getUsername(), "Username should be set correctly");
    }

    @Test
    void testNullUser() {
        // Given a null user and a username
        CommonUser user = null;
        String username = "testUser";

        // When creating an instance of LoginOutputData
        LoginOutputData outputData = new LoginOutputData(user, username);

        // Then the user should be null and the username should be correctly set
        assertNull(outputData.getUser(), "User should be null");
        assertEquals(username, outputData.getUsername(), "Username should be set correctly");
    }

    @Test
    void testNullUsername() {
        // Given a CommonUser and a null username
        CommonUser user = new CommonUser("testUser", "password123", "English");
        String username = null;

        // When creating an instance of LoginOutputData
        LoginOutputData outputData = new LoginOutputData(user, username);

        // Then the user should be correctly set and the username should be null
        assertEquals(user, outputData.getUser(), "User should be set correctly");
        assertNull(outputData.getUsername(), "Username should be null");
    }

    @Test
    void testBothNull() {
        // Given a null user and a null username
        CommonUser user = null;
        String username = null;

        // When creating an instance of LoginOutputData
        LoginOutputData outputData = new LoginOutputData(user, username);

        // Then both the user and username should be null
        assertNull(outputData.getUser(), "User should be null");
        assertNull(outputData.getUsername(), "Username should be null");
    }
}

