package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import interface_adapter.Login.LoginViewModel;

/**
 * The View for the Login Use Case.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName; //Login
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);

    private final JButton loginButton;
    private final JButton signUpButton;
    private final ViewManager viewManager;
    private final LoginViewModel loginViewModel;

    public LoginView(LoginViewModel loginViewModel, ViewManager viewManager) {
        this.loginViewModel = loginViewModel;
        this.viewManager = viewManager;
        this.viewName = loginViewModel.getViewName(); // Assume getViewName() returns "loginView"
        loginViewModel.addPropertyChangeListener(this);

        // Set layout and padding for main panel
        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Set preferred size to 1280x720
        this.setPreferredSize(new Dimension(1280, 720));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title label for "LinkUp" with custom font style
        JLabel title = new JLabel("LinkUp");
        title.setFont(new Font("Arial", Font.BOLD, 36));
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
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameInputField.setToolTipText("Enter your username");
        this.add(usernameInputField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordInputField.setToolTipText("Enter your password");
        this.add(passwordInputField, gbc);

        // "No Existing Account Yet?" label
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel noAccountLabel = new JLabel("No Existing Account Yet?");
        noAccountLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        this.add(noAccountLabel, gbc);

        // Buttons panel for Log In and Sign Up
        JPanel buttons = new JPanel(new GridBagLayout());
        GridBagConstraints btnGbc = new GridBagConstraints();
        btnGbc.insets = new Insets(10, 10, 10, 10);

        loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(100, 40));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        btnGbc.gridx = 0;
        btnGbc.gridy = 0;
        buttons.add(loginButton, btnGbc);

        signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(100, 40));
        signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        btnGbc.gridy++;
        buttons.add(signUpButton, btnGbc);

        // Add buttons panel to the main panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(buttons, gbc);

        // Action listeners for buttons
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == loginButton) {
            // Handle login action
            JOptionPane.showMessageDialog(this, "Log In action not implemented yet.");
        } else if (evt.getSource() == signUpButton) {
            // Handle sign-up action
            viewManager.switchToView("accountCreationView");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update based on property changes from the ViewModel, if needed
    }

    public String getViewName() {
        return this.viewName;
    }
}
