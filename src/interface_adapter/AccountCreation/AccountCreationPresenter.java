package interface_adapter.AccountCreation;

import usecases.account_creation.AccountCreationOutputBoundary;
import usecases.account_creation.AccountCreationOutputData;

public class AccountCreationPresenter implements AccountCreationOutputBoundary {

    public AccountCreationPresenter() {
        private final SignupViewModel signupViewModel;
        private final LoginViewModel loginViewModel;
    }

        public SignupPresenter(SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
            this.signupViewModel = signupViewModel;
            this.loginViewModel = loginViewModel;
    }

    @Override
    public void setPassView(AccountCreationOutputData user) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(user.getUsername());
        this.loginViewModel.setState(loginState);

        viewManagerModel.setState(loginViewModel.getViewName());
    }

    @Override
    public void setFailView(String error) {
        final AccountCreationState accountCreationState = accountCreationViewModel.getState();
        accountCreationState.setUsernameError(error);
    }
}
