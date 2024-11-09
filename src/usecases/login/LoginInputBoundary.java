package usecases.login;

/**
 * Interface for the login use case input boundary.
 * This interface defines the method for executing a login operation
 * using the provided login input data.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login process with the provided input data.
     *
     * @param loginInputData The data required for logging in, including user credentials.
     */
    void execute(LoginInputData loginInputData);
}