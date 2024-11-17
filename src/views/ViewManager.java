package views;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

import java.time.LocalDateTime;
import entity.Calendar;
import entity.Group;
import entity.Event;
import java.util.List;

import entity.User;
import interface_adapter.ViewManagerModel;

/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private final ViewManagerModel viewManagerModel;

    private final Map<String, Object> viewMap = new HashMap<>();

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("activeViews".equals(evt.getPropertyName())) {
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewManagerModel.getViewName());
        }
    }

    public void addView(String name, JPanel view) {
        views.add(view, name);
        viewMap.put(name, view);
    }

    public void switchToView(String name) {
        cardLayout.show(views, name);
    }

    public JPanel getMainPanel() {
        return views;
    }

    public String getUsername() {
        return this.viewManagerModel.getUser().getName();
    }

    public List<String> getGroupNames() {
        if (this.viewManagerModel.getUser() == null) {
            return new ArrayList<>();
        }
        List<Group> groups = this.viewManagerModel.getUser().getGroups();
        List<String> groupNames = new ArrayList<>();
        for (Group group : groups) {
            groupNames.add(group.getName());
        }
        return groupNames;
    }

    public List<List<String>> getUserEvents() {
        if (this.viewManagerModel.getUser() == null) {
            return new ArrayList<>();
        }
        Calendar calendar = this.viewManagerModel.getUser().getUserCalendar();
        List<List<String>> userEvents = new ArrayList<>();

        // Define a formatter for the desired date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"); // e.g., 2024-11-17 10:00 AM

        for (Event event : calendar.getEvents()) {
            // TODO: Add logic to filter events that have already ended in database
            if (event.getEndTime().isBefore(LocalDateTime.now())) {
                continue;
            }
            List<String> eventDetails = new ArrayList<>();
            eventDetails.add(event.getEventName());
            eventDetails.add(event.getStartTime().format(formatter)); // Format start time
            eventDetails.add(event.getEndTime().format(formatter));   // Format end time
            userEvents.add(eventDetails);
        }
        return userEvents;
    }


    public List<List<String>> getFriends() {
        if (this.viewManagerModel.getUser() == null) {
            return new ArrayList<>();
        }
        List<User> friends = this.viewManagerModel.getUser().getFriends();
        List<List<String>> friendDetails = new ArrayList<>();
        for (User friend : friends) {
            List<String> friendInfo = new ArrayList<>();
            friendInfo.add(friend.getName());
            friendInfo.add(friend.getLanguage());
            friendDetails.add(friendInfo);
        }
        return friendDetails;
    }

    public String getLanguage() {
        if (this.viewManagerModel.getUser() == null) {
            return "";
        }
        User user = viewManagerModel.getUser();
        return user.getLanguage();
    }

    public Object getView(String name) {
        return viewMap.get(name);
    }

    public User getUser() {
        return viewManagerModel.getUser();
    }
}
