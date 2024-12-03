//package usecases.add_group_event;
//
//import entity.Event;
//import entity.EventFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import usecases.add_group_event.AddGroupEventInputData;
//
//import java.time.LocalDateTime;
//
//import static org.mockito.Mockito.*;
//
//class AddGroupEventInteractorTest {
//    private AddGroupEventDataAccessInterface mockDataAccess;
//    private AddGroupEventOutputBoundary mockOutputBoundary;
//    private EventFactory mockEventFactory;
//    private AddGroupEventInteractor interactor;
//
//    @BeforeEach
//    void setUp() {
//        mockDataAccess = mock(AddGroupEventDataAccessInterface.class);
//        mockOutputBoundary = mock(AddGroupEventOutputBoundary.class);
//        mockEventFactory = mock(EventFactory.class);
//        interactor = new AddGroupEventInteractor(mockDataAccess, mockOutputBoundary, mockEventFactory);
//    }
//
//    @Test
//    void testMissingFields() {
//        // Assuming groupName is the fourth required field.
//        AddGroupEventInputData inputData = new AddGroupEventInputData("", "2023-12-01 12:00", "2023-12-01 13:00", "GroupName");
//        interactor.executeCreate(inputData);
//        verify(mockOutputBoundary).setFailView("Fill in all Fields!");
//    }
//
//    @Test
//    void testInvalidTimeFormat() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData("Meeting", "invalid time", "2023-12-01 13:00", "GroupName");
//        interactor.executeCreate(inputData);
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//    }
//
//    @Test
//    void testInvalidTimeOrder() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData("Meeting", "2023-12-01 14:00", "2023-12-01 13:00", "GroupName");
//        Event mockEvent = mock(Event.class); // Correctly mocking the Event class
//        when(mockEventFactory.create(any(), any(), any(), anyBoolean())).thenReturn(mockEvent);
//        interactor.executeCreate(inputData);
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//    }
//
//    @Test
//    void testValidInput() {
//        // Prepare the mock to return a valid event when the create method is called with any parameters
//        Event mockEvent = mock(Event.class);
//        when(mockEventFactory.create(anyString(), any(LocalDateTime.class), any(LocalDateTime.class), eq(true)))
//                .thenReturn(mockEvent);
//
//        // Make sure that the times provided will pass the validTime check
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2023-12-01 12:00", "2023-12-01 13:00", "GroupName");
//
//        // Execute the method under test
//        interactor.executeCreate(inputData);
//
//        // Verify that the addGroupEvent was indeed called with expected parameters
//        verify(mockDataAccess).addGroupEvent("GroupName", mockEvent);
//
//        // Ensure the setPassView is called with correct parameters
//        verify(mockOutputBoundary).setPassView(any(AddGroupOutputData.class));
//    }
//
//}
//package usecases.add_group_event;
//
//import entity.Event;
//import entity.EventFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.mockito.Mockito.*;
//
//class AddGroupEventInteractorTest {
//
//    private AddGroupEventDataAccessInterface mockDataAccess;
//    private AddGroupEventOutputBoundary mockOutputBoundary;
//    private EventFactory mockEventFactory;
//    private AddGroupEventInteractor interactor;
//
//    @BeforeEach
//    void setUp() {
//        mockDataAccess = mock(AddGroupEventDataAccessInterface.class);
//        mockOutputBoundary = mock(AddGroupEventOutputBoundary.class);
//        mockEventFactory = mock(EventFactory.class);
//        interactor = new AddGroupEventInteractor(mockDataAccess, mockOutputBoundary, mockEventFactory);
//    }
//
//    @Test
//    void testMissingFields() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Fill in all Fields!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeFormatForStart() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "invalid-start-time", "2024-12-01 13:00", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeFormatForEnd() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 12:00", "invalid-end-time", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeOrder() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 14:00", "2024-12-01 13:00", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testValidInput() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName");
//
//        Event mockEvent = mock(Event.class);
//        when(mockEventFactory.create(eq("Meeting"), any(LocalDateTime.class), any(LocalDateTime.class), eq(true)))
//                .thenReturn(mockEvent);
//
//        interactor.executeCreate(inputData);
//
//        verify(mockDataAccess).addGroupEvent("GroupName", mockEvent);
//        verify(mockOutputBoundary).setPassView(any(AddGroupOutputData.class));
//    }
//}

//package usecases.add_group_event;
//
//import entity.Event;
//import entity.EventFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.mockito.Mockito.*;
//
//class AddGroupEventInteractorTest {
//
//    private AddGroupEventDataAccessInterface mockDataAccess;
//    private AddGroupEventOutputBoundary mockOutputBoundary;
//    private EventFactory mockEventFactory;
//    private AddGroupEventInteractor interactor;
//
//    @BeforeEach
//    void setUp() {
//        mockDataAccess = mock(AddGroupEventDataAccessInterface.class);
//        mockOutputBoundary = mock(AddGroupEventOutputBoundary.class);
//        mockEventFactory = mock(EventFactory.class);
//        interactor = new AddGroupEventInteractor(mockDataAccess, mockOutputBoundary, mockEventFactory);
//    }
//
//    @Test
//    void testMissingFields() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Fill in all Fields!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeFormatForStart() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "invalid-start-time", "2024-12-01 13:00", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeFormatForEnd() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 12:00", "invalid-end-time", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeOrder() {
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 14:00", "2024-12-01 13:00", "GroupName");
//
//        interactor.executeCreate(inputData);
//
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testValidInput() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName");
//
//        Event mockEvent = mock(Event.class);
//        LocalDateTime start = LocalDateTime.of(2024, 12, 1, 12, 0);
//        LocalDateTime end = LocalDateTime.of(2024, 12, 1, 13, 0);
//
//        when(mockEventFactory.create("Meeting", start, end, true)).thenReturn(mockEvent);
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockEventFactory).create("Meeting", start, end, true); // Verify method call
//        verify(mockDataAccess).addGroupEvent("GroupName", mockEvent); // Ensure event is added
//        verify(mockOutputBoundary).setPassView(any(AddGroupOutputData.class)); // Verify success message
//    }
//
//}

//package usecases.add_group_event;
//
//import entity.Event;
//import entity.EventFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class AddGroupEventInteractorTest {
//
//    private AddGroupEventDataAccessInterface mockDataAccess;
//    private AddGroupEventOutputBoundary mockOutputBoundary;
//    private EventFactory mockEventFactory;
//    private AddGroupEventInteractor interactor;
//
//    @BeforeEach
//    void setUp() {
//        mockDataAccess = mock(AddGroupEventDataAccessInterface.class);
//        mockOutputBoundary = mock(AddGroupEventOutputBoundary.class);
//        mockEventFactory = mock(EventFactory.class);
//        interactor = new AddGroupEventInteractor(mockDataAccess, mockOutputBoundary, mockEventFactory);
//    }
//
//    @Test
//    void testValidInput() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName"
//        );
//        Event mockEvent = mock(Event.class);
//        when(mockEventFactory.create(
//                "Meeting",
//                LocalDateTime.of(2024, 12, 1, 12, 0),
//                LocalDateTime.of(2024, 12, 1, 13, 0),
//                true
//        )).thenReturn(mockEvent);
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockEventFactory).create(
//                "Meeting",
//                LocalDateTime.of(2024, 12, 1, 12, 0),
//                LocalDateTime.of(2024, 12, 1, 13, 0),
//                true
//        );
//        verify(mockDataAccess).addGroupEvent("GroupName", mockEvent);
//
//        ArgumentCaptor<AddGroupOutputData> outputCaptor = ArgumentCaptor.forClass(AddGroupOutputData.class);
//        verify(mockOutputBoundary).setPassView(outputCaptor.capture());
//        AddGroupOutputData outputData = outputCaptor.getValue();
//        assertEquals("GroupName", outputData.getGroupName());
//        assertEquals("Meeting", outputData.getEventName());
//    }
//
//    @Test
//    void testMissingFields() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName"
//        );
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockOutputBoundary).setFailView("Fill in all Fields!");
//        verifyNoInteractions(mockEventFactory);
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testInvalidTimeFormat() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "InvalidDate", "2024-12-01 13:00", "GroupName"
//        );
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockEventFactory);
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testStartTimeAfterEndTime() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 14:00", "2024-12-01 13:00", "GroupName"
//        );
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockEventFactory);
//        verifyNoInteractions(mockDataAccess);
//    }
//}

//
//package usecases.add_group_event;
//
//import entity.Event;
//import entity.EventFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class AddGroupEventInteractorTest {
//
//    private AddGroupEventDataAccessInterface mockDataAccess;
//    private AddGroupEventOutputBoundary mockOutputBoundary;
//    private EventFactory mockEventFactory;
//    private AddGroupEventInteractor interactor;
//
//    @BeforeEach
//    void setUp() {
//        mockDataAccess = mock(AddGroupEventDataAccessInterface.class);
//        mockOutputBoundary = mock(AddGroupEventOutputBoundary.class);
//        mockEventFactory = mock(EventFactory.class);
//        interactor = new AddGroupEventInteractor(mockDataAccess, mockOutputBoundary, mockEventFactory);
//    }

//    @Test
//    void testValidInput() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName"
//        );
//        Event mockEvent = mock(Event.class);
//        when(mockEventFactory.create(
//                "Meeting",
//                LocalDateTime.parse("2024-12-01 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
//                LocalDateTime.parse("2024-12-01 13:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
//                true
//        )).thenReturn(mockEvent);
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockEventFactory).create(
//                "Meeting",
//                LocalDateTime.of(2024, 12, 1, 12, 0),
//                LocalDateTime.of(2024, 12, 1, 13, 0),
//                true
//        );
//        verify(mockDataAccess).addGroupEvent("GroupName", mockEvent);
//
//        ArgumentCaptor<AddGroupOutputData> outputCaptor = ArgumentCaptor.forClass(AddGroupOutputData.class);
//        verify(mockOutputBoundary).setPassView(outputCaptor.capture());
//        AddGroupOutputData outputData = outputCaptor.getValue();
//        assertEquals("GroupName", outputData.getGroupName());
//        assertEquals("Meeting", outputData.getEventName());
//    }

//    @Test
//    void testMissingFields() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "", "2024-12-01 12:00", "2024-12-01 13:00", "GroupName"
//        );
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockOutputBoundary).setFailView("Fill in all Fields!");
//        verifyNoInteractions(mockEventFactory);
//        verifyNoInteractions(mockDataAccess);
//    }

//    @Test
//    void testInvalidTimeFormat() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "InvalidDate", "2024-12-01 13:00", "GroupName"
//        );
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockEventFactory);
//        verifyNoInteractions(mockDataAccess);
//    }
//
//    @Test
//    void testStartTimeAfterEndTime() {
//        // Arrange
//        AddGroupEventInputData inputData = new AddGroupEventInputData(
//                "Meeting", "2024-12-01 14:00", "2024-12-01 13:00", "GroupName"
//        );
//
//        // Act
//        interactor.executeCreate(inputData);
//
//        // Assert
//        verify(mockOutputBoundary).setFailView("Invalid Time Format!");
//        verifyNoInteractions(mockEventFactory);
//        verifyNoInteractions(mockDataAccess);
//    }
//}


package usecases.add_group_event;

import entity.Event;
import entity.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddGroupEventInteractorTest {

    private AddGroupEventDataAccessInterface dataAccess;
    private AddGroupEventOutputBoundary outputBoundary;
    private EventFactory eventFactory;
    private AddGroupEventInteractor interactor;

    // Define the same formatter as in the interactor
    private static final DateTimeFormatter TEST_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @BeforeEach
    void setUp() {
        // Mock dependencies
        dataAccess = mock(AddGroupEventDataAccessInterface.class);
        outputBoundary = mock(AddGroupEventOutputBoundary.class);
        eventFactory = mock(EventFactory.class);

        // Initialize the interactor with mocked dependencies
        interactor = new AddGroupEventInteractor(dataAccess, outputBoundary, eventFactory);
    }
//
//    @Test
//    void testExecuteCreate_Success() {
//        // Arrange: Valid input data
//        String groupName = "Study Group";
//        String eventName = "Weekly Meeting";
//        String startTime = "2024-01-01 10:00";
//        String endTime = "2024-01-01 12:00";
//
//        AddGroupEventInputData inputData = new AddGroupEventInputData(groupName, eventName, startTime, endTime);
//
//        // Mock the event factory to return a new event
//        Event mockEvent = mock(Event.class);
//        when(eventFactory.create(eventName, LocalDateTime.parse(startTime, TEST_FORMATTER),
//                LocalDateTime.parse(endTime, TEST_FORMATTER), true)).thenReturn(mockEvent);
//
//        // Act: Execute the interactor
//        interactor.executeCreate(inputData);
//
//        // Assert: Verify the data access method was called with the correct parameters
//        verify(dataAccess).addGroupEvent(groupName, mockEvent);
//
//        // Capture and verify the output boundary was called with the correct output data
//        ArgumentCaptor<AddGroupOutputData> captor = ArgumentCaptor.forClass(AddGroupOutputData.class);
//        verify(outputBoundary).setPassView(captor.capture());
//        AddGroupOutputData outputData = captor.getValue();
//        assertEquals(groupName, outputData.getGroupName(), "Group name should match in the output data.");
//        assertEquals(eventName, outputData.getEventName(), "Event name should match in the output data.");
//    }

    @Test
    void testExecuteCreate_Success() {
        // Arrange: Valid input data
        String groupName = "Study Group";
        String eventName = "Weekly Meeting";
        String startTime = "2024-01-01 10:00";
        String endTime = "2024-01-01 12:00";

        AddGroupEventInputData inputData = new AddGroupEventInputData(groupName, eventName, startTime, endTime);

        // Mock the event factory to return a new event
        Event mockEvent = mock(Event.class);
        when(mockEvent.getEventName()).thenReturn(eventName); // Ensure eventName matches expected value
        when(eventFactory.create(eventName, LocalDateTime.parse(startTime, TEST_FORMATTER),
                LocalDateTime.parse(endTime, TEST_FORMATTER), true)).thenReturn(mockEvent);

        // Act: Execute the interactor
        interactor.executeCreate(inputData);

        // Assert: Verify the data access method was called with the correct parameters
        verify(dataAccess).addGroupEvent(groupName, mockEvent);

        // Capture and verify the output boundary was called with the correct output data
        ArgumentCaptor<AddGroupOutputData> captor = ArgumentCaptor.forClass(AddGroupOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        AddGroupOutputData outputData = captor.getValue();
        assertEquals(groupName, outputData.getGroupName(), "Group name should match in the output data.");
        assertEquals(eventName, outputData.getEventName(), "Event name should match in the output data.");
    }


    @Test
    void testExecuteCreate_MissingFields() {
        // Arrange: Input data with missing fields
        AddGroupEventInputData inputData = new AddGroupEventInputData("Study Group", "", "2024-01-01 10:00", "2024-01-01 12:00");

        // Act: Execute the interactor
        interactor.executeCreate(inputData);

        // Assert: Verify output boundary receives failure message
        verify(outputBoundary).setFailView("Fill in all Fields!");
        verifyNoInteractions(dataAccess); // Data access should not be called
    }

    @Test
    void testExecuteCreate_InvalidTimeFormat() {
        // Arrange: Input data with invalid time format
        AddGroupEventInputData inputData = new AddGroupEventInputData("Study Group", "Weekly Meeting", "InvalidTime", "2024-01-01 12:00");

        // Act: Execute the interactor
        interactor.executeCreate(inputData);

        // Assert: Verify output boundary receives failure message
        verify(outputBoundary).setFailView("Invalid Time Format!");
        verifyNoInteractions(dataAccess); // Data access should not be called
    }

    @Test
    void testExecuteCreate_StartTimeAfterEndTime() {
        // Arrange: Input data with start time after end time
        AddGroupEventInputData inputData = new AddGroupEventInputData("Study Group", "Weekly Meeting", "2024-01-01 14:00", "2024-01-01 12:00");

        // Act: Execute the interactor
        interactor.executeCreate(inputData);

        // Assert: Verify output boundary receives failure message
        verify(outputBoundary).setFailView("Invalid Time Format!");
        verifyNoInteractions(dataAccess); // Data access should not be called
    }

    @Test
    void testParseDateTime_Valid() {
        // Act: Call the private method parseDateTime via reflection
        LocalDateTime parsedDateTime = invokePrivateParseDateTime("2024-01-01 10:00");

        // Assert: Verify the parsed date time is correct
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), parsedDateTime, "Parsed date time should match expected value.");
    }

    @Test
    void testParseDateTime_Invalid() {
        // Act: Call the private method parseDateTime with invalid input
        LocalDateTime parsedDateTime = invokePrivateParseDateTime("InvalidTime");

        // Assert: Verify the result is null
        assertEquals(null, parsedDateTime, "Parsed date time should be null for invalid input.");
    }

    /**
     * Helper method to invoke the private parseDateTime method for testing.
     */
    private LocalDateTime invokePrivateParseDateTime(String dateTimeStr) {
        try {
            java.lang.reflect.Method method = AddGroupEventInteractor.class.getDeclaredMethod("parseDateTime", String.class);
            method.setAccessible(true);
            return (LocalDateTime) method.invoke(interactor, dateTimeStr);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke private method", e);
        }
    }
}

