package entity;

import java.time.LocalDateTime;

public class CommonUserEvent implements Event {
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor for CommonUserEvent.
     *
     * @param eventName The name of the event.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public CommonUserEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
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
     * Returns a string representation of the CommonUserEvent.
     *
     * @return a string representation of the event details.
     */
    @Override
    public String toString() {
        return "CommonUserEvent{" +
                "eventName='" + eventName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
