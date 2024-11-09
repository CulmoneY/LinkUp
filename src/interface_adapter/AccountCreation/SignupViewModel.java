package interface_adapter.AccountCreation;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SignupViewModel class manages the state and behavior of the sign-up view.
 * It handles user inputs, updates the view model state, and provides support for property change notifications.
 */
public class SignupViewModel extends ViewModel {

    public static final String CLEAR_BUTTON_LABEL = "Clear";
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public static final String PROGRAM_OF_STUDY_LABEL = "Program of Study";
    public static final String INTEREST1_LABEL = "Interest 1";
    public static final String INTEREST2_LABEL = "Interest 2";
    public static final String INTEREST3_LABEL = "Interest 3";
    public static final String BIO_LABEL = "Bio";
    public static final String AGE_LABEL = "Age";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";

    private SignupState state = new SignupState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public final JTextField usernameInputField = new JTextField(20);
    public final JPasswordField passwordInputField = new JPasswordField(20);
    public final JPasswordField repeatPasswordInputField = new JPasswordField(20);
    public final JTextField programOfStudyInputField = new JTextField(20);
    public final JTextField interest1InputField = new JTextField(20);
    public final JTextField interest2InputField = new JTextField(20);
    public final JTextField interest3InputField = new JTextField(20);
    public final JTextArea bioInputField = new JTextArea(3, 20);
    public final JTextField ageInputField = new JTextField( 20);

    public final JLabel titleLabel = new JLabel(TITLE_LABEL);
    public final JLabel usernameLabel = new JLabel(USERNAME_LABEL);
    public final JLabel passwordLabel = new JLabel(PASSWORD_LABEL);
    public final JLabel repeatPasswordLabel = new JLabel(REPEAT_PASSWORD_LABEL);
    public final JLabel programOfStudyLabel = new JLabel(PROGRAM_OF_STUDY_LABEL);
    public final JLabel interest1Label = new JLabel(INTEREST1_LABEL);
    public final JLabel interest2Label = new JLabel(INTEREST2_LABEL);
    public final JLabel interest3Label = new JLabel(INTEREST3_LABEL);

    /**
     * Gets the current state of the sign-up view model.
     *
     * @return the current SignupState
     */
    public SignupState getState() {
        return state;
    }

    /**
     * Sets the state of the sign-up view model.
     *
     * @param state the new SignupState
     */
    public void setState(SignupState state) {
        this.state = state;
    }


    //for testing
    private String username;
    private String error;

/**
 * Sets the username and fires a property change event.
 *
 * @param username the new username
 */
public void setUsername(String username) {
    String oldUsername = this.username;
    this.username = username;
    firePropertyChanged("username", oldUsername, username);
}
    /**
     * Sets the error message and fires a property change event.
     *
     * @param error the new error message
     */
    public void setError(String error) {
        String oldError = this.error;
        this.error = error;
        firePropertyChanged("error", oldError, error);
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