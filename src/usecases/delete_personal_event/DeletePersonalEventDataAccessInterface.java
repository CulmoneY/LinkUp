package usecases.delete_personal_event;

public interface DeletePersonalEventDataAccessInterface {
    void removeUserEvent(String username, String eventName, String startTime, String endTime);

    public void removeGroupEvent(String groupname, String eventname, String startTime, String endTime);
}
