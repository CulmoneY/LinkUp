package usecases.login;
import entity.User;

public interface LoginUserDataAccessInterface {
    User getUser(String username);

    boolean accountExists(String username);
}
