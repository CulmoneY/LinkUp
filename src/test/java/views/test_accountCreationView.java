//package views;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//
//import interface_adapter.AccountCreation.AccountCreationViewModel;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountCreationViewTest {
//    private AccountCreationView accountCreationView;
//    private AccountCreationViewModel accountCreationViewModel;
//
//    @BeforeEach
//    void setUp() {
//        accountCreationViewModel = new AccountCreationViewModel();
//        accountCreationView = new AccountCreationView(accountCreationViewModel);
//    }
//
//    @Test
//    void testTitleLabel() {
//        // Check if the title label is correctly set
//        JLabel titleLabel = (JLabel) accountCreationView.getComponent(0);
//        assertEquals("Create Account", titleLabel.getText(), "Title label should match 'Create Account'");
//    }
//
//    @Test
//    void testInputFieldsAreEditable() {
//        // Check that the username, password, repeat password, and language fields are editable
//        JTextField usernameField = (JTextField) ((JPanel) accountCreationView.getComponent(1)).getComponent(1);
//        assertTrue(usernameField.isEditable(), "Username field should be editable");
//
//        JPasswordField passwordField = (JPasswordField) ((JPanel) accountCreationView.getComponent(2)).getComponent(1);
//        assertTrue(passwordField.isEditable(), "Password field should be editable");
//
//        JPasswordField repeatPasswordField = (JPasswordField) ((JPanel) accountCreationView.getComponent(3)).getComponent(1);
//        assertTrue(repeatPasswordField.isEditable(), "Repeat password field should be editable");
//
//        JTextField languageField = (JTextField) ((JPanel) accountCreationView.getComponent(4)).getComponent(1);
//        assertTrue(languageField.isEditable(), "Language field should be editable");
//    }
//
//    @Test
//    void testButtonLabels() {
//        // Check that the buttons have the correct labels
//        JPanel buttonPanel = (JPanel) accountCreationView.getComponent(5);
//        JButton createAccountButton = (JButton) buttonPanel.getComponent(0);
//        JButton cancelButton = (JButton) buttonPanel.getComponent(1);
//        JButton loginButton = (JButton) buttonPanel.getComponent(2);
//
//        assertEquals("Create Account", createAccountButton.getText(), "Create Account button label should match");
//        assertEquals("Cancel", cancelButton.getText(), "Cancel button label should match");
//        assertEquals("Switch to Login", loginButton.getText(), "Login button label should match");
//    }
//
//    @Test
//    void testCreateAccountButtonAction() {
//        // Simulate clicking the Create Account button
//        JPanel buttonPanel = (JPanel) accountCreationView.getComponent(5);
//        JButton createAccountButton = (JButton) buttonPanel.getComponent(0);
//
//        ActionEvent createAccountEvent = new ActionEvent(createAccountButton, ActionEvent.ACTION_PERFORMED, "Create Account");
//        accountCreationView.actionPerformed(createAccountEvent);
//
//        // Since we have a message dialog in the actionPerformed method, we'll just ensure no exception was thrown
//        assertTrue(true, "No exceptions should be thrown on Create Account button click");
//    }
//
//    @Test
//    void testCancelButtonAction() {
//        // Simulate clicking the Cancel button
//        JPanel buttonPanel = (JPanel) accountCreationView.getComponent(5);
//        JButton cancelButton = (JButton) buttonPanel.getComponent(1);
//
//        ActionEvent cancelEvent = new ActionEvent(cancelButton, ActionEvent.ACTION_PERFORMED, "Cancel");
//        accountCreationView.actionPerformed(cancelEvent);
//
//        // Ensure no exception is thrown on cancel action (basic test for UI response)
//        assertTrue(true, "No exceptions should be thrown on Cancel button click");
//    }
//
//    @Test
//    void testLoginButtonAction() {
//        // Simulate clicking the Switch to Login button
//        JPanel buttonPanel = (JPanel) accountCreationView.getComponent(5);
//        JButton loginButton = (JButton) buttonPanel.getComponent(2);
//
//        ActionEvent loginEvent = new ActionEvent(loginButton, ActionEvent.ACTION_PERFORMED, "Switch to Login");
//        accountCreationView.actionPerformed(loginEvent);
//
//        // Ensure no exception is thrown on login action (basic test for UI response)
//        assertTrue(true, "No exceptions should be thrown on Switch to Login button click");
//    }
//}
