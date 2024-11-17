package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import controllers.MessageController;
import entity.Message;
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

    private MessageController messageController;

    // Class-level userInfo button to allow access in the refresh method
    private final JButton userInfo;

    public GroupChatView(GroupChatViewModel groupChatViewModel, ViewManager viewManager) {
        this.groupChatViewModel = groupChatViewModel;
        this.viewManager = viewManager;
        this.viewName = groupChatViewModel.getViewName();
        // add property change lisener

        this.setLayout(new BorderLayout());

        // Left Panel: Group List
        JPanel groupListPanel = new JPanel();
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

        // Example groups (add below the title panel)
        JButton group1Button = new JButton("Group 1");
        // TODO: Set button max length to truncate after n chars
        group1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupListPanel.add(group1Button);

        JButton group2Button = new JButton("Group 2");
        group2Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupListPanel.add(group2Button);

        // Wrap in a scroll pane and add to the main layout
        JScrollPane groupListScrollPane = new JScrollPane(groupListPanel);
        groupListScrollPane.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));
        this.add(groupListScrollPane, BorderLayout.WEST);

        // Top Panel: Group and User Information
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel groupInfo = new JLabel();
        JButton groupButton = new JButton("Group 1");
        JLabel userInfoLabel = new JLabel("username1, username2, ...");

        JPanel groupInfoPanel = new JPanel();
        groupInfoPanel.setLayout(new BoxLayout(groupInfoPanel, BoxLayout.X_AXIS));
        groupInfoPanel.add(groupButton);
        groupInfoPanel.add(userInfoLabel);

        topPanel.add(groupInfoPanel, BorderLayout.WEST);
        // groupInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Initialize userInfo button with placeholder text
        userInfo = new JButton("Signed In As: Loading...");
        // TODO: add listener for user info button
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
        sendButton.addActionListener(this);
        inputPanel.add(sendButton, BorderLayout.EAST);
        this.add(inputPanel, BorderLayout.SOUTH);

        // Display initial messages
        displayMessages();
    }

    // Adds messages to the chat panel
    private void displayMessages() {
        chatPanel.removeAll();
        List<Message> messages = groupChatViewModel.getMessages();
        for (Message message : messages) {
            JLabel messageLabel = new JLabel(message.getSender().getName() + ": " + message.getMessage());
            chatPanel.add(messageLabel);
        }
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = messageInputField.getText();
        if (!message.isEmpty()) {
            messageController.execute(messageInputField.getText(), viewManager.getGroup(), viewManager.getUsername());
            messageInputField.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        displayMessages();
    }

    public String getViewName() {
        return viewName;
    }

    public void setMessageController(MessageController messageController) {
        this.messageController = messageController;
    }

    // Refresh method to update the username on the userInfo button
    public void refresh() {
        String username = viewManager.getUsername(); // Fetch the updated username
        if (username != null && !username.isEmpty()) {
            userInfo.setText("Signed In As: " + username);
        }
    }
}
