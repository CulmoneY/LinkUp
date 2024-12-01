package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User{

    private String name;
    private String password;
    private List<User> friends;
    private Calendar userCalendar;
    private String language;
    private List<Group> groups;

    public CommonUser (String name, String password, String language) {
        this.name = name;
        this.password = password;
        this.language = language;
        this.friends = new ArrayList<User>();
        CalendarFactory calendarFactory = new CommonCalendarFactory();
        this.userCalendar = calendarFactory.create(name + "'s Calendar");
        this.groups = new ArrayList<Group>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<User> getFriends() {
        return friends;
    }

    @Override
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public Calendar getUserCalendar() {
        return userCalendar;
    }

    @Override
    public void setUserCalendar(Calendar userCalendar) {
        this.userCalendar = userCalendar;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Adds a friend to the user.
     *
     * @param friend the friend to add.
     */
    @Override
    public void addFriend(User friend) {
        friends.add(friend);
    }

    /**
     * Removes a friend from the user.
     *
     * @param friend the friend to remove.
     */
    @Override
    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    /**
     * Adds a group to the user.
     *
     * @param group the group to add.
     */
    @Override
    public void addGroup(Group group) {
        groups.add(group);
    }

    /**
     * Removes a group from the user.
     *
     * @param group the group to remove.
     */
    @Override
    public void removeGroup(Group group) {
        groups.remove(group);
    }

    /**
     * Sets the groups the user is in.
     *
     * @param groups the groups the user is in.
     */
    @Override
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * Gets the user's groups
     */
    @Override
    public List<Group> getGroups() {
        return groups;
    }


}
