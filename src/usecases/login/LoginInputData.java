package usecases.login;


/**
 * Data transfer object for encapsulating the input data required for a login operation.
 * This class holds the user credentials necessary for logging in.
 */
public class LoginInputData {
    private String username;
    private String password;

    /**
     * Constructs a new {@code LoginInputData} object with the specified username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The password associated with the username.
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;

    }

    /**
     * Returns the username associated with this login input data.
     *
     * @return The username of the user attempting to log in.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password associated with this login input data.
     *
     * @return The password of the user attempting to log in.
     */
    public String getPassword() {
        return password;
    }
}