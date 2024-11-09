package interface_adapter.Login;

/**
 * The LoginState class represents the state of the login menu.
 * It holds the user inputs and any error messages.
 */
public class LoginState {

    private String username = "";
    private String password = "";
    private String usernameError = null;
    private String passwordError = null;

    /**
     * Constructs a new LoginState by copying the state from another LoginState.
     *
     * @param copy the LoginState to copy from
     */
    public LoginState(LoginState copy) {
        username = copy.username;
        password = copy.password;
        usernameError = copy.usernameError;
        passwordError = copy.passwordError;
    }

    /**
     * Constructs a new LoginState with default values.
     */
    public LoginState() {}

    /**
     * Gets the password of the user.
     *
     * @return the password of the current user
     */
    public String getPassword() {
        return password;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}