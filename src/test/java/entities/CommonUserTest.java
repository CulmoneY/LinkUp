package entities;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class CommonUserTest {

    private CommonUser user;

    @BeforeEach
    public void setUp() {
        // Create a CommonUser instance with test data
        user = new CommonUser("John Doe", "password123", "English");
    }

    @Test
    public void testGetName() {
        // Verify the name of the user
        assertEquals("John Doe", user.getName(), "User name should match.");
    }

    @Test
    public void testSetName() {
        // Set a new name and verify
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName(), "User name should be updated.");
    }

    @Test
    public void testGetPassword() {
        // Verify the password of the user
        assertEquals("password123", user.getPassword(), "User password should match.");
    }

    @Test
    public void testSetPassword() {
        // Set a new password and verify
        user.setPassword("newpassword123");
        assertEquals("newpassword123", user.getPassword(), "User password should be updated.");
    }

    @Test
    public void testGetLanguage() {
        // Verify the language of the user
        assertEquals("English", user.getLanguage(), "User language should match.");
    }

    @Test
    public void testSetLanguage() {
        // Set a new language and verify
        user.setLanguage("French");
        assertEquals("French", user.getLanguage(), "User language should be updated.");
    }

    @Test
    public void testAddFriend() {
        // Add a friend and verify
        User friend = new CommonUser("Alice", "password123", "English");
        user.addFriend(friend);
        assertTrue(user.getFriends().contains(friend), "Friend should be added to the user's friend list.");
    }

    @Test
    public void testRemoveFriend() {
        // Remove a friend and verify
        User friend = new CommonUser("Alice", "password123", "English");
        user.addFriend(friend);
        user.removeFriend(friend);
        assertFalse(user.getFriends().contains(friend), "Friend should be removed from the user's friend list.");
    }

    @Test
    public void testGetFriends() {
        // Verify the list of friends
        User friend = new CommonUser("Alice", "password123", "English");
        user.addFriend(friend);
        List<User> friends = user.getFriends();
        assertEquals(1, friends.size(), "User should have one friend.");
        assertTrue(friends.contains(friend), "Friend should be in the user's friend list.");
    }

    @Test
    public void testAddGroup() {
        // Add a group to the user and verify
        Group group = new CommonGroup("Study Group", new ArrayList<>());
        user.addGroup(group);
        assertTrue(user.getGroups().contains(group), "Group should be added to the user's groups.");
    }

    @Test
    public void testRemoveGroup() {
        // Remove a group from the user and verify
        Group group = new CommonGroup("Study Group", new ArrayList<>());
        user.addGroup(group);
        user.removeGroup(group);
        assertFalse(user.getGroups().contains(group), "Group should be removed from the user's groups.");
    }

    @Test
    public void testGetGroups() {
        // Verify the list of groups
        Group group = new CommonGroup("Study Group", new ArrayList<>());
        user.addGroup(group);
        List<Group> groups = user.getGroups();
        assertEquals(1, groups.size(), "User should be part of one group.");
        assertTrue(groups.contains(group), "Group should be in the user's groups.");
    }

    @Test
    public void testGetUserCalendar() {
        // Verify that the user has a non-null calendar
        Calendar calendar = user.getUserCalendar();
        assertNotNull(calendar, "User's calendar should not be null.");
    }

    @Test
    public void testSetUserCalendar() {
        // Create a new calendar and set it for the user
        Calendar newCalendar = new CommonCalendar("New Calendar", new ArrayList<>());
        user.setUserCalendar(newCalendar);
        assertEquals(newCalendar, user.getUserCalendar(), "User's calendar should be updated.");
    }

    @Test
    public void testSetFriends() {
        // Create a list of friends
        List<User> friends = new ArrayList<>();
        User friend1 = new CommonUser("Alice", "password123", "English");
        User friend2 = new CommonUser("Bob", "password123", "English");
        friends.add(friend1);
        friends.add(friend2);

        // Set the list of friends and verify
        user.setFriends(friends);
        assertEquals(friends, user.getFriends(), "The friends list should be updated correctly.");
    }

    @Test
    public void testSetGroups() {
        // Create a list of groups
        List<Group> groups = new ArrayList<>();
        Group group1 = new CommonGroup("Study Group", new ArrayList<>());
        Group group2 = new CommonGroup("Project Team", new ArrayList<>());
        groups.add(group1);
        groups.add(group2);

        // Set the list of groups for the user
        user.setGroups(groups);

        // Verify that the groups list was updated correctly
        assertEquals(groups, user.getGroups(), "The groups list should be updated correctly.");
        assertTrue(user.getGroups().contains(group1), "Group1 should be in the user's groups.");
        assertTrue(user.getGroups().contains(group2), "Group2 should be in the user's groups.");
        assertEquals(2, user.getGroups().size(), "User should have exactly two groups.");
    }

}
