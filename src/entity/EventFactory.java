package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating events.
 */
public interface EventFactory {
    /**
     * Creates a new Event.
     * @param eventName the new name of the event.
     * @param time the new time of the event.
     * @return the new event
     */
    Event create(String eventName, LocalDateTime time);

}
