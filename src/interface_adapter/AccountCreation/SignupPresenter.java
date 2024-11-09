package interface_adapter.AccountCreation;

import interface_adapter.ViewModelManager;
import usecases.account_creation.AccountCreationOutputBoundary;
import usecases.account_creation.AccountCreationOutputData;
import interface_adapter.Login.LoginViewModel;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SignupPresenter implements AccountCreationOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final ViewModelManager viewModelManager;
    private final LoginViewModel loginViewModel;


    /**
     * Constructs a new SignupPresenter with the specified view model manager, signup view model, and login view model.
     *
     * @param viewModelManager the view model manager
     * @param signupViewModel  the signup view model
     * @param loginViewModel   the login view model
     */
    public SignupPresenter(ViewModelManager viewModelManager, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewModelManager = viewModelManager;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Sets the pass view for successful account creation.
     * Updates the signup view model state, fires property change events, and activates the login view.
     *
     * @param user the output data containing user information
     */
    @Override
    public void setPassView(AccountCreationOutputData user) {

        SignupState signupState = signupViewModel.getState();
        signupState.setUsername(user.getUsername());
        signupState.setError(null);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        viewModelManager.setActiveView("LoginView");
        viewModelManager.firePropertyChanged();

        signupViewModel.firePropertyChanged("success", null, "Account successfully created!");
    }

    /**
     * Sets the fail view for unsuccessful account creation.
     * Updates the signup view model state with the error message and fires property change events.
     *
     * @param error the error message
     */
    @Override
    public void setFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setError(error);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged("generalError", null, error);
    }
}

