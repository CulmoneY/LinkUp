package entity;

import java.time.LocalDateTime;

public class CommonEvent implements Event {
    private String eventName;
    private LocalDateTime time;

    /**
     * Constructs an Event with the specified name and time.
     * @param eventName the name of the event.
     * @param time the time of the event.
     */
    public CommonEvent(String eventName, LocalDateTime time) {
        this.eventName = eventName;
        this.time = time;
    }

    /**
     * Returns the name of the event.
     * @return the name of the event.
     */
    @Override
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the name of the event.
     * @param newEventName the new name of the event.
     */
    @Override
    public void setEventName(String newEventName) {
        eventName = newEventName;
    }

    /**
     * Returns the time of the event.
     * @return the time of the event.
     */
    @Override
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Sets the time of the event.
     * @param newTime the new time of the event.
     */
    @Override
    public void setTime(LocalDateTime newTime) {
        time = newTime;
    }

}
