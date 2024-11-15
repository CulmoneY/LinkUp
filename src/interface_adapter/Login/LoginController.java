package interface_adapter.Login;
import usecases.login.LoginInputBoundary;
import usecases.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password) {
        final LoginInputData loginInputData = new LoginInputData(username, password);
        loginInteractor.execute(loginInputData);
    }
}
