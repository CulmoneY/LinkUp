package entity;

import java.time.LocalDateTime;

public class CommonEvent implements Event {
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User creator;

    /**
     * Constructor for CommonEvent.
     *
     * @param eventName The name of the event.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public CommonEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, User creator) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creator = creator;
    }

    /**
     * Returns the name of the event.
     *
     * @return the name of the event.
     */
    @Override
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the name of the event.
     *
     * @param eventName the new name of the event.
     */
    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time of the event.
     */
    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the event.
     *
     * @param time the new start time of the event.
     */
    @Override
    public void setStartTime(LocalDateTime time) {
        this.startTime = time;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time of the event.
     */
    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the event.
     *
     * @param time the new end time of the event.
     */
    @Override
    public void setEndTime(LocalDateTime time) {
        this.endTime = time;
    }

    /**
     * Returns the creator of the event.
     *
     * @return the creator of the event.
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the event.
     *
     * @param creator the creator of the event.
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Returns a string representation of the CommonEvent.
     *
     * @return a string representation of the event details.
     */
    @Override
    public String toString() {
        return "CommonEvent{" +
                "eventName='" + eventName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
