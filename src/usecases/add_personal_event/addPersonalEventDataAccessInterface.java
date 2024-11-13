package usecases.add_personal_event;
import entity.Event;

public interface addPersonalEventDataAccessInterface {

    public void addEvent(Event event);

    public void getUser(String username);
}
