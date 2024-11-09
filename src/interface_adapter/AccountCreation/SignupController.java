package interface_adapter.AccountCreation;

import usecases.account_creation.AccountCreationInputBoundary;
import usecases.account_creation.AccountCreationInputData;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for handling user signup.
 */
public class SignupController {
    private final AccountCreationInputBoundary accountCreationInputBoundary;
    /**
     * Constructor for SignupController.
     *
     * @param accountCreationInputBoundary the input boundary for the account creation use case
     */
    public SignupController(AccountCreationInputBoundary accountCreationInputBoundary) {
        this.accountCreationInputBoundary = accountCreationInputBoundary;
    }
    /**
     * Executes the account creation process.
     *
     * @param username       the username of the new account
     * @param password       the password for the new account
     * @param repeatPassword the password repeated for confirmation
     * @param language       the language of the user
     * @throws IllegalArgumentException if validation fails
     */

    public void execute(String username, String password, String repeatPassword, String language) {
        AccountCreationInputData inputData = new AccountCreationInputData(username, password, repeatPassword, language);
        accountCreationInputBoundary.execute(inputData);
    }
}