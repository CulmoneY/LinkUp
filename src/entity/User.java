package entity;

import java.util.List;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    void setName(String name);

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    void setPassword(String password);

    /**
     * Returns the users' friends.
     * @return the users' friends.
     */
    List<User> getFriends();

    void setFriends(List<User> friends);

    /**
     * Returns the users' calendar.
     * @return the users' calendar.
     */
    Calendar getUserCalendar();

    void setUserCalendar(Calendar userCalendar);

    /**
     * Returns the users' language.
     * @return the users' language.
     */
    String getLanguage();

    void setLanguage(String language);
}
