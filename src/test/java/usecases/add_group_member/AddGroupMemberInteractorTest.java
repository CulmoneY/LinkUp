package usecases.add_group_member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddGroupMemberInteractorTest {

    private AddGroupMemberOutputBoundary outputPresenter;
    private AddGroupMemberDataAccessInterface dataAccess;
    private AddGroupMemberInteractor interactor;

    @BeforeEach
    void setUp() {
        outputPresenter = mock(AddGroupMemberOutputBoundary.class);
        dataAccess = mock(AddGroupMemberDataAccessInterface.class);
        interactor = new AddGroupMemberInteractor(outputPresenter, dataAccess);
    }

    @Test
    void testExecuteAddGroupMember() {
        // Arrange
        String groupName = "Test Group";
        String userName = "Test User";
        AddGroupMemberInputData inputData = new AddGroupMemberInputData(groupName, userName);

        // Act
        interactor.executeAddGroupMember(inputData);

        // Assert
        verify(dataAccess).addGroupToUser(userName, groupName);
        verify(dataAccess).addUserToGroup(groupName, userName);

        ArgumentCaptor<AddGroupMemberOutputData> captor = ArgumentCaptor.forClass(AddGroupMemberOutputData.class);
        verify(outputPresenter).setPassViewData(captor.capture());

        AddGroupMemberOutputData capturedOutput = captor.getValue();
        assertEquals(groupName, capturedOutput.getGroupname());
        assertEquals(userName, capturedOutput.getUsername());
    }
}
