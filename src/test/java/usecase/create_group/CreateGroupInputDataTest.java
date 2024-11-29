package usecases.create_group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupInputDataTest {

    private String groupName;
    private List<String> groupMembers;
    private CreateGroupInputData createGroupInputData;

    @BeforeEach
    void setUp() {
        // Prepare test data
        groupName = "Study Group";
        groupMembers = Arrays.asList("user1", "user2", "user3");

        // Initialize the CreateGroupInputData object
        createGroupInputData = new CreateGroupInputData(groupName, groupMembers);
    }

    @Test
    void testGroupNameInitialization() {
        // Test if the group name is correctly initialized
        assertEquals(groupName, createGroupInputData.getGroupName(), "Group name should be initialized correctly.");
    }

    @Test
    void testGroupMembersInitialization() {
        // Test if the group members list is correctly initialized
        List<String> expectedMembers = Arrays.asList("user1", "user2", "user3");
        assertEquals(expectedMembers, createGroupInputData.getGroupMembers(), "Group members should be initialized correctly.");
    }

    @Test
    void testEmptyGroupName() {
        // Test the case where an empty group name is provided
        CreateGroupInputData emptyGroupNameData = new CreateGroupInputData("", groupMembers);
        assertEquals("", emptyGroupNameData.getGroupName(), "Group name should be empty.");
    }

    @Test
    void testEmptyGroupMembers() {
        // Test the case where an empty list of group members is provided
        CreateGroupInputData emptyMembersData = new CreateGroupInputData(groupName, Arrays.asList());
        assertTrue(emptyMembersData.getGroupMembers().isEmpty(), "Group members list should be empty.");
    }

    @Test
    void testSingleMember() {
        // Test the case with only one group member
        CreateGroupInputData singleMemberData = new CreateGroupInputData(groupName, Arrays.asList("user1"));
        List<String> expectedSingleMember = Arrays.asList("user1");
        assertEquals(expectedSingleMember, singleMemberData.getGroupMembers(), "Group members should contain a single member.");
    }
}
