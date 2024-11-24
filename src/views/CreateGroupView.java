//package views;
//
package views;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import entity.User;
import interface_adapter.AccountCreation.AccountCreationController;
import interface_adapter.CreateGroup.CreateGroupController;
import interface_adapter.CreateGroup.CreateGroupState;
import interface_adapter.CreateGroup.CreateGroupViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import java.util.List;



/**
 * The View for the Create Group Use Case.
 */
public class CreateGroupView extends JPanel implements ActionListener, PropertyChangeListener {
    private final JTextField groupNameField = new JTextField(20);
    private final JList<String> userList;
    private final JLabel selectedUsersLabel = new JLabel("Selected Users: ");
    private final JButton createGroupButton;

    private final CreateGroupViewModel createGroupViewModel;
    private final ViewManager viewManager;
    private CreateGroupController createGroupController;
    private final String viewName = "createGroupView";

    public CreateGroupView(CreateGroupViewModel createGroupViewModel, ViewManager viewManager) {
        this.createGroupViewModel = createGroupViewModel;
        this.viewManager = viewManager;


        // Add the view as a property change listener
        createGroupViewModel.addPropertyChangeListener(this);

        // Set layout and padding for the main panel
        this.setLayout(new GridBagLayout());
        this.setOpaque(false); // Transparent background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
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

        // Select users
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel selectUsersLabel = new JLabel("Select Users:");
        formPanel.add(selectUsersLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;


        User current_user = viewManager.getUser();
        DefaultListModel<String> usernames = new DefaultListModel<>();

        if (current_user != null) {
            List<User> userFriends = current_user.getFriends();
            for (User user : userFriends) {
                usernames.addElement(user.getName());
            }
        }


        userList = new JList<>(usernames);
        userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        formPanel.add(scrollPane, gbc);

        // Selected users label
        gbc.gridy++;
        selectedUsersLabel.setPreferredSize(new Dimension(400, 30)); // Fix the size to prevent resizing
        formPanel.add(selectedUsersLabel, gbc);

        // Add a listener to update selected users dynamically
        userList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                List<String> selectedUsers = userList.getSelectedValuesList();
                selectedUsersLabel.setText("Selected Users: " + String.join(", ", selectedUsers)); // Update the label text
            }
        });

        // Create Group button
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        createGroupButton = new JButton("Create Group");
        formPanel.add(createGroupButton, gbc);

        createGroupButton.addActionListener(this);

        // Add form panel to the main panel
        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.anchor = GridBagConstraints.CENTER;
        this.add(formPanel, mainGbc);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == createGroupButton) {
            // Fetch the group name and selected users
            String groupName = groupNameField.getText();
            List<String> selectedUsers = userList.getSelectedValuesList();

            if (groupName.isEmpty()) {
                // Notify ViewModel of an error
                createGroupViewModel.setState(new CreateGroupState());
                createGroupViewModel.firePropertyChanged("error");
                return;
            }
            else if (selectedUsers.isEmpty()) {
                // Notify ViewModel of an error
                createGroupViewModel.setState(new CreateGroupState());
                createGroupViewModel.firePropertyChanged("error");
                return;
            }else {

                createGroupController.execute(groupNameField.getText(),
                        userList.getSelectedValuesList(),
                        viewManager.getUsername());
            }

            // Update the ViewModel with the new state
            createGroupViewModel.setState(new CreateGroupState());
            createGroupViewModel.firePropertyChanged("success");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("error".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Error: Please fill all fields properly.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if ("success".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Group created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getViewName() {
        return this.viewName;
    }

public void setCreateGroupController(CreateGroupController controller) {
    this.createGroupController = controller;
    }
}
