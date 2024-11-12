package interface_adapter;

import java.beans.PropertyChangeSupport;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {
    private String activeView;
    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }
}
