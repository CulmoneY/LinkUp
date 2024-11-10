package usecases.account_creation;

public interface AccountCreationInputBoundary {
    void execute(AccountCreationInputData inputData);

    void switchToLoginView();
}
