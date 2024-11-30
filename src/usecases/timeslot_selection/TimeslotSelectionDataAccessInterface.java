package usecases.timeslot_selection;

import entity.Event;
import entity.Group;

import java.util.List;

// MongoDOA implements this interface is
public interface TimeslotSelectionDataAccessInterface {
    Group getGroup(String groupName);
}
