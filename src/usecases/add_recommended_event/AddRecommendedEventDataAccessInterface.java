package usecases.add_recommended_event;

import entity.Group;

public interface AddRecommendedEventDataAccessInterface {
    Group getGroup(String groupName);
}
