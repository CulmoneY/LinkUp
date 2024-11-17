package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import interface_adapter.GroupChat.GroupChatViewModel;

/**
 * The View for the Group Chat Use Case.
 */
public class GroupChatView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JTextArea messageInputField = new JTextArea(3, 40);
    private final JPanel chatPanel = new JPanel();
    private final JScrollPane chatScrollPane;

    private final String viewName;

    private final GroupChatViewModel groupChatViewModel;
    private final ViewManager viewManager;

    private final JButton userInfo;

    private String currentGroup; // New instance variable to store the current group
    private JPanel groupListPanel; // Updated to instance-level for dynamic updates

    public GroupChatView(GroupChatViewModel groupChatViewModel, ViewManager viewManager) {
        this.groupChatViewModel = groupChatViewModel;
        this.viewManager = viewManager;
        this.viewName = groupChatViewModel.getViewName();

        this.setLayout(new BorderLayout());

        // Left Panel: Group List
        groupListPanel = new JPanel();
        groupListPanel.setLayout(new BoxLayout(groupListPanel, BoxLayout.Y_AXIS));

        // Title panel for "Linkups" with the "+" button inline
        JPanel groupTitlePanel = new JPanel(new BorderLayout());
        JLabel groupListTitle = new JLabel("LinkUp");
        groupTitlePanel.add(groupListTitle, BorderLayout.WEST);
        groupListTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JButton addGroupButton = new JButton("+");
        addGroupButton.setToolTipText("Press to add a new group");
        addGroupButton.setPreferredSize(new Dimension(20, 20));
        // TODO: Add listener for addGroupButton
        groupTitlePanel.setMaximumSize(new Dimension(220, 20));
        groupTitlePanel.add(addGroupButton, BorderLayout.EAST);

        // Add the title panel to the top of the group list panel
        groupListPanel.add(groupTitlePanel); // Add title panel with aligned "+" button

        // Wrap in a scroll pane and add to the main layout
        JScrollPane groupListScrollPane = new JScrollPane(groupListPanel);
        groupListScrollPane.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));
        this.add(groupListScrollPane, BorderLayout.WEST);

        // Top Panel: Group and User Information
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel groupInfo = new JLabel();
        JButton groupButton = new JButton("Group Settings");
        JLabel userInfoLabel = new JLabel("username1, username2, ...");

        JPanel groupInfoPanel = new JPanel();
        groupInfoPanel.setLayout(new BoxLayout(groupInfoPanel, BoxLayout.X_AXIS));
        groupInfoPanel.add(groupButton);
        groupInfoPanel.add(userInfoLabel);

        topPanel.add(groupInfoPanel, BorderLayout.WEST);

        // Initialize userInfo button with placeholder text
        userInfo = new JButton("Signed In As: Loading...");
        userInfo.setActionCommand("switchToUserSettings"); // Add action command for switching views
        userInfo.addActionListener(this); // Use actionPerformed for button logic
        topPanel.add(userInfo, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        // Center Panel: Chat Messages
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatScrollPane = new JScrollPane(chatPanel);
        this.add(chatScrollPane, BorderLayout.CENTER);

        // Bottom Panel: Message Input
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel typemessageLabel = new JLabel("Type a message:");
        typemessageLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        inputPanel.add(typemessageLabel, BorderLayout.WEST);
        inputPanel.add(new JScrollPane(messageInputField), BorderLayout.CENTER);
        JButton sendButton = new JButton("Send");
        sendButton.setActionCommand("sendMessage"); // Add action command for sending messages
        sendButton.addActionListener(this);
        inputPanel.add(sendButton, BorderLayout.EAST);
        this.add(inputPanel, BorderLayout.SOUTH);

        // Initial Group Setup
        refreshGroups();
    }

    // Adds messages to the chat panel
    private void displayMessages() {
        chatPanel.removeAll();
        // TODO: Add logic to display messages for the selected currentGroup
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("sendMessage".equals(command)) {
            // Handle sending a message
            String message = messageInputField.getText();
            if (!message.isEmpty()) {
                // TODO: Implement message sending logic
                messageInputField.setText(""); // Clear the input field
            }
        } else if ("switchToUserSettings".equals(command)) {
            // Handle switching to the UserSettings view
            viewManager.switchToView("userSettings");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        displayMessages();
    }

    public String getViewName() {
        return viewName;
    }

    // Refresh method to update the username on the userInfo button
    public void refresh() {
        String username = viewManager.getUsername(); // Fetch the updated username
        if (username != null && !username.isEmpty()) {
            userInfo.setText("Signed In As: " + username);
        }
    }

    /**
     * Refreshes the group list and updates the currentGroup.
     */
    public void refreshGroups() {
        // Fetch group names from viewManager
        List<String> groupNames = viewManager.getGroupNames();
        System.out.println(groupNames);

        // Clear the existing group list panel
        groupListPanel.removeAll();

        // Add the title panel back to the group list panel
        JPanel groupTitlePanel = new JPanel(new BorderLayout());
        JLabel groupListTitle = new JLabel("LinkUp");
        groupTitlePanel.add(groupListTitle, BorderLayout.WEST);
        groupListTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JButton addGroupButton = new JButton("+");
        addGroupButton.setToolTipText("Press to add a new group");
        addGroupButton.setPreferredSize(new Dimension(20, 20));
        groupTitlePanel.setMaximumSize(new Dimension(220, 20));
        groupTitlePanel.add(addGroupButton, BorderLayout.EAST);
        groupListPanel.add(groupTitlePanel);

        // Add a button for each group name
        if (!groupNames.isEmpty()) {
            for (String groupName : groupNames) {
                JButton groupButton = new JButton(groupName);
                groupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                groupButton.addActionListener(e -> {
                    currentGroup = groupName; // Update currentGroup when clicked
                    System.out.println("Switched to group: " + currentGroup);
                    // TODO: Add logic to refresh chat panel based on selected group
                });
                groupListPanel.add(groupButton);
            }

            // Set the first group as the currentGroup by default
            currentGroup = groupNames.get(0);
            System.out.println("Default group set to: " + currentGroup);
        }

        // Revalidate and repaint the group list panel
        groupListPanel.revalidate();
        groupListPanel.repaint();
    }
}
