package interface_adapter.Login;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The LoginModel class manages the state and behavior of the login view.
 * It handles user inputs, updates the view model state, and provides support for property change notifications.
 */
public class LoginViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Log In";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String PASSWORD_LABEL = "Enter password";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private LoginState state = new LoginState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public final JTextField usernameInputField = new JTextField(15);
    public final JPasswordField passwordInputField = new JPasswordField(15);

    public final JLabel titleLabel = new JLabel(TITLE_LABEL);
    public final JLabel usernameLabel = new JLabel(USERNAME_LABEL);
    public final JLabel passwordLabel = new JLabel(PASSWORD_LABEL);

    /**
     * Gets the current state of the login view model.
     *
     * @return the current LoginState
     */
    public LoginState getState() {
        return state;
    }

    /**
     * Sets the state of the login view model.
     *
     * @param state the new LoginState
     */
    public void setState(LoginState state) {
        this.state = state;
    }

    /**
     * Fires a property change event for the state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Fires a property change event with a specific property name.
     *
     * @param propertyName the name of the property
     * @param oldValue     the old value of the property
     * @param newValue     the new value of the property
     */
    public void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
        support.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Adds a property change listener to this view model.
     *
     * @param listener the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from this view model.
     *
     * @param listener the property change listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}