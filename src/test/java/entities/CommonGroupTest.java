package entities;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CommonGroupTest {

    private CommonGroup group;
    private User user1;
    private User user2;
    private Event event1;
    private Event event2;

    @BeforeEach
    void setUp() {
        // Create users
        user1 = new CommonUser("Samy Asnoun", "password123", "English");
        user2 = new CommonUser("Yianni Culmone", "password456", "Spanish");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // Create a group with users
        group = new CommonGroup("Test Group", users);

        // Create events for users
        event1 = new CommonGroupEvent("Team Meeting", LocalDateTime.of(2024, 10, 1, 10, 0), LocalDateTime.of(2024, 10, 1, 11, 0));
        event2 = new CommonGroupEvent("Project Discussion", LocalDateTime.of(2024, 10, 2, 14, 0), LocalDateTime.of(2024, 10, 2, 15, 0));

        // Adding events to user's calendars
        user1.getUserCalendar().addEvent(event1);
        user2.getUserCalendar().addEvent(event2);
    }

    @Test
    void testAddEventToGroup() {
        // Add event to the group's calendar
        group.addGroupEvent(event1);

        // Check if event1 is added to the group's calendar
        assertTrue(group.getGroupCalendar().getEvents().contains(event1), "Event 1 should be added to the group's calendar.");
    }

    @Test
    void testAddMultipleEvents() {
        // Add multiple events
        group.addGroupEvent(event1);
        group.addGroupEvent(event2);

        // Verify both events are in the group's calendar
        assertTrue(group.getGroupCalendar().getEvents().contains(event1), "Event 1 should be added.");
        assertTrue(group.getGroupCalendar().getEvents().contains(event2), "Event 2 should be added.");
    }

    @Test
    void testRemoveEventFromGroup() {
        // Add events to group
        group.addGroupEvent(event1);
        group.addGroupEvent(event2);

        // Remove event1 from group
        group.removeGroupEvent(event1);

        // Verify event1 is removed, but event2 remains
        assertFalse(group.getGroupCalendar().getEvents().contains(event1), "Event 1 should be removed.");
        assertTrue(group.getGroupCalendar().getEvents().contains(event2), "Event 2 should remain in the calendar.");
    }

    @Test
    void testUpdateGroupCalendar() {
        // Add events
        group.addGroupEvent(event1);
        group.addGroupEvent(event2);

        // Update the group calendar
        group.updateGroupCalendar();

        // Verify events are added after calendar update
        assertTrue(group.getGroupCalendar().getEvents().contains(event1), "Event 1 should be present after calendar update.");
        assertTrue(group.getGroupCalendar().getEvents().contains(event2), "Event 2 should be present after calendar update.");
    }

    @Test
    void testEventDetails() {
        // Check event details
        assertEquals("Team Meeting", event1.getEventName(), "Event name should match.");
        assertEquals(LocalDateTime.of(2024, 10, 1, 10, 0), event1.getStartTime(), "Start time should match.");
        assertEquals(LocalDateTime.of(2024, 10, 1, 11, 0), event1.getEndTime(), "End time should match.");
    }

    @Test
    public void testSetName() {
        CommonGroup group = new CommonGroup("Initial Name", new ArrayList<>());
        group.setName("New Name");
        assertEquals("New Name", group.getName());
    }

    @Test
    public void testAddUser() {
        User mockUser = mock(User.class);
        Calendar mockCalendar = mock(Calendar.class);
        Event mockEvent = mock(Event.class);

        when(mockUser.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(List.of(mockEvent));

        CommonGroup group = new CommonGroup("Group", new ArrayList<>());
        group.addUser(mockUser);

        assertTrue(group.getUsers().contains(mockUser));
    }

    @Test
    public void testSetUsers() {
        User mockUser = mock(User.class);
        Calendar mockCalendar = mock(Calendar.class);
        Event mockEvent = mock(Event.class);

        when(mockUser.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(List.of(mockEvent));

        CommonGroup group = new CommonGroup("Group", new ArrayList<>());
        group.setUsers(List.of(mockUser));

        assertEquals(1, group.getUsers().size());
    }

    @Test
    public void testRemoveUser() {
        User mockUser = mock(User.class);
        Calendar mockCalendar = mock(Calendar.class);
        Event mockEvent = mock(Event.class);

        when(mockUser.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(List.of(mockEvent));

        CommonGroup group = new CommonGroup("Group", new ArrayList<>(List.of(mockUser)));
        group.removeUser(mockUser);

        assertFalse(group.getUsers().contains(mockUser));
    }

    @Test
    public void testSetGroupCalendar() {
        Calendar mockCalendar = mock(Calendar.class);

        CommonGroup group = new CommonGroup("Group", new ArrayList<>());
        group.setGroupCalendar(mockCalendar);

        assertEquals(mockCalendar, group.getGroupCalendar());
    }

    @Test
    public void testAddMessage() {
        Message mockMessage = mock(Message.class);

        CommonGroup group = new CommonGroup("Group", new ArrayList<>());
        group.addMessage(mockMessage);

        assertTrue(group.getMessages().contains(mockMessage));
    }

}
