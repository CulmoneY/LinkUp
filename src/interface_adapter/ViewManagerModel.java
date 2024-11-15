package interface_adapter;
import entity.User;

import java.beans.PropertyChangeSupport;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {
    private User user;
    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
