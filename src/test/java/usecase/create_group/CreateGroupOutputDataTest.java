package usecase.create_group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.create_group.CreateGroupOutputData;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupOutputDataTest {

    private String groupName;
    private List<String> groupMembers;
    private boolean success;
    private CreateGroupOutputData createGroupOutputData;

    @BeforeEach
    void setUp() {
        // Prepare test data
        groupName = "Study Group";
        groupMembers = Arrays.asList("user1", "user2", "user3");
        success = true;

        // Initialize the CreateGroupOutputData object
        createGroupOutputData = new CreateGroupOutputData(groupName, groupMembers, success);
    }

    @Test
    void testGroupNameInitialization() {
        // Test if the group name is correctly initialized
        assertEquals(groupName, createGroupOutputData.getGroupName(), "Group name should be initialized correctly.");
    }

    @Test
    void testGroupMembersInitialization() {
        // Test if the group members list is correctly initialized
        List<String> expectedMembers = Arrays.asList("user1", "user2", "user3");
        assertEquals(expectedMembers, createGroupOutputData.getGroupMembers(), "Group members should be initialized correctly.");
    }

    @Test
    void testSuccessInitialization() {
        // Test if the success status is correctly initialized
        assertTrue(createGroupOutputData.getSuccess(), "Success should be initialized to true.");
    }

    @Test
    void testFailureStatus() {
        // Test the case where the success status is false
        CreateGroupOutputData failureData = new CreateGroupOutputData(groupName, groupMembers, false);
        assertFalse(failureData.getSuccess(), "Success should be false for failure case.");
    }

    @Test
    void testEmptyGroupName() {
        // Test the case where an empty group name is provided
        CreateGroupOutputData emptyGroupNameData = new CreateGroupOutputData("", groupMembers, success);
        assertEquals("", emptyGroupNameData.getGroupName(), "Group name should be empty.");
    }

    @Test
    void testEmptyGroupMembers() {
        // Test the case where an empty list of group members is provided
        CreateGroupOutputData emptyMembersData = new CreateGroupOutputData(groupName, Arrays.asList(), success);
        assertTrue(emptyMembersData.getGroupMembers().isEmpty(), "Group members list should be empty.");
    }
}
