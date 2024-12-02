package usecases.delete_personal_event;

import entity.Calendar;
import entity.Group;
import entity.Event;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeletePersonalEventInteractorTest {

    private DeletePersonalEventDataAccessInterface dataAccess;
    private DeletePersonalEventOutputBoundary outputBoundary;
    private DeletePersonalEventInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = mock(DeletePersonalEventDataAccessInterface.class);
        outputBoundary = mock(DeletePersonalEventOutputBoundary.class);
        interactor = new DeletePersonalEventInteractor(dataAccess, outputBoundary);
    }

    @Test
    void testExecuteDeleteEventExists() {
        // Arrange
        User user = mock(User.class);
        Calendar calendar = mock(Calendar.class);
        Event event = mock(Event.class);
        List<Event> events = new ArrayList<>();
        events.add(event);

        when(user.getName()).thenReturn("TestUser");
        when(user.getUserCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);
        when(event.getEventName()).thenReturn("Test Event");

        LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 12, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        when(event.getStartTime()).thenReturn(startTime);
        when(event.getEndTime()).thenReturn(endTime);

        DeletePersonalEventInputData inputData = new DeletePersonalEventInputData(user, "Test Event",
                startTime.format(formatter), endTime.format(formatter));

        // Act
        interactor.executeDelete(inputData);

        // Assert
        verify(dataAccess).removeUserEvent("TestUser", "Test Event", startTime.format(formatter), endTime.format(formatter));

        ArgumentCaptor<DeletePersonalEventOutputData> captor = ArgumentCaptor.forClass(DeletePersonalEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("Test Event", captor.getValue().getEventName());

        verify(calendar).removeEvent(event);
    }

    @Test
    void testExecuteDeleteEventDoesNotExist() {
        // Arrange
        User user = mock(User.class);
        Calendar calendar = mock(Calendar.class);
        List<Event> events = new ArrayList<>(); // Empty event list

        when(user.getName()).thenReturn("TestUser");
        when(user.getUserCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);

        DeletePersonalEventInputData inputData = new DeletePersonalEventInputData(user, "Nonexistent Event",
                "2024-01-01 10:00", "2024-01-01 12:00");

        // Act
        interactor.executeDelete(inputData);

        // Assert
        verify(dataAccess).removeUserEvent("TestUser", "Nonexistent Event", "2024-01-01 10:00", "2024-01-01 12:00");

        ArgumentCaptor<DeletePersonalEventOutputData> captor = ArgumentCaptor.forClass(DeletePersonalEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("Nonexistent Event", captor.getValue().getEventName());

        verify(calendar, never()).removeEvent(any());
    }

    @Test
    void testExecuteDeletePartialMatch() {
        // Arrange
        User user = mock(User.class);
        Calendar calendar = mock(Calendar.class);
        Event event = mock(Event.class);
        List<Event> events = new ArrayList<>();
        events.add(event);

        when(user.getName()).thenReturn("TestUser");
        when(user.getUserCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);
        when(event.getEventName()).thenReturn("Test Event");

        // Start time and end time do not match
        when(event.getStartTime()).thenReturn(LocalDateTime.of(2024, 1, 1, 11, 0));
        when(event.getEndTime()).thenReturn(LocalDateTime.of(2024, 1, 1, 13, 0));

        DeletePersonalEventInputData inputData = new DeletePersonalEventInputData(user, "Test Event",
                "2024-01-01 10:00", "2024-01-01 12:00");

        // Act
        interactor.executeDelete(inputData);

        // Assert
        verify(dataAccess).removeUserEvent("TestUser", "Test Event", "2024-01-01 10:00", "2024-01-01 12:00");

        ArgumentCaptor<DeletePersonalEventOutputData> captor = ArgumentCaptor.forClass(DeletePersonalEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("Test Event", captor.getValue().getEventName());

        verify(calendar, never()).removeEvent(event);
    }

    @Test
    void testExecuteDeleteWithUserInMultipleGroups() {
        // Arrange
        User user = mock(User.class);
        Calendar calendar = mock(Calendar.class);
        Group group1 = mock(Group.class);
        Group group2 = mock(Group.class);
        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);

        Event event = mock(Event.class);
        List<Event> events = new ArrayList<>();
        events.add(event);

        when(user.getName()).thenReturn("TestUser");
        when(user.getUserCalendar()).thenReturn(calendar);
        when(user.getGroups()).thenReturn(groups);
        when(group1.getName()).thenReturn("Study Group");
        when(group2.getName()).thenReturn("Book Club");
        when(calendar.getEvents()).thenReturn(events);
        when(event.getEventName()).thenReturn("Weekly Meeting");

        LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 12, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        when(event.getStartTime()).thenReturn(startTime);
        when(event.getEndTime()).thenReturn(endTime);

        DeletePersonalEventInputData inputData = new DeletePersonalEventInputData(user, "Weekly Meeting",
                startTime.format(formatter), endTime.format(formatter));

        // Act
        interactor.executeDelete(inputData);

        // Assert
        verify(dataAccess).removeUserEvent("TestUser", "Weekly Meeting", startTime.format(formatter), endTime.format(formatter));
        verify(dataAccess).removeGroupEvent("Study Group", "Weekly Meeting", startTime.format(formatter), endTime.format(formatter));
        verify(dataAccess).removeGroupEvent("Book Club", "Weekly Meeting", startTime.format(formatter), endTime.format(formatter));

        ArgumentCaptor<DeletePersonalEventOutputData> captor = ArgumentCaptor.forClass(DeletePersonalEventOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("Weekly Meeting", captor.getValue().getEventName());

        verify(calendar).removeEvent(event);
    }
}
