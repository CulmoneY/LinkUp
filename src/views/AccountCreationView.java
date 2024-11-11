package views;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.AccountCreation.AccountCreationController;
import interface_adapter.AccountCreation.AccountCreationViewModel;

/**
 * The View for the Account Creation Use Case.
 */
public class AccountCreationView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "AccountCreationView";
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextField languageInputField = new JTextField(15);

    private final JButton createAccountButton;
    private final JButton cancelButton;
    private final JButton loginButton;

    private final AccountCreationViewModel accountCreationViewModel;
    private AccountCreationController accountCreationController;

    public AccountCreationView(AccountCreationViewModel accountCreationViewModel) {
        this.accountCreationViewModel = accountCreationViewModel;
        accountCreationViewModel.addPropertyChangeListener(this);

        // Title label
        final JLabel title = new JLabel(AccountCreationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(AccountCreationViewModel.USERNAME_LABEL), usernameInputField);

        // Password field
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(AccountCreationViewModel.PASSWORD_LABEL), passwordInputField);

        // Repeat Password field
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(AccountCreationViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        // Language field
        final LabelTextPanel languageInfo = new LabelTextPanel(
                new JLabel(AccountCreationViewModel.LANGUAGE_LABEL), languageInputField);

        // Buttons panel
        final JPanel buttons = new JPanel();
        createAccountButton = new JButton(AccountCreationViewModel.CREATE_ACCOUNT_BUTTON_LABEL);
        cancelButton = new JButton(AccountCreationViewModel.CANCEL_BUTTON_LABEL);
        loginButton = new JButton(AccountCreationViewModel.LOGIN_BUTTON_LABEL);

        buttons.add(createAccountButton);
        buttons.add(cancelButton);
        buttons.add(loginButton);

        // Action listeners
        createAccountButton.addActionListener(this);
        cancelButton.addActionListener(this);
        loginButton.addActionListener(this);

        // Adding components to the main panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(languageInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == createAccountButton) {
            accountCreationController.execute(usernameInputField.getText(), passwordInputField.getText(),
                    repeatPasswordInputField.getText(), languageInputField.getText());
        } else if (evt.getSource() == cancelButton) {
            JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
        } else if (evt.getSource() == loginButton) {
            JOptionPane.showMessageDialog(this, "Switch to Login not implemented yet.");
        } // TODO: Account creation works
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update based on property changes from the ViewModel, if needed
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setAccountCreationController(AccountCreationController controller) {
        this.accountCreationController = controller;
    }

    private class LabelTextPanel extends JPanel {
        public LabelTextPanel(JLabel label, JTextField textField) {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
            this.add(label);
            this.add(textField);
        }

        public LabelTextPanel(JLabel label, JPasswordField passwordField) {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            passwordField.setAlignmentX(Component.RIGHT_ALIGNMENT);
            this.add(label);
            this.add(passwordField);
        }
    }
}
