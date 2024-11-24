package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.deepl.api.DeepLException;
import interface_adapter.Message.MessageController;
import entity.Message;
import interface_adapter.GroupChat.GroupChatViewModel;
import interface_adapter.MessageTranslation.MessageTranslationController;
import interface_adapter.MessageTranslation.MessageTranslationState;
import interface_adapter.MessageTranslation.MessageTranslationViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The View for the Group Chat Use Case.
 */
public class GroupChatView extends JPanel implements ActionListener, PropertyChangeListener {
    private final boolean translatemode = true;

    private final JTextArea messageInputField = new JTextArea(1, 40);
    private final JPanel chatPanel = new JPanel();
    private final JScrollPane chatScrollPane;

    private final String viewName;

    private final GroupChatViewModel groupChatViewModel;
    private final MessageTranslationViewModel messageTranslationViewModel;
    private final ViewManager viewManager;

    private MessageController messageController;
    private MessageTranslationController messageTranslationController;

    // Class-level userInfo button to allow access in the refresh method
    private final JButton userInfo;

    private String currentGroup; // New instance variable to store the current group
    private JPanel groupListPanel; // Updated to instance-level for dynamic updates
    private Message translatedMessage;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService messageExecutorService = Executors.newSingleThreadExecutor();
    private ExecutorService messageDisplayService = Executors.newSingleThreadExecutor();
    private volatile boolean listenerRunning = true;
    private List<Message> lastKnownMessages;
    private final int fontSize;
    private JLabel groupNameLabel;


    public GroupChatView(GroupChatViewModel groupChatViewModel, ViewManager viewManager, MessageTranslationViewModel messageTranslationViewModel) {
        this.groupChatViewModel = groupChatViewModel;
        this.viewManager = viewManager;
        this.viewName = groupChatViewModel.getViewName();
        this.messageTranslationViewModel = messageTranslationViewModel;
        messageTranslationViewModel.addPropertyChangeListener(this);

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
        addGroupButton.setActionCommand("addGroup"); // Set action command
        addGroupButton.addActionListener(this); // Delegate handling to actionPerformed

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
        groupNameLabel = new JLabel("Group: ");

        JPanel groupInfoPanel = new JPanel();
        groupInfoPanel.setLayout(new BoxLayout(groupInfoPanel, BoxLayout.X_AXIS));
        groupInfoPanel.add(groupButton);
        groupInfoPanel.add(groupNameLabel);

        topPanel.add(groupInfoPanel, BorderLayout.WEST);

        // Initialize userInfo button with placeholder text
        userInfo = new JButton("Signed In As: Loading...");
        userInfo.setActionCommand("switchToUserSettings"); // Add action command for switching views
        userInfo.addActionListener(this); // Use actionPerformed for button logic
        topPanel.add(userInfo, BorderLayout.EAST);
        this.add(topPanel, BorderLayout.NORTH);

        // Center Panel: Chat Messages
        this.fontSize = 16;
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatScrollPane = new JScrollPane(chatPanel);
        this.add(chatScrollPane, BorderLayout.CENTER);

        // Bottom Panel: Message Input
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel typemessageLabel = new JLabel("Type a message:");
        typemessageLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        inputPanel.add(typemessageLabel, BorderLayout.WEST);
        inputPanel.add(new JScrollPane(messageInputField), BorderLayout.CENTER);
        messageInputField.setLineWrap(false);
        JButton sendButton = new JButton("Send");
        sendButton.setActionCommand("sendMessage"); // Add action command for sending messages
        sendButton.addActionListener(this);
        inputPanel.add(sendButton, BorderLayout.EAST);

        messageInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    evt.consume(); // Prevent the default action of adding a new line
                    sendButton.doClick();
                }
            }
        });

        this.add(inputPanel, BorderLayout.SOUTH);

        // Initial Group Setup
        refreshGroups();

        startDatabaseListener();

    }

    private void initializeMessages() {
        JLabel loadingLabel = new JLabel("<html><span style='font-size:" + fontSize + "px;'><b>Loading messages from server...</b></span></html>");
        chatPanel.add(loadingLabel);
        chatPanel.revalidate();
        chatPanel.repaint();
        messageDisplayService.submit(() -> {
            try {
                displayMessagesHelper();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Adds messages to the chat panel
    public void displayMessages() {
        messageDisplayService.submit(() -> {
            try {
                displayMessagesHelper();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void displayMessagesHelper() {
        List<Message> messages = groupChatViewModel.getMessages(currentGroup);
        chatPanel.removeAll();
        for (Message message : messages) {
            if (translatemode) {
                try {
                    messageTranslationController.execute(message.getMessage(), currentGroup, message.getSender(), viewManager.getLanguage());
                } catch (DeepLException | InterruptedException e) {
                    ;
                }
                message = this.translatedMessage;
            }
            if (message != null) {
                JLabel messageLabel = new JLabel("<html><span style='font-size:" + fontSize + "px;'><b>" + message.getSender().getName() + ":</b> " + message.getMessage() + "</span></html>");
                chatPanel.add(messageLabel);
            }
        }
        chatPanel.revalidate();
        chatPanel.repaint();
    }

    private void startDatabaseListener() {
        executorService.submit(() -> {
            while (listenerRunning) {
                try {
                    List<Message> fetchedMessages = groupChatViewModel.getMessages(currentGroup);
                    if (lastKnownMessages == null) {
                        lastKnownMessages = fetchedMessages;
                    }
                    if (!((Integer) fetchedMessages.size()).equals(lastKnownMessages.size())) {
                        lastKnownMessages = fetchedMessages;
                        SwingUtilities.invokeLater(this::displayMessages);
                    }
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("addGroup".equals(command)) {
            // Handle the "+" button logic here
            JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if ("sendMessage".equals(command)) {
            // Handle sending a message
            String message = messageInputField.getText();
            if (!message.isEmpty()) {
                messageInputField.setText("");
                messageExecutorService.submit(() -> {
                    try {
                        messageController.execute(message, currentGroup, viewManager.getUser(), viewManager.getLanguage());
                        SwingUtilities.invokeLater(() -> {
                            JLabel messageLabel = new JLabel("<html><span style='font-size:" + fontSize + "px;'><b>" + viewManager.getUsername() + ":</b> " + message + "</span></html>");
                            chatPanel.add(messageLabel);
                            chatPanel.revalidate();
                            chatPanel.repaint();
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        } else if ("switchToUserSettings".equals(command)) {
            // Handle switching to the UserSettings view
            viewManager.switchToView("userSettings");
            UserSettingsView userSettingsView = (UserSettingsView) viewManager.getView("userSettings");
            userSettingsView.refreshFriends();
            userSettingsView.refreshEvents();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("translationSuccess".equals(evt.getPropertyName())) {
            MessageTranslationState messageTranslationState = (MessageTranslationState) evt.getNewValue();
            this.translatedMessage = messageTranslationState.getMessage();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setMessageController(MessageController messageController) {
        this.messageController = messageController;
    }

    public void setMessageTranslationController(MessageTranslationController messageTranslationController) {
        this.messageTranslationController = messageTranslationController;
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
                    groupNameLabel.setText("Group: " + currentGroup);
                    messageExecutorService.submit(() -> {
                        displayMessagesHelper();
                    });
                });
                groupListPanel.add(groupButton);
            }

            // Set the first group as the currentGroup by default
            currentGroup = groupNames.get(0);
        }

        // Revalidate and repaint the group list panel
        groupNameLabel.setText("Group: " + currentGroup);
        groupListPanel.revalidate();
        groupListPanel.repaint();

        initializeMessages();

    }
}
