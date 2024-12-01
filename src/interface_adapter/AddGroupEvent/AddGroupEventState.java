package interface_adapter.AddGroupEvent;

public class AddGroupEventState {
    private String groupname;
    private String eventname;
    private String error;

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getEventname() {
        return eventname;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
