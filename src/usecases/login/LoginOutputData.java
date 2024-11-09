package usecases.login;

import entity.User;

/**
 * Data class that encapsulates the output data for a successful login.
 * This class holds the information about the user who has successfully logged in.
 */
public class LoginOutputData {
    private final User user;

    /**
     * Constructs an instance of {@code LoginOutputData} with the specified user.
     *
     * @param user The user who has successfully logged in.
     */
    public LoginOutputData(User user) {
        this.user = user;
    }

    /**
     * Retrieves the user associated with this login output data.
     *
     * @return The user who has successfully logged in.
     */
    public User getUser() {
        return user;
    }
}