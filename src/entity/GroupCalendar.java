package entity;

import java.util.List;

public class GroupCalendar implements Calendar {
    private String name;
    private List<Event> events;
    private Group owner;

    public GroupCalendar(String name, List<Event> events, Group owner) {
        this.name = name;
        this.events = events;
        this.owner = owner;
    }

    /**
     * Returns the name of the calendar.
     *
     * @return the name of the calendar.
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the events in the calendar.
     *
     * @return the events in the calendar.
     */
    @Override
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the calendar.
     *
     * @param event the event to add.
     */
    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an event from the calendar.
     *
     * @param event the event to remove.
     */
    @Override
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Returns the owner of the calendar.
     *
     * @return the owner of the calendar.
     */
    @Override
    public Group getOwner() { // TODO: Fix this
        return owner;
    }
}
