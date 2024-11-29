package usecases.timeslot_selection;

import entity.Event;

import java.util.List;

// MongoDOA implements this interface is
public interface TimeslotSelectionDataAccessInterface {
    public List<Event> getGroupEvents(String groupName);
}
