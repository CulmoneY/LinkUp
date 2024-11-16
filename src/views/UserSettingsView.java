package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserSettingsView extends JPanel implements ActionListener {

    private final String viewName = "userSettings";
    private final ViewManager viewManager;
    private final JPanel eventsPanel;
    private final JPanel friendsPanel;
    private final JComboBox<String> languageDropdown;
    private final JTextField eventNameField;
    private final JTextField eventStartField;
    private final JTextField eventEndField;
    private final JTextField addFriendField;

    public UserSettingsView(ViewManager viewManager) {
        this.viewManager = viewManager;

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1280, 720));

        // Top Panel: Back Button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(e -> viewManager.switchToView("groupChatView")); // Add functionality to switch to GroupChatView
        topPanel.add(backButton);
        this.add(topPanel, BorderLayout.NORTH);

        // Left Panel: Upcoming Events
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        JScrollPane eventsScrollPane = new JScrollPane(eventsPanel);
        eventsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        eventsScrollPane.setPreferredSize(new Dimension(600, 720));
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Upcoming Events:"), BorderLayout.NORTH);
        leftPanel.add(eventsScrollPane, BorderLayout.CENTER);

        // Add Event Section
        JPanel addEventPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Event Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        addEventPanel.add(new JLabel("Name:"), gbc);

        eventNameField = new JTextField(20); // Increased width
        gbc.gridx = 1;
        gbc.gridwidth = 2; // Span across two columns
        addEventPanel.add(eventNameField, gbc);

        // Start Time Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset column span
        addEventPanel.add(new JLabel("Start:"), gbc);

        eventStartField = new JTextField(20); // Increased width
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventStartField, gbc);

        // End Time Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        addEventPanel.add(new JLabel("End:"), gbc);

        eventEndField = new JTextField(20); // Increased width
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventEndField, gbc);

        // Add Event Button
        JButton addEventButton = new JButton("ADD EVENT");
        addEventButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3; // Center the button across all columns
        addEventPanel.add(addEventButton, gbc);

        leftPanel.add(addEventPanel, BorderLayout.SOUTH);

        this.add(leftPanel, BorderLayout.WEST);

        // Right Panel: User Settings and Friends
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Change Language Section
        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton changeLanguageButton = new JButton("CHOOSE LANGUAGE");
        changeLanguageButton.addActionListener(this);
        languageDropdown = new JComboBox<>(new String[]{"English", "Spanish", "French", "Arabic", "Russian"});
        languagePanel.add(changeLanguageButton);
        languagePanel.add(languageDropdown);

        rightPanel.add(languagePanel);

        // Friends Section
        friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.X_AXIS));
        JScrollPane friendsScrollPane = new JScrollPane(friendsPanel);
        friendsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friendsScrollPane.setPreferredSize(new Dimension(600, 150));
        rightPanel.add(new JLabel("Friends:"));
        rightPanel.add(friendsScrollPane);

        // Add Friend Section
        JPanel addFriendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addFriendField = new JTextField(20);
        JButton addFriendButton = new JButton("ADD FRIEND");
        addFriendButton.addActionListener(this);

        addFriendPanel.add(addFriendButton);
        addFriendPanel.add(addFriendField);

        rightPanel.add(addFriendPanel);

        this.add(rightPanel, BorderLayout.CENTER);

        // Initialize UI with existing data
        refreshEvents();
        refreshFriends();
    }

    public void refreshEvents() {
        // Clear existing events
        eventsPanel.removeAll();

        // Fetch user events
        List<List<String>> userEvents = viewManager.getUserEvents();

        // Set fixed size for each event panel
        Dimension eventPanelSize = new Dimension(580, 100); // Fixed size for each panel

        // Create a container to hold the event panels without stretching
        JPanel fixedSizeContainer = new JPanel();
        fixedSizeContainer.setLayout(new BoxLayout(fixedSizeContainer, BoxLayout.Y_AXIS));

        // Display each event
        for (List<String> event : userEvents) {
            String eventName = event.get(0);
            String startTime = event.get(1);
            String endTime = event.get(2);

            JPanel eventPanel = new JPanel(new BorderLayout());
            eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            eventPanel.setMaximumSize(eventPanelSize); // Prevent panels from growing larger
            eventPanel.setPreferredSize(eventPanelSize); // Keep panels at a fixed size
            eventPanel.setMinimumSize(eventPanelSize); // Ensure minimum size is fixed

            // Create a label for the event details
            JLabel eventLabel = new JLabel("<html><div style='padding-left: 5px;'>" + // Add slight left padding
                    "<b>" + eventName + "</b><br>" + // Bold the event name
                    "Start Time: " + startTime + "<br>" +
                    "End Time: " + endTime + "</div></html>");
            eventPanel.add(eventLabel, BorderLayout.CENTER);

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                // TODO: Implement event removal logic
                JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
            });
            eventPanel.add(removeButton, BorderLayout.EAST);

            // Add the event panel to the container
            fixedSizeContainer.add(eventPanel);
        }

        // Add the fixed-size container to the scrollable eventsPanel
        eventsPanel.setLayout(new BorderLayout());
        eventsPanel.add(fixedSizeContainer, BorderLayout.NORTH);

        // Refresh UI
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    public void refreshFriends() {
        // Clear existing friends
        friendsPanel.removeAll();

        // Fetch friends
        List<List<String>> friends = viewManager.getFriends();

        // Display each friend
        for (List<String> friend : friends) {
            String friendName = friend.get(0);
            String friendLanguage = friend.get(1);

            JButton friendButton = new JButton(friendName + " (" + friendLanguage + ")");
            friendButton.addActionListener(e -> {
                // TODO: Implement friend removal logic
                JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
            });

            friendsPanel.add(friendButton);
        }

        // Refresh UI
        friendsPanel.revalidate();
        friendsPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if ("ADD EVENT".equals(source.getText())) {
            // TODO: Implement add event logic
            JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if ("CHOOSE LANGUAGE".equals(source.getText())) {
            // TODO: Implement change language logic
            JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if ("ADD FRIEND".equals(source.getText())) {
            // TODO: Implement add friend logic
            JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
