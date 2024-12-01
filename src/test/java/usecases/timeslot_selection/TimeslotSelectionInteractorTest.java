package usecases.timeslot_selection;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TimeslotSelectionInteractorTest {

    private TimeslotSelectionDataAccessInterface dataAccess;
    private TimeslotSelectionOutputBoundary presenter;
    private EventFactory eventFactory;
    private TimeslotSelectionInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = mock(TimeslotSelectionDataAccessInterface.class);
        presenter = mock(TimeslotSelectionOutputBoundary.class);
        eventFactory = mock(EventFactory.class);
        interactor = new TimeslotSelectionInteractor(dataAccess, presenter, eventFactory);
    }

    @Test
    void testFindAvailableSlot() {
        // Arrange
        Group group = mock(Group.class);
        Calendar calendar = mock(Calendar.class);
        List<Event> events = new ArrayList<>();

        when(dataAccess.getGroup("Test Group")).thenReturn(group);
        when(group.getGroupCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);
        when(group.getName()).thenReturn("Test Group");

        User user = mock(User.class);
        when(user.getGroups()).thenReturn(Collections.singletonList(group));

        TimeslotSelectionInputData inputData = new TimeslotSelectionInputData("Test Group", user);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<TimeslotSelectionOutputData> captor = ArgumentCaptor.forClass(TimeslotSelectionOutputData.class);
        verify(presenter).setPassView(captor.capture());

        TimeslotSelectionOutputData capturedOutput = captor.getValue();
        assertEquals("Test Group's Linkup", capturedOutput.getEvent().getEventName());
    }

    @Test
    void testFindAvailableSlotWithOverlappingEvents() {
        // Arrange
        Group group = mock(Group.class);
        Calendar calendar = mock(Calendar.class);
        List<Event> events = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        events.add(new CommonGroupEvent("Event 1", now.plusHours(1), now.plusHours(3)));

        when(dataAccess.getGroup("Test Group")).thenReturn(group);
        when(group.getGroupCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);
        when(group.getName()).thenReturn("Test Group");

        User user = mock(User.class);
        when(user.getGroups()).thenReturn(Collections.singletonList(group));

        TimeslotSelectionInputData inputData = new TimeslotSelectionInputData("Test Group", user);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(presenter).setPassView(any(TimeslotSelectionOutputData.class));
    }

    @Test
    void testNoAvailableSlot() {
        // Arrange
        Group group = mock(Group.class);
        Calendar calendar = mock(Calendar.class);
        List<Event> events = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // Add events to block all possible slots
        for (int i = 0; i < 48; i++) {
            events.add(new CommonGroupEvent("Event " + i, now.plusMinutes(i * 30), now.plusMinutes(i * 30 + 29)));
        }

        when(dataAccess.getGroup("Test Group")).thenReturn(group);
        when(group.getGroupCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);
        when(group.getName()).thenReturn("Test Group");

        User user = mock(User.class);
        when(user.getGroups()).thenReturn(Collections.singletonList(group));

        TimeslotSelectionInputData inputData = new TimeslotSelectionInputData("Test Group", user);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<TimeslotSelectionOutputData> captor = ArgumentCaptor.forClass(TimeslotSelectionOutputData.class);
        verify(presenter).setPassView(captor.capture());

        TimeslotSelectionOutputData capturedOutput = captor.getValue();
        assertEquals("Test Group's Linkup", capturedOutput.getEvent().getEventName());
    }

    @Test
    void testWhileLoopForMultipleDays() {
        // Arrange
        Group group = mock(Group.class);
        Calendar calendar = mock(Calendar.class);
        List<Event> events = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        // Add events to block the entire first day
        LocalDateTime dayStart = now.withHour(9).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dayEnd = now.withHour(22).withMinute(0).withSecond(0).withNano(0);
        for (LocalDateTime time = dayStart; time.plusMinutes(120).isBefore(dayEnd); time = time.plusMinutes(120)) {
            events.add(new CommonGroupEvent("Blocked Event", time, time.plusMinutes(120)));
        }

        // Ensure the second day is free
        when(dataAccess.getGroup("Test Group")).thenReturn(group);
        when(group.getGroupCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(events);
        when(group.getName()).thenReturn("Test Group");

        User user = mock(User.class);
        when(user.getGroups()).thenReturn(Collections.singletonList(group));

        TimeslotSelectionInputData inputData = new TimeslotSelectionInputData("Test Group", user);

        // Act
        interactor.execute(inputData);

        // Assert
        ArgumentCaptor<TimeslotSelectionOutputData> captor = ArgumentCaptor.forClass(TimeslotSelectionOutputData.class);
        verify(presenter).setPassView(captor.capture());

        TimeslotSelectionOutputData capturedOutput = captor.getValue();
        assertEquals("Test Group's Linkup", capturedOutput.getEvent().getEventName());

        // Verify that the selected event time is after the blocked first day
        LocalDateTime selectedStartTime = capturedOutput.getEvent().getStartTime();
        assertTrue(selectedStartTime.isAfter(dayEnd));
    }

}
