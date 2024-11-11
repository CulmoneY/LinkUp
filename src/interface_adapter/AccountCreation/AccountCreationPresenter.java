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
        //TODO: On success, switch to the login view.
        System.out.println("Success, account has been created. Check the MongoDB Database.");
    }

    @Override
    public void setFailView(String error) {
//        AccountCreationState accountCreationState = accountCreationViewModel.getState();
//        accountCreationState.setUsernameError(error);
//        //TODO: Set correct error and display an error screen.
//        accountCreationViewModel.setState(accountCreationState);
//        accountCreationViewModel.firePropertyChanged(error);
        System.out.println("Error");
    }
}
