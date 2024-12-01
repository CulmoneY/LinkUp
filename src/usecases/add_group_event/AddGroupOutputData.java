package usecases.add_group_event;

public class AddGroupOutputData {
    private String eventName;
    private String groupName;

    public AddGroupOutputData(String groupName, String eventName) {
        this.groupName = groupName;
        this.eventName = eventName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getEventName() {
        return eventName;
    }
}
