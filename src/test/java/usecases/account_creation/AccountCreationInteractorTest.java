package usecases.account_creation;

import entity.UserFactory;
import entity.User;
import entity.CommonUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import usecases.account_creation.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountCreationInteractorTest {

    private AccountCreationInteractor interactor;
    private AccountCreationUserDataAccessInterface accountDataAccess;
    private AccountCreationOutputBoundary accountPresenter;
    private UserFactory userFactory;

    // Mock-like classes to simulate real dependencies
    class MockAccountCreationUserDataAccess implements AccountCreationUserDataAccessInterface {
        private boolean accountExistsReturnValue = false;

        @Override
        public boolean accountExists(String username) {
            return accountExistsReturnValue;
        }

        @Override
        public void saveUser(User user) {
            // Simulate saving user
        }

        public void setAccountExistsReturnValue(boolean value) {
            this.accountExistsReturnValue = value;
        }
    }

    class MockAccountCreationOutput implements AccountCreationOutputBoundary {

        private String failView;
        private AccountCreationOutputData outputData;

        @Override
        public void setFailView(String errorType) {
            this.failView = errorType;
        }

        @Override
        public void setPassView(AccountCreationOutputData outputData) {
            this.outputData = outputData;
        }

        public String getFailView() {
            return failView;
        }

        public AccountCreationOutputData getPassView() {
            return outputData;
        }
    }

    @BeforeEach
    void setUp() {
        // Initialize the mock dependencies
        accountDataAccess = new MockAccountCreationUserDataAccess();
        accountPresenter = new MockAccountCreationOutput();
        userFactory = new CommonUserFactory();  // Use a real factory

        // Initialize the interactor with mock dependencies
        interactor = new AccountCreationInteractor(accountDataAccess, accountPresenter, userFactory);
    }

    @Nested
    class AccountCreationTests {

        @Test
        void testAccountExists() {
            // Simulate an existing account
            ((MockAccountCreationUserDataAccess) accountDataAccess).setAccountExistsReturnValue(true);

            // Setup input data for an existing account
            AccountCreationInputData inputData = new AccountCreationInputData("existingUser", "password123", "password123", "English");

            // Execute the interactor
            interactor.execute(inputData);

            // Verify the fail view is set correctly
            assertEquals("account_exists", ((MockAccountCreationOutput) accountPresenter).getFailView());
        }

        @Test
        void testPasswordsDoNotMatch() {
            // Simulate non-existing account
            ((MockAccountCreationUserDataAccess) accountDataAccess).setAccountExistsReturnValue(false);

            // Setup input data where passwords don't match
            AccountCreationInputData inputData = new AccountCreationInputData("newUser", "password123", "password456", "English");

            // Execute the interactor
            interactor.execute(inputData);

            // Verify that the correct fail view is set
            assertEquals("unmatched_passwords", ((MockAccountCreationOutput) accountPresenter).getFailView());
        }

        @Test
        void testMissingFields() {
            // Simulate non-existing account
            ((MockAccountCreationUserDataAccess) accountDataAccess).setAccountExistsReturnValue(false);

            // Setup input data where a required field (username) is missing
            AccountCreationInputData inputData = new AccountCreationInputData("", "password123", "password123", "English");

            // Execute the interactor
            interactor.execute(inputData);

            // Verify that the fail view is set to "missing_fields"
            assertEquals("missing_fields", ((MockAccountCreationOutput) accountPresenter).getFailView());
        }

        @Test
        void testSuccessfulAccountCreation() {
            // Simulate non-existing account
            ((MockAccountCreationUserDataAccess) accountDataAccess).setAccountExistsReturnValue(false);

            // Setup input data for valid account creation
            AccountCreationInputData inputData = new AccountCreationInputData("newUser", "password123", "password123", "English");

            // Execute the interactor
            interactor.execute(inputData);

            // Verify that the user was saved and pass view is set with user data
            AccountCreationOutputData outputData = ((MockAccountCreationOutput) accountPresenter).getPassView();
            assertNotNull(outputData);
            assertEquals("newUser", outputData.getUsername());
            assertEquals("English", outputData.getLanguage());
        }

        @Test
        void testSwitchToLoginView() {
            // Verify that the switchToLoginView method can be invoked
            assertDoesNotThrow(() -> interactor.switchToLoginView());
        }
    }
}
