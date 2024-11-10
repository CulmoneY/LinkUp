package entity;

import java.util.List;

public interface Group {

    /**
     * Returns the name of the group.
     * @return the name of the group.
     */
    String getName();

    void setName(String name);

    /**
     * Returns the users in the group.
     * @return the users in the group.
     */
    List<User> getUsers();

    void setUsers(List<User> users);

    /**
     * Adds a user to the group.
     * @param user the user to add.
     */
    void addUser(User user);

    /**
     * Removes a user from the group.
     * @param user the user to remove.
     */
    void removeUser(User user);

    /**
     * Returns the group's calendar.
     * @return the group's calendar.
     */
    Calendar getGroupCalendar();

    /**
     * sets the group's calendar.
     * @param groupCalendar is the added groupCalendar
     */
    void setGroupCalendar(Calendar groupCalendar);

    /**
     * Updates the group's calendar.
     */
    void updateGroupCalendar();

    /**
     * Gets the group's messages
     */
    List<Message> getMessages();

    /**
     * Adds a message to the group
     * @param message the message to add
     */
    void addMessage(Message message);

    /**
     * Adds an event to the group's calendar.
     * @param event the event to add.
     */
    void addGroupEvent(Event event);

    /**
     * Removes an event from the group's calendar.
     * @param event the event to remove.
     */
    void removeGroupEvent(Event event);
}