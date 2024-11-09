package interface_adapter;

import entity.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModelManager {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private String activeView;
    private User currentUser;
    private User viewedUser;

    public void setActiveView(String activeView) {
        String oldView = this.activeView;
        this.activeView = activeView;
        pcs.firePropertyChange("activeView", oldView, activeView);
    }

    public String getActiveView() {
        return activeView;
    }

    public void setCurrentUser(User currentUser) {
        User oldUser = this.currentUser;
        this.currentUser = currentUser;
        pcs.firePropertyChange("currentUser", oldUser, currentUser);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setViewedUser(User viewedUser) {
        User oldViewedUser = this.viewedUser;
        this.viewedUser = viewedUser;
        pcs.firePropertyChange("viewedUser", oldViewedUser, viewedUser);
    }

    public User getViewedUser() {
        return viewedUser;
    }

    public void clearViewedUser() {
        User oldViewedUser = this.viewedUser;
        this.viewedUser = null;
        pcs.firePropertyChange("viewedUser", oldViewedUser, null);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        pcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void firePropertyChanged() {
    }
}