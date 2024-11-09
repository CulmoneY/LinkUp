package usecases.login;

/**
 * Interface for handling the output of the login process.
 * This interface defines methods to present the result of the login operation
 * to the user, including success or failure screens.
 */
public interface LoginOutputBoundary {

    /**
     * Displays a success screen with the details of the logged-in user.
     *
     * @param user The output data containing user information after a successful login.
     */
    void showSuccessScreen(LoginOutputData user);

    /**
     * Displays a failure screen with an error message.
     *
     * @param error The error message to be displayed when login fails.
     */
    void showFailureScreen(String error);

}