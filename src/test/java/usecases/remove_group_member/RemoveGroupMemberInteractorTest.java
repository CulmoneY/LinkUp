package usecases.remove_group_member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveGroupMemberInteractorTest {

    private RemoveGroupMemberOutputBoundary mockOutputPresenter;
    private RemoveGroupMemberDataAccessInterface mockDataAccess;
    private RemoveGroupMemberInteractor interactor;

    @BeforeEach
    public void setUp() {
        mockOutputPresenter = mock(RemoveGroupMemberOutputBoundary.class);
        mockDataAccess = mock(RemoveGroupMemberDataAccessInterface.class);
        interactor = new RemoveGroupMemberInteractor(mockOutputPresenter, mockDataAccess);
    }

    @Test
    public void testExecuteRemoveGroupMember() {
        // Arrange
        String groupName = "Study Group";
        String userName = "JohnDoe";
        RemoveGroupMemberInputData inputData = new RemoveGroupMemberInputData(groupName, userName);

        // Act
        interactor.executeRemoveGroupMember(inputData);

        // Assert
        verify(mockDataAccess, times(1)).removeGroupMember(groupName, userName);

        ArgumentCaptor<RemoveGroupMemberOutputData> captor = ArgumentCaptor.forClass(RemoveGroupMemberOutputData.class);
        verify(mockOutputPresenter, times(1)).setPassViewData(captor.capture());

        RemoveGroupMemberOutputData capturedData = captor.getValue();
        assertEquals(groupName, capturedData.getGroupname(), "Group name should match.");
        assertEquals(userName, capturedData.getUsername(), "User name should match.");
    }
}
