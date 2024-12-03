package usecases.modify_group_name;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ModifyGroupNameInteractorTest {

    private ModifyGroupNameInteractor interactor;
    private ModifyGroupNameOutputBoundary mockPresenter;
    private ModifyGroupNameDataAccessInterface mockDataAccess;

    @BeforeEach
    public void setUp() {
        // Mock dependencies
        mockPresenter = mock(ModifyGroupNameOutputBoundary.class);
        mockDataAccess = mock(ModifyGroupNameDataAccessInterface.class);

        // Create the interactor
        interactor = new ModifyGroupNameInteractor(mockPresenter, mockDataAccess);
    }

    @Test
    public void testExecuteModifyGroupName_MissingField() {
        // Arrange: Create input data with an empty new group name
        ModifyGroupNameInputData inputData = new ModifyGroupNameInputData("OldGroup", "");

        // Act: Execute the use case
        interactor.executeModifyGroupName(inputData);

        // Assert: Verify the fail view is set with the correct message
        verify(mockPresenter).setFailViewData("Please Fill in all Fields!");
        verifyNoInteractions(mockDataAccess);
    }

    @Test
    public void testExecuteModifyGroupName_GroupNameTaken() {
        // Arrange: Input data with a valid new group name
        ModifyGroupNameInputData inputData = new ModifyGroupNameInputData("OldGroup", "NewGroup");
        // Mock that the group name already exists
        when(mockDataAccess.groupExist("NewGroup")).thenReturn(true);

        // Act: Execute the use case
        interactor.executeModifyGroupName(inputData);

        // Assert: Verify the fail view is set with the correct message
        verify(mockPresenter).setFailViewData("This group name already exists, Please choose another one!");
        verify(mockDataAccess).groupExist("NewGroup");
        verifyNoMoreInteractions(mockDataAccess);
    }

    @Test
    public void testExecuteModifyGroupName_Success() {
        // Arrange
        ModifyGroupNameInputData inputData = new ModifyGroupNameInputData("OldGroup", "NewGroup");
        when(mockDataAccess.groupExist("NewGroup")).thenReturn(false);

        // Act
        interactor.executeModifyGroupName(inputData);

        // Assert: Capture the argument passed to setPassViewData
        ArgumentCaptor<ModifyGroupNameOutputData> captor = ArgumentCaptor.forClass(ModifyGroupNameOutputData.class);
        verify(mockPresenter).setPassViewData(captor.capture());

        // Verify captured data matches expected values
        ModifyGroupNameOutputData capturedData = captor.getValue();
        assertEquals("OldGroup", capturedData.getOldGroupName(), "Old group name should match.");
        assertEquals("NewGroup", capturedData.getNewGroupName(), "New group name should match.");

        // Verify the data access method was called correctly
        verify(mockDataAccess).modifyGroupName("OldGroup", "NewGroup");
    }

    @Test
    public void testMissingField_MethodCoverage() {
        // Arrange: Input data with and without missing fields
        ModifyGroupNameInputData validData = new ModifyGroupNameInputData("OldGroup", "NewGroup");
        ModifyGroupNameInputData invalidData = new ModifyGroupNameInputData("OldGroup", "");

        // Act & Assert: Call the method directly
        assertFalse(interactor.missingField(validData), "Should return false for valid data.");
        assertTrue(interactor.missingField(invalidData), "Should return true for data with missing fields.");
    }

    @Test
    public void testGroupNameTaken_MethodCoverage() {
        // Arrange: Input data with a group name that exists
        ModifyGroupNameInputData inputData = new ModifyGroupNameInputData("OldGroup", "NewGroup");
        when(mockDataAccess.groupExist("NewGroup")).thenReturn(true);
        when(mockDataAccess.groupExist("AnotherGroup")).thenReturn(false);

        // Act & Assert: Call the method directly
        assertTrue(interactor.groupNameTaken(inputData), "Should return true when the group name exists.");
        inputData = new ModifyGroupNameInputData("OldGroup", "AnotherGroup");
        assertFalse(interactor.groupNameTaken(inputData), "Should return false when the group name does not exist.");
    }
}
