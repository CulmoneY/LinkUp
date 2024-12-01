package usecases.add_group_event;

import entity.Event;

public interface AddGroupEventDataAccessInterface {
    void addGroupEvent(String groupname, Event event);
}
