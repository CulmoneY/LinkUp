package entity;
import java.time.LocalDateTime;

public interface Event {
    /**
     * Returns the name of the event.
     * @return the name of the event.
     */
    String getEventName();

    /**
     * Sets the name of the event.
     * @param eventName the new name of the event.
     */
    void setEventName(String eventName);

    /**
     * Returns the Start time of the event.
     * @return the Start time of the event.
     */
    LocalDateTime getStartTime();

    /**
     * Sets the time of the event.
     * @param time the new start time of the event.
     */
    void setStartTime(LocalDateTime time);

    /**
     * Returns the End time of the event.
     * @return the End time of the event.
     */
    LocalDateTime getEndTime();

    /**
     * Sets the End time of the event.
     * @param time the new End time of the event.
     */
    void setEndTime(LocalDateTime time);

    /**
     * Returns a string representation of the CommonEvent.
     *
     * @return a string representation of the event details.
     */
    public String toString();
}
