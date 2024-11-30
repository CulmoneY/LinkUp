package usecases.add_group_event;
import entity.Event;
import entity.Group;

public interface AddGroupEventDataAccessInterface {

    public void addEvent(Group group, Event event);
}
