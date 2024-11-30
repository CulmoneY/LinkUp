package usecases.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import usecases.login.LoginInputData;

class LoginInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Given test data
        String username = "testUser";
        String password = "testPassword123";

        // When creating an instance of LoginInputData
        LoginInputData inputData = new LoginInputData(username, password);

        // Then the username and password should be correctly set and retrievable
        assertEquals(username, inputData.getUsername(), "Username should be set correctly");
        assertEquals(password, inputData.getPassword(), "Password should be set correctly");
    }

    @Test
    void testEmptyUsername() {
        // Given an empty username
        String username = "";
        String password = "password123";

        // When creating an instance of LoginInputData
        LoginInputData inputData = new LoginInputData(username, password);

        // Then the username should be an empty string
        assertEquals(username, inputData.getUsername(), "Username should be an empty string");
        assertEquals(password, inputData.getPassword(), "Password should be set correctly");
    }

    @Test
    void testEmptyPassword() {
        // Given an empty password
        String username = "testUser";
        String password = "";

        // When creating an instance of LoginInputData
        LoginInputData inputData = new LoginInputData(username, password);

        // Then the password should be an empty string
        assertEquals(username, inputData.getUsername(), "Username should be set correctly");
        assertEquals(password, inputData.getPassword(), "Password should be an empty string");
    }

    @Test
    void testNullUsername() {
        // Given a null username
        String username = null;
        String password = "validPassword123";

        // When creating an instance of LoginInputData
        LoginInputData inputData = new LoginInputData(username, password);

        // Then the username should be null
        assertNull(inputData.getUsername(), "Username should be null");
        assertEquals(password, inputData.getPassword(), "Password should be set correctly");
    }

    @Test
    void testNullPassword() {
        // Given a null password
        String username = "testUser";
        String password = null;

        // When creating an instance of LoginInputData
        LoginInputData inputData = new LoginInputData(username, password);

        // Then the password should be null
        assertEquals(username, inputData.getUsername(), "Username should be set correctly");
        assertNull(inputData.getPassword(), "Password should be null");
    }
}
