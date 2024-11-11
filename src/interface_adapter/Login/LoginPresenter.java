package interface_adapter.Login;

import interface_adapter.ViewManagerModel;
//import interface_adapter.logged_in.LoggedInState;
//import interface_adapter.logged_in.LoggedInViewModel;
import usecases.login.LoginOutputBoundary;
import usecases.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    //TODO: Change the view toLogIn
    @Override
    public void setPassView(LoginOutputData outputData) {

    }

    @Override
    public void setFailView(String error) {

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