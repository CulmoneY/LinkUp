package usecases.create_group;

import entity.CommonGroup;
import entity.Group;
import entity.User;
import entity.GroupFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CreateGroupInteractorTest {

    private CreateGroupDataAccessInterface dataAccess;
    private CreateGroupOutputBoundary outputBoundary;
    private GroupFactory groupFactory;
    private CreateGroupInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        dataAccess = mock(CreateGroupDataAccessInterface.class);
        outputBoundary = mock(CreateGroupOutputBoundary.class);
        groupFactory = mock(GroupFactory.class);

        // Initialize the interactor with mocks
        interactor = new CreateGroupInteractor(dataAccess, outputBoundary, groupFactory);
    }

    @Test
    void testSuccessfulGroupCreation() {
        // Arrange
        User currentUser = mock(User.class);
        List<String> groupMembersNames = List.of("member1", "member2");
        List<User> groupMembers = new ArrayList<>();
        User user1 = mock(User.class);
        User user2 = mock(User.class);
        groupMembers.add(user1);
        groupMembers.add(user2);

        CommonGroup group = mock(CommonGroup.class);

        CreateGroupInputData inputData = new CreateGroupInputData("Test Group", groupMembersNames, currentUser);

        // Mock interactions
        when(dataAccess.groupExist("Test Group")).thenReturn(false); // Ensure group doesn't exist
        when(dataAccess.groupMembersToUsers(groupMembersNames)).thenReturn(groupMembers); // Return mocked users
        when(groupFactory.create("Test Group", groupMembers)).thenReturn(group);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(dataAccess).saveGroup(group);
        verify(dataAccess).addGroupToUser("member1", group);
        verify(dataAccess).addGroupToUser("member2", group);
        verify(dataAccess).addGroupToUser(currentUser.getName(), group);
        verify(outputBoundary).setPassView(any(CreateGroupOutputData.class));
        verify(currentUser).addGroup(group);
    }

@Test
    void testGroupNameAlreadyExists() {
        // Mock input data
        User mockCurrentUser = mock(User.class);
        List<String> groupMembersNames = new ArrayList<>(Arrays.asList("user1", "user2"));
        CreateGroupInputData inputData = new CreateGroupInputData("Existing Group", groupMembersNames, mockCurrentUser);

        // Stub group existence
        when(dataAccess.groupExist("Existing Group")).thenReturn(true);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that fail view is set
        verify(outputBoundary).setFailView("This Group Name Already Exists!");
    }

    @Test
    void testMissingGroupName() {
        // Mock input data
        User mockCurrentUser = mock(User.class);
        List<String> groupMembersNames = new ArrayList<>(Arrays.asList("user1", "user2"));
        CreateGroupInputData inputData = new CreateGroupInputData("", groupMembersNames, mockCurrentUser);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that fail view is set
        verify(outputBoundary).setFailView("You Are Missing a Group Name!");
    }

    @Test
    void testEmptyGroupMembers() {
        // Mock input data
        User mockCurrentUser = mock(User.class);
        List<String> groupMembersNames = new ArrayList<>(); // Empty list
        CreateGroupInputData inputData = new CreateGroupInputData("New Group", groupMembersNames, mockCurrentUser);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that fail view is set
        verify(outputBoundary).setFailView("Please Select At Least One Friend");
    }
}
