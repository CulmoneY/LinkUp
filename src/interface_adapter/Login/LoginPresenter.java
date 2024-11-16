package interface_adapter.Login;

import interface_adapter.ViewManagerModel;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import usecases.login.LoginInputBoundary;
import usecases.login.LoginOutputBoundary;
import usecases.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void setPassView(LoginOutputData outputData) {
        LoginState loginState = loginViewModel.getState();
        viewManagerModel.setUser(outputData.getUser());
        loginState.setLoginError(null);
        loginState.setUsername(outputData.getUsername());
        viewManagerModel.setUser(outputData.getUser());
        loginViewModel.firePropertyChanged("LoginSuccess");
    }

    @Override
    public void setFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged("LoginError");
    }

//    private final LoginViewModel loginViewModel;
//    private final LoggedInViewModel loggedInViewModel;
//    private final ViewManagerModel viewManagerModel;
//
//    public LoginPresenter(ViewManagerModel viewManagerModel,
//                          LoggedInViewModel loggedInViewModel,
//                          LoginViewModel loginViewModel) {
//        this.viewManagerModel = viewManagerModel;
//        this.loggedInViewModel = loggedInViewModel;
//        this.loginViewModel = loginViewModel;
//    }
//
//    @Override
//    public void prepareSuccessView(LoginOutputData response) {
//        // On success, switch to the logged in view.
//
//        final LoggedInState loggedInState = loggedInViewModel.getState();
//        loggedInState.setUsername(response.getUsername());
//        this.loggedInViewModel.setState(loggedInState);
//        this.loggedInViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(loggedInViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//        final LoginState loginState = loginViewModel.getState();
//        loginState.setLoginError(error);
//        loginViewModel.firePropertyChanged();
//    }
}