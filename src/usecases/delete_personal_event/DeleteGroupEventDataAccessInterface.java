package usecases.delete_personal_event;

public interface DeleteGroupEventDataAccessInterface {
    void removeGroupEvent(String name, String eventName, String startTime, String endTime);
}
