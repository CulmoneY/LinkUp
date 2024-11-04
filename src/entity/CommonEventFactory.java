package entity;

import java.time.LocalDateTime;

/**
 * Factory for creating CommonEvent objects.
 */
public class CommonEventFactory implements EventFactory {

    @Override
    public Event create(String eventName, LocalDateTime time) {
        return new CommonEvent(eventName, time);
    }
}
