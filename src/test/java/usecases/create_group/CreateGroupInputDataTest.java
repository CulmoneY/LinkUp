package usecases.create_group;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateGroupInputDataTest {

    @Test
    void testConstructorAndGetters() {
        // Setup data
        String groupName = "Test Group";
        List<String> groupMembers = Arrays.asList("user1", "user2");
        User currentUser = new CommonUser("currentUser", "password123", "English");

        // Initialize CreateGroupInputData
        CreateGroupInputData inputData = new CreateGroupInputData(groupName, groupMembers, currentUser);

        // Verify getters
        assertEquals(groupName, inputData.getGroupName(), "Group name should match.");
        assertEquals(groupMembers, inputData.getGroupMembers(), "Group members should match.");
        assertEquals(currentUser, inputData.getCurrent_user(), "Current user should match.");
    }

    @Test
    void testEmptyConstructorArguments() {
        // Initialize CreateGroupInputData with empty group name and members
        CreateGroupInputData inputData = new CreateGroupInputData("", null, null);

        // Verify getters return expected defaults
        assertEquals("", inputData.getGroupName(), "Group name should be empty.");
        assertNull(inputData.getGroupMembers(), "Group members should be null.");
        assertNull(inputData.getCurrent_user(), "Current user should be null.");
    }
}
