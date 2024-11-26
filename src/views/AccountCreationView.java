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
    private final JComboBox<String> languageDropdown = new JComboBox<>();
    private final String[][] languages = {
            {"EN-US", "English (American)"},
            {"AR", "Arabic"},
            {"FR", "French"},
            {"ES", "Spanish"},
            {"IT", "Italian"},
            {"JA", "Japanese"},
            {"KO", "Korean"},
            {"RU", "Russian"},
            {"ZH-HANS", "Chinese (Simplified)"},
            {"EL", "Greek"},
            {"PT-BR", "Portuguese (Brazil)"}
    };

    private final JButton createAccountButton;
    private final JButton loginButton;

    private final AccountCreationViewModel accountCreationViewModel;
    private AccountCreationController accountCreationController;
    private final ViewManager viewManager;
    private final String viewName;

    public AccountCreationView(AccountCreationViewModel accountCreationViewModel, ViewManager viewManager) {
        this.accountCreationViewModel = accountCreationViewModel;
        this.viewManager = viewManager;
        accountCreationViewModel.addPropertyChangeListener(this);
        this.viewName = accountCreationViewModel.getViewName();

        // Set layout and padding for main panel
        this.setLayout(new GridBagLayout());
        this.setOpaque(false); // Make the main panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Outer panel with rounded border
        JPanel formPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 200)); // Semi-transparent white
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded corners
            }
        };
        formPanel.setOpaque(false); // Transparent background
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding inside the form

        // Title label
        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(title, gbc);

        // Username field
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameInputField.setToolTipText("Enter your username");
        formPanel.add(usernameInputField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordInputField.setToolTipText("Enter your password");
        formPanel.add(passwordInputField, gbc);

        // Repeat Password field
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel repeatPasswordLabel = new JLabel("Repeat Password");
        repeatPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(repeatPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        repeatPasswordInputField.setToolTipText("Re-enter your password");
        formPanel.add(repeatPasswordInputField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel languageLabel = new JLabel("Language");
        languageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(languageLabel, gbc);

//        JComboBox<String> languageDropdown = new JComboBox<>();
        languageDropdown.setPreferredSize(new Dimension(255, languageDropdown.getPreferredSize().height));
        for (String[] language : languages) {
            languageDropdown.addItem(language[1]); // Display name
        }

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;

        formPanel.add(languageDropdown, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        createAccountButton = new JButton("Create Account") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fill background with rounded corners
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Set text color and paint text
                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
                g2.drawString(getText(), textX, textY);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                // Ignore to prevent default behavior
            }
        };


        Color buttonBlue = new Color(30, 144, 255); // Blue color
        createAccountButton.setBackground(buttonBlue);
        createAccountButton.setForeground(Color.WHITE); // White text
        createAccountButton.setOpaque(false);
        createAccountButton.setFocusPainted(false); // Remove focus border


        loginButton = new JButton("Switch to Login") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fill background with rounded corners
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Set text color and paint text
                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
                g2.drawString(getText(), textX, textY);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }

            @Override
            public void setContentAreaFilled(boolean b) {
                // Ignore to prevent default behavior
            }
        };

        loginButton.setBackground(buttonBlue);
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(false);
        loginButton.setFocusPainted(false);


        // Add action listeners
        createAccountButton.addActionListener(this);
        loginButton.addActionListener(this);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttons.setOpaque(false); // Make the buttons panel transparent
        buttons.add(createAccountButton);
        buttons.add(loginButton);

        formPanel.add(buttons, gbc);

        // Centering the formPanel on main panel
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.anchor = GridBagConstraints.CENTER;
        this.add(formPanel, mainGbc);
    }

    private String getSelectedLanguageCode() {
        int selectedLanguageIndex = languageDropdown.getSelectedIndex();
        System.out.println(selectedLanguageIndex);
        if (selectedLanguageIndex != -1) {
            return languages[selectedLanguageIndex][0];
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == createAccountButton) {
            accountCreationController.execute(usernameInputField.getText(),
                    new String(passwordInputField.getPassword()),
                    new String(repeatPasswordInputField.getPassword()),
                    getSelectedLanguageCode());
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
            JOptionPane.showMessageDialog(this, accountCreationState.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setAccountCreationController(AccountCreationController controller) {
        this.accountCreationController = controller;
    }

    //easter egg
}
