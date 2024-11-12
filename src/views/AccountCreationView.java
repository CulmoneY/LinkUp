package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import interface_adapter.AccountCreation.AccountCreationController;
import interface_adapter.AccountCreation.AccountCreationState;
import interface_adapter.AccountCreation.AccountCreationViewModel;

/**
 * The View for the Account Creation Use Case.
 */
public class AccountCreationView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(20);
    private final JTextField languageInputField = new JTextField(20);

    private final JButton createAccountButton;
    private final JButton loginButton;

    private final AccountCreationViewModel accountCreationViewModel;
    private AccountCreationController accountCreationController;
    private final ViewManager viewManager;
    private final String viewName; // Account

    public AccountCreationView(AccountCreationViewModel accountCreationViewModel, ViewManager viewManager) {
        this.accountCreationViewModel = accountCreationViewModel;
        this.viewManager = viewManager;
        accountCreationViewModel.addPropertyChangeListener(this);
        this.viewName = accountCreationViewModel.getViewName();
        // Set layout and padding for main panel
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Set preferred size to 1280x720
        this.setPreferredSize(new Dimension(1280, 720));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title label
        JLabel title = new JLabel("Welcome to LinkUp");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(title, gbc);

        // Username field
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel(accountCreationViewModel.USERNAME_LABEL);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameInputField.setToolTipText("Enter your username");
        this.add(usernameInputField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel(accountCreationViewModel.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordInputField.setToolTipText("Enter your password");
        this.add(passwordInputField, gbc);

        // Repeat Password field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel repeatPasswordLabel = new JLabel(accountCreationViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(repeatPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        repeatPasswordInputField.setToolTipText("Re-enter your password");
        this.add(repeatPasswordInputField, gbc);

        // Language field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel languageLabel = new JLabel(accountCreationViewModel.LANGUAGE_LABEL);
        languageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(languageLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        languageInputField.setToolTipText("Preferred language");
        this.add(languageInputField, gbc);

        // Buttons panel
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        createAccountButton = new JButton(accountCreationViewModel.CREATE_ACCOUNT_BUTTON_LABEL);
        loginButton = new JButton(accountCreationViewModel.LOGIN_BUTTON_LABEL);

        // Styling buttons
        createAccountButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setPreferredSize(new Dimension(150, 40));
        createAccountButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add buttons to the panel
        buttons.add(createAccountButton);
        buttons.add(loginButton);

        // Add buttons panel to the main panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(buttons, gbc);

        // Action listeners
        createAccountButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == createAccountButton) {
            accountCreationController.execute(usernameInputField.getText(), passwordInputField.getText(),
                    repeatPasswordInputField.getText(), languageInputField.getText());
        } else if (evt.getSource() == loginButton) {
            viewManager.switchToView("loginView");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("success".equals(evt.getPropertyName())) {
            viewManager.switchToView("loginView");
        }
        if ("error".equals(evt.getPropertyName())) {
            AccountCreationState accountCreationState = (AccountCreationState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, accountCreationState.getErrorCode(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setAccountCreationController(AccountCreationController controller) {
        this.accountCreationController = controller;
    }
}
