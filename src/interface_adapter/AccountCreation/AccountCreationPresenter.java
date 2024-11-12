package interface_adapter.AccountCreation;

import interface_adapter.Login.*;
import usecases.account_creation.AccountCreationOutputBoundary;
import usecases.account_creation.AccountCreationOutputData;
import interface_adapter.ViewManagerModel;

public class AccountCreationPresenter implements AccountCreationOutputBoundary {
    private final AccountCreationViewModel accountCreationViewModel;
    private final ViewManagerModel viewManagerModel;
//    private final LoginViewModel loginViewModel;
// TODO: Implement LoginView Model and connect it to setPassView
    public AccountCreationPresenter(AccountCreationViewModel accountViewModel, ViewManagerModel viewManagerModel) {
        this.accountCreationViewModel = accountViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(AccountCreationOutputData response) {
        AccountCreationState accountCreationState = accountCreationViewModel.getState();
        accountCreationState.setUsernameError(response.getUsername());
        accountCreationState.setPasswordError(null);
        accountCreationViewModel.firePropertyChanged("success");
    }

    @Override
    public void setFailView(String error) {
        AccountCreationState accountCreationState = accountCreationViewModel.getState();
        accountCreationState.setErrorCode(error);
        if (error.equals("unmatched_passwords")) {
            accountCreationState.setErrorCode("Passwords do not match.");
        }
        if (error.equals("missing_fields")) {
            accountCreationState.setErrorCode("Please fill in all fields.");
        }
        if (error.equals("account_exists")) {
            accountCreationState.setErrorCode("Account already exists.");
        }
        accountCreationViewModel.firePropertyChanged("error");
    }
}
