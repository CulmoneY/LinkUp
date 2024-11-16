package usecases.login;
import entity.User;

public class LoginOutputData {
    private final User user;
    private final String username;

    public LoginOutputData(User user, String username) {
        this.user = user;
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

}
