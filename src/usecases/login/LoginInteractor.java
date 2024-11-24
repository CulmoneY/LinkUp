package usecases.login;

import entity.Calendar;
import entity.Event;
import entity.User;
import entity.UserFactory;
import usecases.account_creation.AccountCreationInputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            User user = userDataAccess.getUser(inputData.getUsername());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Calendar calendar = user.getUserCalendar();
            presenter.setPassView(new LoginOutputData(user, inputData.getUsername()));

            // Clean DataBase
            for (Event event : calendar.getEvents()) {
                if (event.getEndTime().isBefore(LocalDateTime.now())) {
                    userDataAccess.removeUserEvent(user.getName(), event.getEventName(),
                            event.getStartTime().format(formatter), event.getEndTime().format(formatter));
                }
            }
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
