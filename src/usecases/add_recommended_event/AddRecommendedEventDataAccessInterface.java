package usecases.add_recommended_event;

import entity.Event;


public interface AddRecommendedEventDataAccessInterface {
   public void addEventToGroup(Event event, String groupName);

}
