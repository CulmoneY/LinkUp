package usecases.login;

import entity.UserFactory;
import usecases.account_creation.AccountCreationInputData;

public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccess;
    private final LoginOutputBoundary presenter;
    final UserFactory userFactory;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccess, LoginOutputBoundary presenter, UserFactory userFactory) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.userFactory = userFactory;
    }

    public void execute(LoginInputData inputData) {
        if (missingFields(inputData)) {
            presenter.setFailView("Please Fill in All Fields!");
        } else if (!accountExists(inputData)) {
            presenter.setFailView("This User Does Not Exist!");
        } else if (!passwordMatches(inputData)) {
            presenter.setFailView("Incorrect Password!");
        } else {
            presenter.setPassView(new LoginOutputData(userDataAccess.getUser(inputData.getUsername())));
        }
    }

    private boolean accountExists(LoginInputData inputData) {
        return userDataAccess.accountExists(inputData.getUsername());
    }

    private boolean missingFields(LoginInputData inputData) {
        return (inputData.getUsername().isEmpty() || inputData.getPassword().isEmpty());
    }

    private boolean passwordMatches(LoginInputData inputData) {
        return userDataAccess.getUser(inputData.getUsername()).getPassword().equals(inputData.getPassword());
    }
}
