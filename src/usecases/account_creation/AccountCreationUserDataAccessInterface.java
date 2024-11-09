package usecases.account_creation;

import entity.User;

public interface AccountCreationUserDataAccessInterface {

    boolean accountExists(String username);

    void saveUser(User user);
}
