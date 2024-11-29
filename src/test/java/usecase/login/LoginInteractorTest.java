package usecase.login;

import entity.CommonUser;
import entity.User;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.Login.LoginPresenter;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.login.LoginInputData;
import usecases.login.LoginInteractor;
import usecases.login.LoginUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInteractorTest {

    private LoginInteractor loginInteractor;
    private LoginUserDataAccessInterface userDataAccess;
    private LoginPresenter presenter;
    private UserFactory userFactory;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void setUp() {
        // Mocked user
        CommonUser user = new CommonUser("testUser", "password123", "English");

        // Mock the LoginUserDataAccessInterface to simulate user data access
        userDataAccess = new LoginUserDataAccessInterface() {
            @Override
            public User getUser(String username) {
                // Return user if the username matches, otherwise return null
                return username.equals("testUser") ? user : null;
            }

            @Override
            public boolean accountExists(String username) {
                // Return true if the account exists
                return username.equals("testUser");
            }
        };

        // Mock ViewManagerModel and LoginViewModel
        viewManagerModel = new ViewManagerModel() {
            private User user;

            @Override
            public void setUser(User user) {
                this.user = user;
            }

            public User getUser() {
                return this.user;
            }
        };

        loginViewModel = new LoginViewModel() {
            private LoginState loginState = new LoginState();

            @Override
            public LoginState getState() {
                return loginState;
            }

            @Override
            public void firePropertyChanged(String property) {
                // Mocked method for triggering property change
            }
        };

        // Initialize presenter with the mocked models
        presenter = new LoginPresenter(viewManagerModel, loginViewModel);

        // Initialize user factory
        userFactory = new CommonUserFactory();

        // Initialize LoginInteractor
        loginInteractor = new LoginInteractor(userDataAccess, presenter, userFactory);
    }

    @Test
    void testMissingFields() {
        // Given input data with missing fields (empty username)
        LoginInputData inputData = new LoginInputData("", "password123");

        // When executing the login
        loginInteractor.execute(inputData);

        // Then the fail view should be triggered with the correct message
        assertEquals("Please Fill in All Fields!", loginViewModel.getState().getLoginError(), "Expected missing fields message.");
    }

    @Test
    void testAccountDoesNotExist() {
        // Given input data for a user that doesn't exist
        LoginInputData inputData = new LoginInputData("nonExistentUser", "password123");

        // When executing the login
        loginInteractor.execute(inputData);

        // Then the fail view should be triggered with the correct message
        assertEquals("This User Does Not Exist!", loginViewModel.getState().getLoginError(), "Expected account doesn't exist message.");
    }

    @Test
    void testIncorrectPassword() {
        // Given input data with a correct username but incorrect password
        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");

        // When executing the login
        loginInteractor.execute(inputData);

        // Then the fail view should be triggered with the correct message
        assertEquals("Incorrect Password!", loginViewModel.getState().getLoginError(), "Expected incorrect password message.");
    }

    @Test
    void testSuccessfulLogin() {
        // Given input data with correct username and password
        LoginInputData inputData = new LoginInputData("testUser", "password123");

        // When executing the login
        loginInteractor.execute(inputData);

        // Then the pass view should be triggered with the correct user and username
        assertNull(loginViewModel.getState().getLoginError(), "Expected no login error.");
        assertEquals("testUser", loginViewModel.getState().getUsername(), "Expected username to match.");
        assertEquals("testUser", viewManagerModel.getUser().getName(), "Expected user name to match.");
    }
}
