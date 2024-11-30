package usecases.account_creation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.account_creation.AccountCreationInputData;

import static org.junit.jupiter.api.Assertions.*;

public class AccountCreationInputDataTest {

    private AccountCreationInputData accountData;

    @BeforeEach
    public void setUp() {
        // Setup the test data by creating an instance of AccountCreationInputData
        accountData = new AccountCreationInputData("john_doe", "password123", "password123", "English");
    }

    @Test
    public void testGetUsername() {
        // Verify that the getUsername method returns the expected username.
        assertEquals("john_doe", accountData.getUsername(), "The username should match the expected value.");
    }

    @Test
    public void testGetPassword() {
        // Verify that the getPassword method returns the expected password.
        assertEquals("password123", accountData.getPassword(), "The password should match the expected value.");
    }

    @Test
    public void testGetRepeatedPassword() {
        // Verify that the getRepeatedPassword method returns the expected repeated password.
        assertEquals("password123", accountData.getRepeatedPassword(), "The repeated password should match the expected value.");
    }

    @Test
    public void testGetLanguage() {
        // Verify that the getLanguage method returns the expected language.
        assertEquals("English", accountData.getLanguage(), "The language should match the expected value.");
    }

    @Test
    public void testConstructorInitialization() {
        // Create a new AccountCreationInputData object with different values
        AccountCreationInputData newAccount = new AccountCreationInputData("jane_doe", "newpassword", "newpassword", "French");

        // Verify the new object's properties are initialized correctly.
        assertEquals("jane_doe", newAccount.getUsername(), "The username should be initialized correctly.");
        assertEquals("newpassword", newAccount.getPassword(), "The password should be initialized correctly.");
        assertEquals("newpassword", newAccount.getRepeatedPassword(), "The repeated password should be initialized correctly.");
        assertEquals("French", newAccount.getLanguage(), "The language should be initialized correctly.");
    }

    @Test
    public void testUsernameNotNull() {
        // Verify that the username is not null.
        assertNotNull(accountData.getUsername(), "Username should not be null.");
    }

    @Test
    public void testPasswordNotNull() {
        // Verify that the password is not null.
        assertNotNull(accountData.getPassword(), "Password should not be null.");
    }

    @Test
    public void testRepeatedPasswordNotNull() {
        // Verify that the repeated password is not null.
        assertNotNull(accountData.getRepeatedPassword(), "Repeated password should not be null.");
    }

    @Test
    public void testLanguageNotNull() {
        // Verify that the language is not null.
        assertNotNull(accountData.getLanguage(), "Language should not be null.");
    }

    @Test
    public void testPasswordMatch() {
        // Verify that the password and repeated password match.
        assertEquals(accountData.getPassword(), accountData.getRepeatedPassword(), "Password and repeated password should match.");
    }
}
