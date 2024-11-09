package interface_adapter.Login;

import interface_adapter.ViewModelManager;
import usecases.login.LoginOutputBoundary;
import usecases.login.LoginOutputData;
import views.MainView;
/**
 * The LoginPresenter class handles the presentation logic for account logins.
 * It updates the view model based on the result of the login process.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel viewModel;
    private final ViewModelManager viewModelManager;

    /**
     * Constructs a new Login Presenter with the specified view model manager and view model.
     * @param viewModel   the login view model
     * @param viewModelManager   the view model manager
     */
    public LoginPresenter(LoginViewModel viewModel, ViewModelManager viewModelManager) {
        this.viewModel = viewModel;
        this.viewModelManager = viewModelManager;
    }

    /**
     * Sets the pass view for successful logins
     * Updates the login view model state, fires property change events, and activates the login view.
     *
     * @param user the output data containing user information
     */
    @Override
    public void showSuccessScreen(LoginOutputData user) {
        viewModelManager.setCurrentUser(user.getUser()); // Use the User object
        viewModelManager.setActiveView(MainView.viewName);
        viewModelManager.firePropertyChanged();
    }

    /**
     * Sets the fail view for unsuccessful logins.
     * Updates the login view model state with the error message and fires property change events.
     *
     * @param error the error message
     */
    @Override
    public void showFailureScreen(String error) {
        LoginState loginState = viewModel.getState();
        loginState.setPasswordError(error);
        viewModel.setState(loginState);
        viewModel.firePropertyChanged("generalError", null, error);
    }
}