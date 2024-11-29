package usecase.account_creation;

import org.junit.jupiter.api.Test;
import usecases.account_creation.AccountCreationOutputData;

import static org.junit.jupiter.api.Assertions.*;

class AccountCreationOutputDataTest {

    @Test
    void testGetUsername() {
        // Setup
        AccountCreationOutputData outputData = new AccountCreationOutputData("newUser", "English", true);

        // Test and assertion
        assertEquals("newUser", outputData.getUsername(), "Username should match the one provided.");
    }

    @Test
    void testGetLanguage() {
        // Setup
        AccountCreationOutputData outputData = new AccountCreationOutputData("newUser", "English", true);

        // Test and assertion
        assertEquals("English", outputData.getLanguage(), "Language should match the one provided.");
    }

    @Test
    void testGetSuccess() {
        // Setup
        AccountCreationOutputData outputData = new AccountCreationOutputData("newUser", "English", true);

        // Test and assertion
        assertTrue(outputData.getSuccess(), "Success should be true for a valid account creation.");
    }

    @Test
    void testFailureSuccess() {
        // Setup
        AccountCreationOutputData outputData = new AccountCreationOutputData("newUser", "English", false);

        // Test and assertion
        assertFalse(outputData.getSuccess(), "Success should be false if account creation fails.");
    }
}
