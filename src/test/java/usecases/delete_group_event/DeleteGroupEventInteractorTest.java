package usecases.delete_group_event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeleteGroupEventInteractorTest {

    private DeleteGroupEventDataAccessInterface dataAccess;
    private DeleteGroupEventOutputBoundary outputBoundary;
    private DeleteGroupEventInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        dataAccess = mock(DeleteGroupEventDataAccessInterface.class);
        outputBoundary = mock(DeleteGroupEventOutputBoundary.class);
        interactor = new DeleteGroupEventInteractor(dataAccess, outputBoundary);
    }

    @Test
    void testExecuteSuccess() {
        // Arrange: Input data with valid group event details
        String groupName = "Study Group";
        String eventName = "Weekly Meeting";
        String startTime = "2024-01-01 10:00";
        String endTime = "2024-01-01 12:00";

        DeleteGroupEventInputData inputData = new DeleteGroupEventInputData(groupName, eventName, startTime, endTime);

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: Verify data access was called with correct parameters
        verify(dataAccess).removeGroupEvent(groupName, eventName, startTime, endTime);

        // Capture and verify the output boundary was called with correct output data
        ArgumentCaptor<DeleteGroupEventOutputData> captor = ArgumentCaptor.forClass(DeleteGroupEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        DeleteGroupEventOutputData outputData = captor.getValue();
        assertEquals(eventName, outputData.getEventName(), "The event name in output data should match.");
    }

    @Test
    void testExecuteWithDifferentTimes() {
        // Arrange: Input data with different start and end times
        String groupName = "Book Club";
        String eventName = "Monthly Discussion";
        String startTime = "2024-02-01 14:00";
        String endTime = "2024-02-01 16:00";

        DeleteGroupEventInputData inputData = new DeleteGroupEventInputData(groupName, eventName, startTime, endTime);

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: Verify data access and presenter
        verify(dataAccess).removeGroupEvent(groupName, eventName, startTime, endTime);

        ArgumentCaptor<DeleteGroupEventOutputData> captor = ArgumentCaptor.forClass(DeleteGroupEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        DeleteGroupEventOutputData outputData = captor.getValue();
        assertEquals(eventName, outputData.getEventName(), "The event name in output data should match.");
    }

    @Test
    void testExecuteWithEmptyFields() {
        // Arrange: Input data with empty group name
        String groupName = "";
        String eventName = "Weekly Meeting";
        String startTime = "2024-01-01 10:00";
        String endTime = "2024-01-01 12:00";

        DeleteGroupEventInputData inputData = new DeleteGroupEventInputData(groupName, eventName, startTime, endTime);

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: Ensure data access is still called (no validation implemented in this interactor)
        verify(dataAccess).removeGroupEvent(groupName, eventName, startTime, endTime);

        ArgumentCaptor<DeleteGroupEventOutputData> captor = ArgumentCaptor.forClass(DeleteGroupEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        DeleteGroupEventOutputData outputData = captor.getValue();
        assertEquals(eventName, outputData.getEventName(), "The event name in output data should match.");
    }

    @Test
    void testExecuteWithSpecialCharacters() {
        // Arrange: Input data with special characters in event name
        String groupName = "Study Group";
        String eventName = "Weekly Meeting @#$";
        String startTime = "2024-01-01 10:00";
        String endTime = "2024-01-01 12:00";

        DeleteGroupEventInputData inputData = new DeleteGroupEventInputData(groupName, eventName, startTime, endTime);

        // Act: Execute the interactor
        interactor.execute(inputData);

        // Assert: Verify data access and presenter
        verify(dataAccess).removeGroupEvent(groupName, eventName, startTime, endTime);

        ArgumentCaptor<DeleteGroupEventOutputData> captor = ArgumentCaptor.forClass(DeleteGroupEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        DeleteGroupEventOutputData outputData = captor.getValue();
        assertEquals(eventName, outputData.getEventName(), "The event name in output data should match.");
    }
}

