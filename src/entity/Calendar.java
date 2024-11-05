package entity;

import java.util.List;

public interface Calendar {
    /**
     * Returns the name of the calendar.
     * @return the name of the calendar.
     */
    String getName();

    void setName(String name);

    /**
     * Returns the events in the calendar.
     * @return the events in the calendar.
     */
    List<Event> getEvents();

    void setEvents(List<Event> events);

    /**
     * Adds an event to the calendar.
     * @param event the event to add.
     */
    void addEvent(Event event);

    /**
     * Removes an event from the calendar.
     * @param event the event to remove.
     */
    void removeEvent(Event event);
}
