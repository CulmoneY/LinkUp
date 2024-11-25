package views;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import entity.User;
import interface_adapter.CreateGroup.CreateGroupController;
import interface_adapter.CreateGroup.CreateGroupState;
import interface_adapter.CreateGroup.CreateGroupViewModel;

/**
 * The View for the Create Group Use Case.
 */
public class CreateGroupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField groupNameField = new JTextField(20);
    private JList<String> userList; // Declare userList as non-final for dynamic updates
    private JScrollPane scrollPane; // Reference to the scroll pane for replacement
    private final JLabel selectedUsersLabel = new JLabel("Selected Users: ");
    private final JButton createGroupButton;

    private final CreateGroupViewModel createGroupViewModel;
    private final ViewManager viewManager;
    private CreateGroupController createGroupController;
    private final String viewName = "createGroupView";

    private final JPanel formPanel; // Main form panel
    private final GridBagConstraints gbc; // For layout management

    public CreateGroupView(CreateGroupViewModel createGroupViewModel, ViewManager viewManager) {
        this.createGroupViewModel = createGroupViewModel;
        this.viewManager = viewManager;

        // Add the view as a property change listener
        createGroupViewModel.addPropertyChangeListener(this);

        // Set layout and padding for the main panel
        this.setLayout(new BorderLayout()); // Using BorderLayout for easier placement of Back button

        // Top Panel for Back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);
        topPanel.add(backButton);
        this.add(topPanel, BorderLayout.NORTH);

        // Initialize GridBagConstraints
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Outer panel with rounded border
        formPanel = new JPanel(new GridBagLayout()) {
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
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label
        JLabel title = new JLabel("Add a New Group");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(title, gbc);

        // Group name field
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel groupNameLabel = new JLabel("Group Name:");
        formPanel.add(groupNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(groupNameField, gbc);

        // Placeholder for Select Users section
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel selectUsersLabel = new JLabel("Select Users:");
        formPanel.add(selectUsersLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Initialize an empty JList and JScrollPane
        userList = new JList<>(new DefaultListModel<>()); // Empty list initially
        userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollPane = new JScrollPane(userList);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        formPanel.add(scrollPane, gbc);

        // Selected users label
        gbc.gridy++;
        selectedUsersLabel.setPreferredSize(new Dimension(400, 30)); // Fix the size to prevent resizing
        formPanel.add(selectedUsersLabel, gbc);

        // Add a listener to update selected users dynamically
        userList.addListSelectionListener(e -> {
            List<String> selectedUsers = userList.getSelectedValuesList();
            selectedUsersLabel.setText("Selected Users: " + String.join(", ", selectedUsers)); // Update the label text
        });

        // Create Group button
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        createGroupButton = new JButton("Create Group");
        formPanel.add(createGroupButton, gbc);

        createGroupButton.addActionListener(this);

        // Add form panel to the main panel
        this.add(formPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == createGroupButton) {
            // Fetch the group name and selected users
            String groupName = groupNameField.getText();
            List<String> selectedUsers = userList.getSelectedValuesList();

            createGroupController.execute(groupNameField.getText(),
                    userList.getSelectedValuesList(),
                    viewManager.getUser());
        } else if (evt.getActionCommand().equals("Back")) {
            GroupChatView groupChatView = (GroupChatView) viewManager.getView("groupChatView");
            groupChatView.refreshGroups();
            viewManager.switchToView("groupChatView");
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("createFail".equals(evt.getPropertyName())) {
            CreateGroupState createGroupState = (CreateGroupState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, createGroupState.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        if ("createSuccess".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Group created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setCreateGroupController(CreateGroupController controller) {
        this.createGroupController = controller;
    }

    public void refreshFriends() {
        // Fetch the updated list of friends
        User currentUser = viewManager.getUser();
        DefaultListModel<String> usernames = new DefaultListModel<>();
        if (currentUser != null) {
            List<User> userFriends = currentUser.getFriends();
            for (User user : userFriends) {
                usernames.addElement(user.getName());
            }
        }

        // Update the JList with the new list of usernames
        userList.setModel(usernames);

        // Reset the selectedUsersLabel to a clean state
        selectedUsersLabel.setText("Selected Users: ");

        // Revalidate and repaint the formPanel to ensure changes are reflected
        formPanel.revalidate();
        formPanel.repaint();
    }
}
