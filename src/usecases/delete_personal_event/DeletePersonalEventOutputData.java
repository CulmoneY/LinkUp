package usecases.delete_personal_event;

public class DeletePersonalEventOutputData {
    private String eventName;

    public DeletePersonalEventOutputData(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return this.eventName;
    }
}
