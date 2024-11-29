package entities;

import entity.CommonGroup;
import entity.CommonGroupFactory;
import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonGroupFactoryTest {

    private CommonGroupFactory groupFactory;
    private User user1;
    private User user2;
    private List<User> users;

    @BeforeEach
    void setUp() {
        // Creating mock users (using CommonUser or a mock class)
        user1 = new CommonUser("John", "password123", "English");
        user2 = new CommonUser("Jane", "password456", "English");

        // Creating the list of users to pass to the factory
        users = Arrays.asList(user1, user2);

        // Initializing the factory
        groupFactory = new CommonGroupFactory();
    }

    @Test
    void testCreateGroup() {
        // Creating the group using the factory
        String groupName = "Study Group";
        CommonGroup group = groupFactory.create(groupName, users);

        // Verifying that the group is created properly
        assertNotNull(group, "The group should not be null.");
        assertEquals(groupName, group.getName(), "The group name should match the provided name.");
        assertEquals(users, group.getUsers(), "The users in the group should match the provided list.");
    }

    @Test
    void testCreateGroupWithEmptyUserList() {
        // Creating the group with an empty list of users
        String groupName = "Empty Group";
        List<User> emptyUsers = Arrays.asList();
        CommonGroup group = groupFactory.create(groupName, emptyUsers);

        // Verifying that the group is created and contains no users
        assertNotNull(group, "The group should not be null.");
        assertEquals(groupName, group.getName(), "The group name should match the provided name.");
        assertTrue(group.getUsers().isEmpty(), "The group should have no users.");
    }

    @Test
    void testCreateGroupWithNullUserList() {
        // Creating the group with a null user list (should throw an exception or handle it in the factory)
        String groupName = "Null Group";
        List<User> nullUsers = null;

        // Verifying that the factory handles null input correctly (either by throwing an exception or handling it in the implementation)
        assertThrows(NullPointerException.class, () -> groupFactory.create(groupName, nullUsers), "Creating a group with a null user list should throw an exception.");
    }
}
