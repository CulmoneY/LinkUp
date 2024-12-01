package usecases.add_personal_event;
import entity.Event;
import entity.User;

public interface AddPersonalEventDataAccessInterface {

    public void addEvent(User user, Event event);

    void addGroupEvent(String groupname, Event event);
}
