package usecases.add_recommended_event;

import entity.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddRecommendedEventInteractorTest {

    private AddRecommendedEventDataAccessInterface mockDataAccess;
    private AddRecommendedEventOutputBoundary mockOutputBoundary;
    private AddRecommendedEventInteractor interactor;

    @BeforeEach
    public void setUp() {
        mockDataAccess = mock(AddRecommendedEventDataAccessInterface.class);
        mockOutputBoundary = mock(AddRecommendedEventOutputBoundary.class);
        interactor = new AddRecommendedEventInteractor(mockDataAccess, mockOutputBoundary);
    }

    @Test
    public void testAddRecommendedEvent() {
        // Arrange
        String groupName = "Study Group";
        Event mockEvent = mock(Event.class);
        when(mockEvent.getEventName()).thenReturn("Recommended Event");

        AddRecommendedEventInputData inputData = new AddRecommendedEventInputData(mockEvent, groupName);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockDataAccess, times(1)).addEventToGroup(mockEvent, groupName);

        ArgumentCaptor<AddRecommendedEventOutputData> captor = ArgumentCaptor.forClass(AddRecommendedEventOutputData.class);
        verify(mockOutputBoundary, times(1)).setPassView(captor.capture());

        AddRecommendedEventOutputData capturedData = captor.getValue();
        assertEquals("Recommended Event", capturedData.getEventName(), "Event name should match.");
    }

    @Test
    public void testExecuteCallsAddRecommendedEvent() {
        // Arrange
        AddRecommendedEventInputData inputData = mock(AddRecommendedEventInputData.class);
        Event mockEvent = mock(Event.class);
        when(inputData.getEvent()).thenReturn(mockEvent);
        when(inputData.getGroupName()).thenReturn("Study Group");
        when(mockEvent.getEventName()).thenReturn("Another Event");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockDataAccess, times(1)).addEventToGroup(mockEvent, "Study Group");

        ArgumentCaptor<AddRecommendedEventOutputData> captor = ArgumentCaptor.forClass(AddRecommendedEventOutputData.class);
        verify(mockOutputBoundary).setPassView(captor.capture());

        AddRecommendedEventOutputData outputData = captor.getValue();
        assertEquals("Another Event", outputData.getEventName(), "Event name should match the event added.");
    }
}
