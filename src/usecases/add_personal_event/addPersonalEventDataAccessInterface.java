package usecases.add_personal_event;
import entity.Event;
import entity.User;

public interface addPersonalEventDataAccessInterface {

    public void addEvent(User user, Event event);
}
