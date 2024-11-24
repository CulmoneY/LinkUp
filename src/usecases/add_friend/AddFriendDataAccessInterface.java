package usecases.add_friend;

import entity.User;

public interface AddFriendDataAccessInterface {
    User addFriend(String username, String friendUsername);

    boolean isFriend(String username, String friendUsername);

    boolean accountExists(String username);
}
