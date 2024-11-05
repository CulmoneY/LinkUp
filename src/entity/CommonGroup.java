package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonGroup implements Group {
    private String name;
    private List<User> users;
    private Calendar calendar;
    private List<Message> messages;

    public CommonGroup(String name, List<User> users) {
        this.name = name;
        this.users = users;
        CalendarFactory calendarFactory = new CommonCalendarFactory();
        this.calendar = calendarFactory.create(name + "'s Calendar");
        for (User user : users) {
            for (Event event : user.getUserCalendar().getEvents()) {
                this.calendar.addEvent(event);
            }
        }
        this.messages = new ArrayList<Message>();
    }

    /**
     * Returns the name of the group.
     *
     * @return the name of the group.
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the users in the group.
     *
     * @return the users in the group.
     */
    @Override
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public void setUsers(List<User> users) {
        this.users = users;
        CalendarFactory calendarFactory = new CommonCalendarFactory();
        this.calendar = calendarFactory.create(this.name + "'s Calendar");
        for (User user : users) {
            for (Event event : user.getUserCalendar().getEvents()) {
                this.calendar.addEvent(event);
            }
        }
    }

    /**
     * Adds a user to the group.
     *
     * @param user the user to add.
     */
    @Override
    public void addUser(User user) {
        this.users.add(user);
        for (Event event : user.getUserCalendar().getEvents()) {
            this.calendar.addEvent(event);
        }
    }

    /**
     * Removes a user from the group.
     *
     * @param user the user to remove.
     */
    @Override
    public void removeUser(User user) {
        this.users.remove(user);
        for (Event event : user.getUserCalendar().getEvents()) {
            this.calendar.removeEvent(event);
        }
    }

    /**
     * Returns the group's calendar.
     *
     * @return the group's calendar.
     */
    @Override
    public Calendar getGroupCalendar() {
        return this.calendar;
    }

    /**
     * Updates the group's calendar.
     */
    @Override
    public void updateGroupCalendar() {
        CalendarFactory calendarFactory = new CommonCalendarFactory();
        this.calendar = calendarFactory.create(this.name + "'s Calendar");
        for (User user : this.users) {
            for (Event event : user.getUserCalendar().getEvents()) {
                this.calendar.addEvent(event);
            }
        }
    }

    /**
     * Gets the group's messages
     */
    @Override
    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * Adds a message to the group
     *
     * @param message the message to add
     */
    @Override
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * Adds an event to the group's calendar.
     *
     * @param event the event to add.
     */
    @Override
    public void addGroupEvent(Event event) {
        this.calendar.addEvent(event);
    }

    /**
     * Removes an event from the group's calendar.
     *
     * @param event the event to remove.
     */
    @Override
    public void removeGroupEvent(Event event) {
        this.calendar.removeEvent(event);
    }

}
