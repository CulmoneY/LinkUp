package usecases.delete_group_event;

public class DeleteGroupEventOutputData {
    private String eventName;

    public DeleteGroupEventOutputData(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return this.eventName;
    }
}
