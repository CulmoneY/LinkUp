package entity;

import java.util.List;

public class CommonCalendar implements Calendar {
    private String name;
    private List<Event> events;

    public CommonCalendar(String name, List<Event> events) {
        this.name = name;
        this.events = events;
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
}