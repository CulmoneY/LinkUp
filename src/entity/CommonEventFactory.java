package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating CommonUserEvent objects.
 */
public class CommonEventFactory implements EventFactory {
    @Override
    public Event create(String eventName, LocalDateTime startTime, LocalDateTime endTime, boolean isGroupEvent) {
        if (isGroupEvent) {
            return new CommonUserEvent(eventName, startTime, endTime);
        }
        else {
            return new CommonGroupEvent(eventName, startTime, endTime);
        }

    }
}
