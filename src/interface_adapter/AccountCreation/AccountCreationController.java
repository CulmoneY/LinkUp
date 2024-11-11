package interface_adapter.AccountCreation;

import usecases.account_creation.AccountCreationInputBoundary;
import usecases.account_creation.AccountCreationInputData;

public class AccountCreationController {
    private final AccountCreationInputBoundary accountCreationInteractor;

    public AccountCreationController(AccountCreationInputBoundary accountCreationInteractor) {
        this.accountCreationInteractor = accountCreationInteractor;
    }

    public void execute(String username, String password, String repeatedPassword, String language) {
        final AccountCreationInputData accountCreationInputData = new AccountCreationInputData(
                username, password, repeatedPassword, language);
        accountCreationInteractor.execute(accountCreationInputData);
    }

    public void switchToLoginView() {
        accountCreationInteractor.switchToLoginView();
    }

}
