package usecases.export_calendar;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;

class ExportCalendarInteractorTest {

    private ExportCalendarInteractor interactor;
    private ExportCalendarDataAccessInterface dataAccessMock;
    private ExportCalendarOutputBoundary outputBoundaryMock;
    private EventFactory eventFactory;

    @BeforeEach
    void setup() {
        dataAccessMock = mock(ExportCalendarDataAccessInterface.class);
        outputBoundaryMock = mock(ExportCalendarOutputBoundary.class);
        eventFactory = new CommonEventFactory();
        interactor = new ExportCalendarInteractor(dataAccessMock, outputBoundaryMock);
    }

    private Event createEvent(boolean isGroup) {
        return eventFactory.create("EventName", LocalDateTime.now(), LocalDateTime.now().plusHours(1), isGroup);
    }

    @Test
    void testExecute_UserWithCalendar() {
        User user = mock(User.class);
        Calendar calendar = mock(Calendar.class);
        when(user.getUserCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(Arrays.asList(createEvent(false)));
        when(calendar.getName()).thenReturn("UserCalendar");

        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        interactor.execute(inputData);

        verify(outputBoundaryMock).exportSuccess(any(ExportCalendarOutputData.class));
    }

    @Test
    void testExecute_GroupWithCalendar() {
        Group group = mock(Group.class);
        Calendar calendar = mock(Calendar.class);
        when(dataAccessMock.getGroup("GroupName")).thenReturn(group);
        when(group.getGroupCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(Arrays.asList(createEvent(true)));
        when(calendar.getName()).thenReturn("GroupCalendar");

        ExportCalendarInputData inputData = new ExportCalendarInputData("GroupName");

        interactor.execute(inputData);

        verify(outputBoundaryMock).exportSuccess(any(ExportCalendarOutputData.class));
    }

    @Test
    void testExecute_EmptyCalendar() {
        User user = mock(User.class);
        Calendar calendar = mock(Calendar.class);
        when(user.getUserCalendar()).thenReturn(calendar);
        when(calendar.getEvents()).thenReturn(new ArrayList<>());
        when(calendar.getName()).thenReturn("EmptyCalendar");

        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        interactor.execute(inputData);

        verify(outputBoundaryMock).exportFail("Calendar has no events to export.");
    }

    @Test
    void testExecute_GeneralException() {
        String groupName = "GroupName";
        doThrow(new RuntimeException("Database error")).when(dataAccessMock).getGroup(groupName);

        ExportCalendarInputData inputData = new ExportCalendarInputData(groupName);

        interactor.execute(inputData);

        verify(outputBoundaryMock).exportFail("An unexpected error occurred: Database error");
    }
}
