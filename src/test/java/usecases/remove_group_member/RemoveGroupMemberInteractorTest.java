package usecases.remove_group_member;

import entity.User;
import entity.Calendar;
import entity.Event;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveGroupMemberInteractorTest {

    private RemoveGroupMemberOutputBoundary mockOutputPresenter;
    private RemoveGroupMemberDataAccessInterface mockDataAccess;
    private RemoveGroupMemberInteractor interactor;
    private User mockUser;
    private Calendar mockCalendar;
    private Event mockEvent;

    @BeforeEach
    public void setUp() {
        mockOutputPresenter = mock(RemoveGroupMemberOutputBoundary.class);
        mockDataAccess = mock(RemoveGroupMemberDataAccessInterface.class);
        interactor = new RemoveGroupMemberInteractor(mockOutputPresenter, mockDataAccess);
        mockUser = mock(User.class);
        mockCalendar = mock(Calendar.class);
        mockEvent = mock(Event.class);

        when(mockDataAccess.getUser(anyString())).thenReturn(mockUser);
        when(mockUser.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(List.of(mockEvent));
        when(mockEvent.getEventName()).thenReturn("Meeting");
        when(mockEvent.getStartTime()).thenReturn(LocalDateTime.now());
        when(mockEvent.getEndTime()).thenReturn(LocalDateTime.now().plusHours(1));
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
        verify(mockDataAccess, times(1)).getUser(userName);
        verify(mockDataAccess, times(1)).removeGroupEvent(eq(groupName), anyString(), anyString(), anyString());

        ArgumentCaptor<RemoveGroupMemberOutputData> captor = ArgumentCaptor.forClass(RemoveGroupMemberOutputData.class);
        verify(mockOutputPresenter, times(1)).setPassViewData(captor.capture());

        RemoveGroupMemberOutputData capturedData = captor.getValue();
        assertEquals(groupName, capturedData.getGroupname(), "Group name should match.");
        assertEquals(userName, capturedData.getUsername(), "User name should match.");
    }

    @Test
    public void testExecuteRemoveGroupMemberWithMultipleEvents() {
        // Arrange
        String groupName = "Study Group";
        String userName = "JohnDoe";
        RemoveGroupMemberInputData inputData = new RemoveGroupMemberInputData(groupName, userName);
        Event event1 = mock(Event.class);
        Event event2 = mock(Event.class);

        when(mockDataAccess.getUser(userName)).thenReturn(mockUser);
        when(mockUser.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(List.of(event1, event2));
        when(event1.getEventName()).thenReturn("Meeting 1");
        when(event1.getStartTime()).thenReturn(LocalDateTime.now());
        when(event1.getEndTime()).thenReturn(LocalDateTime.now().plusHours(1));
        when(event2.getEventName()).thenReturn("Meeting 2");
        when(event2.getStartTime()).thenReturn(LocalDateTime.now().plusHours(2));
        when(event2.getEndTime()).thenReturn(LocalDateTime.now().plusHours(3));

        // Act
        interactor.executeRemoveGroupMember(inputData);

        // Assert
        verify(mockDataAccess, times(1)).removeGroupEvent(eq(groupName), eq("Meeting 1"), anyString(), anyString());
        verify(mockDataAccess, times(1)).removeGroupEvent(eq(groupName), eq("Meeting 2"), anyString(), anyString());
        verify(mockOutputPresenter, times(1)).setPassViewData(any());
    }

    @Test
    public void testSetPassViewDataIsCalled() {
        // Arrange
        String groupName = "Study Group";
        String userName = "JohnDoe";
        RemoveGroupMemberInputData inputData = new RemoveGroupMemberInputData(groupName, userName);

        when(mockDataAccess.getUser(userName)).thenReturn(mockUser);
        when(mockUser.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(new ArrayList<>()); // No events

        // Act
        interactor.executeRemoveGroupMember(inputData);

        // Assert
        ArgumentCaptor<RemoveGroupMemberOutputData> captor = ArgumentCaptor.forClass(RemoveGroupMemberOutputData.class);
        verify(mockOutputPresenter, times(1)).setPassViewData(captor.capture());

        RemoveGroupMemberOutputData capturedData = captor.getValue();
        assertEquals(groupName, capturedData.getGroupname());
        assertEquals(userName, capturedData.getUsername());
    }
}
