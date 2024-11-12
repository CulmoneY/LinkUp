/**
 * Obtained from CSC207-2024F-Uoft/lab-5 starter code.
 */
package interface_adapter.AccountCreation;

/**
 * The state for the Signup View Model.
 */
public class AccountCreationState {
    private String username = "";
    private String usernameError;
    private String password = "";
    private String passwordError;
    private String errorMessage;

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

//    @Override
//    public String toString() {
//
//        //TODO: Implement this
//    }
}