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

import interface_adapter.AddRecommendedEvent.AddRecommendedEventController;
import interface_adapter.AddRecommendedEvent.AddRecommendedEventState;
import interface_adapter.AddRecommendedEvent.AddRecommendedEventViewModel;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberController;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberState;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberViewModel;
import interface_adapter.TimeslotSelection.TimeslotSelectionController;
import entity.Event;
import interface_adapter.TimeslotSelection.TimeslotSelectionState;
import interface_adapter.TimeslotSelection.TimeslotSelectionViewModel;
import interface_adapter.AddGroupMember.*;

public class GroupSettingsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ViewManager viewManager;
    private final JPanel eventsPanel;
    private final JPanel membersPanel;
    private final JPanel addMembersPanel;
    private final JTextField eventNameField;
    private final JTextField eventStartField;
    private final JTextField eventEndField;
    private final JLabel recommendedEventLabel;
    private final JLabel groupNameLabel;
    private final String viewName = "groupSettingsView";
    private Event reccomendedEvent;

    private final TimeslotSelectionViewModel timeslotSelectionViewModel;
    private TimeslotSelectionController timeslotSelectionController;
    
    private final AddGroupMemberViewModel addGroupMemberViewModel;
    private  AddGroupMemberController addGroupMemberController;

    private final RemoveGroupMemberViewModel removeGroupMemberViewModel;
    private RemoveGroupMemberController removeGroupMemberController;


    private final AddRecommendedEventViewModel addRecommendedEventViewModel;
    private AddRecommendedEventController addRecommendedEventController;
    private String currentGroup; // Instance variable to store the current group name

    public GroupSettingsView(ViewManager viewManager, TimeslotSelectionViewModel timeslotSelectionViewModel, AddGroupMemberViewModel addGroupMemberViewModel, 
                             RemoveGroupMemberViewModel removeGroupMemberViewModel, AddRecommendedEventViewModel addRecommendedEventViewModel) {
        this.addGroupMemberViewModel = addGroupMemberViewModel;
        addGroupMemberViewModel.addPropertyChangeListener(this);

        this.removeGroupMemberViewModel = removeGroupMemberViewModel;
        removeGroupMemberViewModel.addPropertyChangeListener(this);
        
        this.viewManager = viewManager;
        this.timeslotSelectionViewModel = timeslotSelectionViewModel;
        this.timeslotSelectionViewModel.addPropertyChangeListener(this);

        this.addRecommendedEventViewModel = addRecommendedEventViewModel;
        addRecommendedEventViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1280, 720));

        // Top Panel: Group Name and Settings
        JPanel topPanel = new JPanel(new BorderLayout());

        // Centered Group Name Label
        groupNameLabel = new JLabel("Group's Settings", SwingConstants.CENTER);
        groupNameLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger and bold font
        topPanel.add(groupNameLabel, BorderLayout.CENTER);

        // Back Button on the Right
        JButton backButton = new JButton("BACK");
        backButton.addActionListener(e -> viewManager.switchToView("groupChatView"));
        topPanel.add(backButton, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        // Left Panel: Upcoming Events
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));
        JScrollPane eventsScrollPane = new JScrollPane(eventsPanel);
        eventsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        eventsScrollPane.setPreferredSize(new Dimension(600, 400));

        JPanel addEventPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Event Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        addEventPanel.add(new JLabel("Name:"), gbc);
        eventNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventNameField, gbc);

        // Event Start Time
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        addEventPanel.add(new JLabel("Start:"), gbc);
        eventStartField = new JTextField(20);
        eventStartField.setToolTipText("Format: YYYY-MM-DD HH:MM");
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        addEventPanel.add(eventStartField, gbc);

        // Event End Time
        gbc.gridx = 0;
        gbc.gridy = 2;
        addEventPanel.add(new JLabel("End:"), gbc);
        eventEndField = new JTextField(20);
        eventEndField.setToolTipText("Format: YYYY-MM-DD HH:MM");
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

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Upcoming Events:"), BorderLayout.NORTH);
        leftPanel.add(eventsScrollPane, BorderLayout.CENTER);
        leftPanel.add(addEventPanel, BorderLayout.SOUTH);

        this.add(leftPanel, BorderLayout.WEST);

        // Right Panel: Recommended Event and Members
        JPanel rightPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Prominent Recommendation Text
        JLabel recommendationTextLabel = new JLabel("We think you should link up on!");
        recommendationTextLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Bold and larger text
        rightPanel.add(recommendationTextLabel, gbc);

        // Recommendation Event Label
        recommendedEventLabel = new JLabel("No recommendation available.");
        gbc.gridy = 1;
        rightPanel.add(recommendedEventLabel, gbc);

        // Add Recommended Event Button
        JButton addRecommendedEventButton = new JButton("Add Recommended Event");
        addRecommendedEventButton.addActionListener(this);
        gbc.gridy = 2;
        rightPanel.add(addRecommendedEventButton, gbc);

        gbc.gridy = 3;
        rightPanel.add(new JLabel("Current Members:"), gbc);

        membersPanel = new JPanel();
        membersPanel.setLayout(new BoxLayout(membersPanel, BoxLayout.Y_AXIS));
        JScrollPane membersScrollPane = new JScrollPane(membersPanel);
        membersScrollPane.setPreferredSize(new Dimension(300, 200));
        gbc.gridy = 4;
        rightPanel.add(membersScrollPane, gbc);

        gbc.gridy = 5;
        rightPanel.add(new JLabel("Add Members:"), gbc);

        addMembersPanel = new JPanel();
        addMembersPanel.setLayout(new BoxLayout(addMembersPanel, BoxLayout.Y_AXIS));
        JScrollPane addMembersScrollPane = new JScrollPane(addMembersPanel);
        addMembersScrollPane.setPreferredSize(new Dimension(300, 200));
        gbc.gridy = 6;
        rightPanel.add(addMembersScrollPane, gbc);

        this.add(rightPanel, BorderLayout.CENTER);

    }

    public void refreshGroupName() {
        // Retrieve current group from the GroupChatView
        currentGroup = ((GroupChatView) viewManager.getView("groupChatView")).getCurrentGroup();
        groupNameLabel.setText(currentGroup + "'s Settings");
    }

    public void refreshReccomendation() {
        System.out.println(currentGroup);
        timeslotSelectionController.execute(currentGroup, viewManager.getUser());
    }

    public void refreshEvents() {
        eventsPanel.removeAll();

        // Retrieve events for the current group
        List<List<String>> groupEvents = viewManager.getGroupEvents(currentGroup);
        // Set a fixed size for each event panel
        Dimension eventPanelSize = new Dimension(580, 100);

        // Create a container with vertical BoxLayout
        JPanel fixedSizeContainer = new JPanel();
        fixedSizeContainer.setLayout(new BoxLayout(fixedSizeContainer, BoxLayout.Y_AXIS));

        for (List<String> event : groupEvents) {
            String eventName = event.get(0);
            String startTime = event.get(1);
            String endTime = event.get(2);

            // Create event panel with a fixed size
            JPanel eventPanel = new JPanel(new BorderLayout());
            eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            eventPanel.setMaximumSize(eventPanelSize); // Set fixed maximum size
            eventPanel.setPreferredSize(eventPanelSize); // Set fixed preferred size

            // Event details label
            JLabel eventLabel = new JLabel("<html><b>" + eventName + "</b><br>Start: " + startTime + "<br>End: " + endTime + "</html>");
            eventPanel.add(eventLabel, BorderLayout.CENTER);

            // Remove button for each event
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
                // TODO: Implement remove logic
            });
            eventPanel.add(removeButton, BorderLayout.EAST);

            // Add event panel to the container
            fixedSizeContainer.add(eventPanel);
        }

        // Ensure the container has the same layout as the main events panel
        eventsPanel.setLayout(new BorderLayout());
        eventsPanel.add(fixedSizeContainer, BorderLayout.NORTH);

        // Revalidate and repaint the panel
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }


    public void refreshGroupMembers() {
        membersPanel.removeAll();
        List<List<String>> groupMembers = viewManager.getGroupMembers(currentGroup);

        for (List<String> member : groupMembers) {
            String memberName = member.get(0);
            String memberLanguage = member.get(1);

            JButton memberButton = new JButton(memberName + " (" + memberLanguage + ")");
            memberButton.addActionListener(e -> {
                //JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
                // TODO: Implement member interaction logic
                removeGroupMemberController.execute(currentGroup, memberName);

            });
            membersPanel.add(memberButton);
        }
        membersPanel.revalidate();
        membersPanel.repaint();
    }

    public void refreshNewMembers() {
        addMembersPanel.removeAll();

        // Retrieve the current group's members
        List<List<String>> groupMembers = viewManager.getGroupMembers(currentGroup);

        // Retrieve the user's friends
        List<List<String>> userFriends = viewManager.getFriends();

        // Filter friends who are not already members of the group
        for (List<String> friend : userFriends) {
            String friendName = friend.get(0);
            String friendLanguage = friend.get(1);

            // Check if the friend is not in the group
            boolean isAlreadyMember = groupMembers.stream()
                    .anyMatch(member -> member.get(0).equals(friendName));

            if (!isAlreadyMember) {
                // Create a button for each friend
                JButton addFriendButton = new JButton(friendName + " (" + friendLanguage + ")");
                addFriendButton.addActionListener(e -> {
                    addGroupMemberController.execute(currentGroup, friendName);
                });
                addMembersPanel.add(addFriendButton);
            }
        }

        addMembersPanel.revalidate();
        addMembersPanel.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("ADD EVENT".equals(command)) {
            JOptionPane.showMessageDialog(this, "NOT IMPLEMENTED", "Warning", JOptionPane.WARNING_MESSAGE);
            // TODO: Implement Add Event logic
        } else if ("Add Recommended Event".equals(command)) {
            addRecommendedEventController.excute(reccomendedEvent, currentGroup);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("timeslotSuccess".equals(evt.getPropertyName())) {
            TimeslotSelectionState timeslotSelectionState = (TimeslotSelectionState) evt.getNewValue();
            Event event = timeslotSelectionState.getEvent();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String eventInfo = "<html><b>" + event.getEventName() + "</b><br>Start: " + event.getStartTime().format(formatter) +
                    "<br>End: " + event.getEndTime().format(formatter) + "</html>";
            recommendedEventLabel.setText(eventInfo);
            reccomendedEvent = event;
        } else if ("addRecommendedSuccess".equals(evt.getPropertyName())) {
            AddRecommendedEventState addRecommendedEventState = (AddRecommendedEventState) evt.getNewValue();
            String eventName = addRecommendedEventState.getEvent();
            refreshReccomendation();
            refreshEvents();
            JOptionPane.showMessageDialog(this, "The LinkUp " + eventName + " Was Successfully Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        }else if ("addGroupMemberSuccess".equals(evt.getPropertyName())) {
            AddGroupMemberState addGroupMemberState = (AddGroupMemberState) evt.getNewValue();
            String username = addGroupMemberState.getUsername();
            String groupname = addGroupMemberState.getGroupname();
            JOptionPane.showMessageDialog(this, "Friend " + username + " was successfully added to " + groupname + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshReccomendation();
            refreshEvents();
            refreshGroupMembers();
            refreshNewMembers();

        }else if ("removeGroupMemberSuccess".equals(evt.getPropertyName())) {
            RemoveGroupMemberState removeGroupMemberState = (RemoveGroupMemberState) evt.getNewValue();
            String username = removeGroupMemberState.getUsername();
            String groupname = removeGroupMemberState.getGroupname();
            JOptionPane.showMessageDialog(this, "Friend " + username + " was successfully removed from " + groupname + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshReccomendation();
            refreshEvents();
            refreshGroupMembers();
            refreshNewMembers();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setTimeslotSelectionController(TimeslotSelectionController timeslotSelectionController) {
        this.timeslotSelectionController = timeslotSelectionController;
    }

    public void setAddRecommendedEventController(AddRecommendedEventController addRecommendedEventController) {
        this.addRecommendedEventController = addRecommendedEventController;
    }
    
    public void setAddGroupMemberController(AddGroupMemberController addGroupMemberController) {
        this.addGroupMemberController = addGroupMemberController;
    }

    public void setRemoveGroupMemberController(RemoveGroupMemberController removeGroupMemberController) {
        this.removeGroupMemberController = removeGroupMemberController;
    }
}
