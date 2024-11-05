package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating events.
 */
public interface EventFactory {
    /**
     * Creates a new Event.
     * @param eventName the new name of the event.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     * @return the new event
     */
    Event create(String eventName, LocalDateTime startTime, LocalDateTime endTime);

}
