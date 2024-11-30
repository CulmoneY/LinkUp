package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import interface_adapter.AddFriend.AddFriendController;
import interface_adapter.AddFriend.AddFriendState;
import interface_adapter.AddFriend.AddFriendViewModel;
import interface_adapter.AddPersonalEvent.AddPersonalEventController;
import interface_adapter.AddPersonalEvent.AddPersonalEventState;
import interface_adapter.AddPersonalEvent.AddPersonalEventViewModel;
import interface_adapter.ChangeLanguage.ChangeLanguageController;
import interface_adapter.ChangeLanguage.ChangeLanguageViewModel;
import interface_adapter.DeletePersonalEvent.DeletePersonalEventController;
import interface_adapter.DeletePersonalEvent.DeletePersonalEventViewModel;
import interface_adapter.RemoveFriend.RemoveFriendController;
import interface_adapter.RemoveFriend.RemoveFriendState;
import interface_adapter.RemoveFriend.RemoveFriendViewModel;

public class UserSettingsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "userSettings";
    private final ViewManager viewManager;
    private final JPanel eventsPanel;
    private final JPanel friendsPanel;
    private final JComboBox<String> languageDropdown;
    private final JTextField eventNameField;
    private final JTextField eventStartField;
    private final JTextField eventEndField;
    private final JTextField addFriendField;
    private final AddPersonalEventViewModel addPersonalEventViewModel;
    private AddPersonalEventController addPersonalEventController;
    private final AddFriendViewModel addFriendViewModel;
    private AddFriendController addFriendController;
    private final ChangeLanguageViewModel changeLanguageViewModel;
    private ChangeLanguageController changeLanguageController;
    private final DeletePersonalEventViewModel deletePersonalEventViewModel;
    private DeletePersonalEventController deletePersonalEventController;
    private final RemoveFriendViewModel removeFriendViewModel;
    private RemoveFriendController removeFriendController;

    public UserSettingsView(ViewManager viewManager, AddPersonalEventViewModel addPersonalEventViewModel,
                            AddFriendViewModel addFriendViewModel, ChangeLanguageViewModel changeLanguageViewModel,
                            DeletePersonalEventViewModel deletePersonalEventViewModel, RemoveFriendViewModel removeFriendViewModel) {
        this.viewManager = viewManager;
        this.addPersonalEventViewModel = addPersonalEventViewModel;
        addPersonalEventViewModel.addPropertyChangeListener(this);
        this.addFriendViewModel = addFriendViewModel;
        addFriendViewModel.addPropertyChangeListener(this);
        this.changeLanguageViewModel = changeLanguageViewModel;
        changeLanguageViewModel.addPropertyChangeListener(this);
        this.deletePersonalEventViewModel = deletePersonalEventViewModel;
        deletePersonalEventViewModel.addPropertyChangeListener(this);
        this.removeFriendViewModel = removeFriendViewModel;
        removeFriendViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1280, 720));

        // Top Panel: Back Button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(e -> viewManager.switchToView("groupChatView"));
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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Get Current Time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String currentTime = now.format(formatter);

        // Event Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        addEventPanel.add(new JLabel("Name:"), gbc);

        eventNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventNameField, gbc);

        // Start Time Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        addEventPanel.add(new JLabel("Start:"), gbc);

        eventStartField = new JTextField(20);
        eventStartField.setToolTipText("Format: YYYY-MM-DD HH:MM");
        eventStartField.setText(currentTime); // Set predefined value to the current time
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventStartField, gbc);

        // End Time Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        addEventPanel.add(new JLabel("End:"), gbc);

        eventEndField = new JTextField(20);
        eventEndField.setToolTipText("Format: YYYY-MM-DD HH:MM");
        eventEndField.setText(currentTime); // Set predefined value to the current time
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventEndField, gbc);

        // Add Event Button
        JButton addEventButton = new JButton("ADD EVENT");
        addEventButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
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
        languageDropdown = new JComboBox<>(new String[]{"English", "Arabic", "French", "Spanish", "Italian", "Japanese",
                "Korean", "Russian", "Chinese", "Greek", "Portuguese"});
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
        AtomicBoolean removeTriggered = new AtomicBoolean(false);
        eventsPanel.removeAll();
        List<List<String>> userEvents = viewManager.getUserEvents();
        Dimension eventPanelSize = new Dimension(580, 100);

        JPanel fixedSizeContainer = new JPanel();
        fixedSizeContainer.setLayout(new BoxLayout(fixedSizeContainer, BoxLayout.Y_AXIS));

        for (List<String> event : userEvents) {
            if (removeTriggered.get()) {
                removeTriggered.set(false);
                break;
            }
            String eventName = event.get(0);
            String startTime = event.get(1);
            String endTime = event.get(2);

            JPanel eventPanel = new JPanel(new BorderLayout());
            eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            eventPanel.setMaximumSize(eventPanelSize);
            eventPanel.setPreferredSize(eventPanelSize);

            JLabel eventLabel = new JLabel("<html><div style='padding-left: 5px;'>" +
                    "<b>" + eventName + "</b><br>" +
                    "Start Time: " + startTime + "<br>" +
                    "End Time: " + endTime + "</div></html>");
            eventPanel.add(eventLabel, BorderLayout.CENTER);

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                removeEvent(eventName, startTime, endTime);
                removeTriggered.set(true);
            });
            eventPanel.add(removeButton, BorderLayout.EAST);

            fixedSizeContainer.add(eventPanel);
        }

        eventsPanel.setLayout(new BorderLayout());
        eventsPanel.add(fixedSizeContainer, BorderLayout.NORTH);
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    public void removeEvent(String eventName, String starTime, String endTime) {
        deletePersonalEventController.executeDelete(viewManager.getUser(), eventName, starTime, endTime);
        refreshEvents();
    }

    public void refreshFriends() {
        friendsPanel.removeAll();
        List<List<String>> friends = viewManager.getFriends();

        for (List<String> friend : friends) {
            String friendName = friend.get(0);
            String friendLanguage = friend.get(1);

            JButton friendButton = new JButton(friendName + " (" + friendLanguage + ")");
            friendButton.addActionListener(e -> {
                removeFriendController.execute(viewManager.getUser(), friendName);
            });
            friendsPanel.add(friendButton);
        }

        friendsPanel.revalidate();
        friendsPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if ("ADD EVENT".equals(source.getText())) {
            addPersonalEventController.executeCreate(eventNameField.getText(), eventStartField.getText(), eventEndField.getText(), viewManager.getUser());
        } else if ("CHOOSE LANGUAGE".equals(source.getText())) {
            changeLanguageController.executeChangeLanguage(viewManager.getUser(), (String) languageDropdown.getSelectedItem());
            GroupChatView groupChatView = (GroupChatView) viewManager.getView("groupChatView");
            groupChatView.displayMessages();
            JOptionPane.showMessageDialog(this, "Language changed to " + viewManager.getUser().getLanguage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(viewManager.getUser().getLanguage());
        } else if ("ADD FRIEND".equals(source.getText())) {
            addFriendController.execute(viewManager.getUser(), addFriendField.getText());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("eventSuccess".equals(evt.getPropertyName())) {
            refreshEvents();
        } else if ("eventFailure".equals(evt.getPropertyName())) {
            AddPersonalEventState addPersonalEventState = (AddPersonalEventState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, addPersonalEventState.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("addFriendSuccess".equals(evt.getPropertyName())) {
            refreshFriends();
            AddFriendState addFriendState = (AddFriendState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, "Friend " + addFriendState.getFriendUsername() + " added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if ("addFriendFailure".equals(evt.getPropertyName())) {
            AddFriendState addFriendState = (AddFriendState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, addFriendState.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("removeFriendSuccess".equals(evt.getPropertyName())) {
            RemoveFriendState removeFriendState = (RemoveFriendState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, "Friend " + removeFriendState.getFriendName() + " removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshFriends();
        }
    }

    public void setAddPersonalEventController(AddPersonalEventController addPersonalEventController) {
        this.addPersonalEventController = addPersonalEventController;
    }

    public void setAddFriendController(AddFriendController addFriendController) {
        this.addFriendController = addFriendController;
    }

    public void setChangeLanguageController(ChangeLanguageController changeLanguageController) {
        this.changeLanguageController = changeLanguageController;
    }

    public void setDeletePersonalEventController(DeletePersonalEventController deletePersonalEventController){
        this.deletePersonalEventController = deletePersonalEventController;
    }

    public void setRemoveFriendController(RemoveFriendController removeFriendController) {
        this.removeFriendController = removeFriendController;
    }

    public String getViewName() {
        return viewName;
    }
}