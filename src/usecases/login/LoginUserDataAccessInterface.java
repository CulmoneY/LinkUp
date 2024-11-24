package usecases.login;
import entity.User;

public interface LoginUserDataAccessInterface {
    User getUser(String username);

    boolean accountExists(String username);

    void removeUserEvent(String username, String eventName, String startTime, String endTime);
}
