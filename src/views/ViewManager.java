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
import java.util.Collections;
import java.util.Comparator;

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
        if (this.viewManagerModel.getUser() != null) {
            return this.viewManagerModel.getUser().getName();
        } else {
            return "";
        }
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Event event : calendar.getEvents()) {
            if (event.getEndTime().isBefore(LocalDateTime.now())) {
                continue;
            }
            List<String> eventDetails = new ArrayList<>();
            eventDetails.add(event.getEventName());
            eventDetails.add(event.getStartTime().format(formatter));
            eventDetails.add(event.getEndTime().format(formatter));
            userEvents.add(eventDetails);
        }

        sortEventsByStartTime(userEvents);

        return userEvents;
    }

    private void sortEventsByStartTime(List<List<String>> events) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Use Collections.sort with a custom Comparator
        Collections.sort(events, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> e1, List<String> e2) {
                LocalDateTime startTime1 = LocalDateTime.parse(e1.get(1), formatter);
                LocalDateTime startTime2 = LocalDateTime.parse(e2.get(1), formatter);
                return startTime1.compareTo(startTime2);
            }
        });
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

    public User getUser() {
        return this.viewManagerModel.getUser();
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

    public List<List<String>> getGroupEvents(String groupChat) {
        if (this.viewManagerModel.getUser() == null) {
            return new ArrayList<>();
        }
        Group currGroup = null;
        for (Group group : this.viewManagerModel.getUser().getGroups()) {
            if (group.getName().equals(groupChat)) {
                currGroup = group;
                break;
            }
        }
        List<List<String>> groupEvents = new ArrayList<>();

        // Define a formatter for the desired date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Event event : currGroup.getGroupCalendar().getEvents()) {
            if (event.getEndTime().isBefore(LocalDateTime.now())) {
                continue;
            }
            List<String> eventDetails = new ArrayList<>();
            eventDetails.add(event.getEventName());
            eventDetails.add(event.getStartTime().format(formatter));
            eventDetails.add(event.getEndTime().format(formatter));
            groupEvents.add(eventDetails);
        }

        sortEventsByStartTime(groupEvents);

        return groupEvents;
    }

    public List<List<String>> getGroupMembers(String groupName) {
        if (this.viewManagerModel.getUser() == null) {
            return new ArrayList<>();
        }
        Group currGroup = null;
        for (Group group : this.viewManagerModel.getUser().getGroups()) {
            if (group.getName().equals(groupName)) {
                currGroup = group;
                break;
            }
        }
        List<List<String>> groupMembers = new ArrayList<>();
        for (User member : currGroup.getUsers()) {
            List<String> memberDetails = new ArrayList<>();
            memberDetails.add(member.getName());
            memberDetails.add(member.getLanguage());
            groupMembers.add(memberDetails);
        }
        System.out.println(groupMembers);
        return groupMembers;
    }
}
