package usecases.add_personal_event;

import entity.Calendar;
import entity.Event;
import entity.EventFactory;
import entity.Group;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddPersonalEventInteractorTest {

    private AddPersonalEventInteractor interactor;
    private AddPersonalEventDataAccessInterface dataAccess;
    private AddPersonalEventOutputBoundary outputBoundary;
    private EventFactory eventFactory;

    @BeforeEach
    void setUp() {
        dataAccess = mock(AddPersonalEventDataAccessInterface.class);
        outputBoundary = mock(AddPersonalEventOutputBoundary.class);
        eventFactory = mock(EventFactory.class);
        interactor = new AddPersonalEventInteractor(dataAccess, outputBoundary, eventFactory);

        // Mock the event creation to return a fully initialized event
        Event mockEvent = mock(Event.class);
        LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 11, 0);
        when(mockEvent.getEventName()).thenReturn("Study Session");
        when(mockEvent.getStartTime()).thenReturn(startTime);
        when(mockEvent.getEndTime()).thenReturn(endTime);
        when(eventFactory.create(anyString(), any(LocalDateTime.class), any(LocalDateTime.class), eq(false))).thenReturn(mockEvent);
    }


    private User createTestUser() {
        User user = mock(User.class);
        when(user.getUserCalendar()).thenReturn(mock(Calendar.class));
        return user;
    }

    private User createTestUserWithGroups() {
        User user = createTestUser();
        List<Group> groups = new ArrayList<>();
        groups.add(mock(Group.class));
        groups.add(mock(Group.class));

        when(groups.get(0).getName()).thenReturn("Study Group");
        when(groups.get(1).getName()).thenReturn("Book Club");
        when(user.getGroups()).thenReturn(groups);

        return user;
    }

    @Nested
    class AddPersonalEventTests {

        @Test
        void testMissingFields_EndTimeEmpty() {
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "2024-12-12 10:00", "", user);

            interactor.executeCreate(inputData);

            ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
            verify(outputBoundary).setFailView(captor.capture());
            assertEquals("Fill in all Fields!", captor.getValue());
        }

        @Test
        void testValidTime_ReturnsFalseWhenNull() {
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Meeting", "invalid_start_time", "invalid_end_time", user);

            interactor.executeCreate(inputData);

            ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
            verify(outputBoundary).setFailView(captor.capture());
            assertEquals("Invalid Time Format!", captor.getValue());
        }

        @Test
        void testSuccessfulEventCreation() {
            // Prepare
            User user = createTestUser();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Study Session", "2024-01-01 09:00", "2024-01-01 11:00", user);

            // Act
            interactor.executeCreate(inputData);

            // Assert
            ArgumentCaptor<AddPersonalEventOutputData> captor = ArgumentCaptor.forClass(AddPersonalEventOutputData.class);
            verify(outputBoundary).setPassView(captor.capture());
            AddPersonalEventOutputData outputData = captor.getValue();
            assertEquals("Study Session", outputData.getEventName());
            assertEquals("2024-01-01T09:00", outputData.getStartTime());
            assertEquals("2024-01-01T11:00", outputData.getEndTime());
        }


        @Test
        void testAddEventToGroups() {
            // Arrange
            User user = createTestUserWithGroups();
            AddPersonalEventInputData inputData = new AddPersonalEventInputData("Study Session", "2024-01-01 09:00", "2024-01-01 11:00", user);

            // Act
            interactor.executeCreate(inputData);

            // Assert
            List<Group> groups = user.getGroups();
            for (Group group : groups) {
                verify(group, times(1)).addGroupEvent(any(Event.class));
            }

            verify(user.getUserCalendar(), times(1)).addEvent(any(Event.class));
            ArgumentCaptor<AddPersonalEventOutputData> captor = ArgumentCaptor.forClass(AddPersonalEventOutputData.class);
            verify(outputBoundary).setPassView(captor.capture());
            AddPersonalEventOutputData outputData = captor.getValue();
            assertEquals("Study Session", outputData.getEventName());
            assertEquals("2024-01-01T09:00", outputData.getStartTime());
            assertEquals("2024-01-01T11:00", outputData.getEndTime());
        }
    }

    @Test
    void testEventFactoryCreatesNonNullEvents() {
        // Arrange
        String eventName = "Study Session";
        String startTime = "2024-01-01 09:00";
        String endTime = "2024-01-01 11:00";
        Event event = eventFactory.create(eventName, LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), false);

        // Assert
        assertNotNull(event);
        assertNotNull(event.getStartTime());
        assertNotNull(event.getEndTime());
        assertEquals(eventName, event.getEventName());
    }

}
