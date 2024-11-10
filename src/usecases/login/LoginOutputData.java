package usecases.login;
import entity.User;

public class LoginOutputData {
    private final User user;

    public LoginOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
