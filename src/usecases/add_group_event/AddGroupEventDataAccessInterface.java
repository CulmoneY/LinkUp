package usecases.add_group_event;
import entity.Event;
import entity.Group;

public interface AddGroupEventDataAccessInterface {

    public void addEvent(String groupName, Event event);
}
