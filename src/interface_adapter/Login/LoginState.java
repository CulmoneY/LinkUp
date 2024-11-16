package interface_adapter.Login;
import entity.User;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String username = "";
    private String loginError;

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }


}