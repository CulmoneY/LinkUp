package usecases.delete_group_event;

public interface DeleteGroupEventDataAccessInterface {

    void removeGroupEvent(String groupName, String eventName, String startTime, String endTime);

}
