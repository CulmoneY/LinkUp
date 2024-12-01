package usecases.delete_group_event;

public interface DeleteGroupEventDataAccessInterface {

    public void removeGroupEvent(String groupname, String eventname, String startTime, String endTime);
}
