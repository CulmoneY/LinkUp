package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating CommonEvent objects.
 */
public class CommonEventFactory implements EventFactory {

    @Override
    public Event create(String eventName, LocalDateTime startTime, LocalDateTime endTime) {
        return new CommonEvent(eventName, startTime, endTime);
    }
}
