package usecase.timeslot_selection;

import entity.CommonGroup;
import entity.CommonGroupEvent;
import entity.CommonUser;
import entity.Event;
import entity.User;
import entity.Group;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.timeslot_selection.TimeslotSelectionInputData;

import static org.junit.jupiter.api.Assertions.*;

class TimeslotSelectionInputDataTest {

    private User mockUser;
    private Event mockEvent;
    private Group mockGroup;
    private LocalDateTime testTime;
    private int testDuration;
    private TimeslotSelectionInputData timeslotSelectionInputData;

    @BeforeEach
    void setUp() {
        // Create a mock user
        mockUser = new CommonUser("John Doe", "password123", "English");

        // Create a mock event using CommonGroupEvent
        mockEvent = new CommonGroupEvent(
                "Test Event",
                LocalDateTime.of(2024, 11, 15, 10, 0), // Event start time
                LocalDateTime.of(2024, 11, 15, 11, 0)  // Event end time
        );

        // Add the event to the user's calendar
        mockUser.getUserCalendar().addEvent(mockEvent);

        // Create a group with the mock user
        List<User> users = Arrays.asList(mockUser);
        mockGroup = new CommonGroup("Test Group", users);

        // Set up test data for TimeslotSelectionInputData
        testTime = LocalDateTime.of(2024, 10, 1, 14, 0); // Test time (e.g., 2:00 PM)
        testDuration = 60; // Test duration (e.g., 60 minutes)

        // Instantiate the TimeslotSelectionInputData object
        timeslotSelectionInputData = new TimeslotSelectionInputData(mockGroup, testDuration, testTime);
    }

    @Test
    void testConstructorAndGetters() {
        // Assert that the group is initialized correctly
        assertEquals(mockGroup, timeslotSelectionInputData.getGroup(), "The group should be the same as the one passed into the constructor.");

        // Assert that the duration is initialized correctly
        assertEquals(testDuration, timeslotSelectionInputData.getDuration(), "The duration should be the same as the one passed into the constructor.");

        // Assert that the time is initialized correctly
        assertEquals(testTime, timeslotSelectionInputData.getTime(), "The time should be the same as the one passed into the constructor.");
    }

    @Test
    void testGroupNotNull() {
        assertNotNull(timeslotSelectionInputData.getGroup(), "Group should not be null");
    }

    @Test
    void testDurationPositive() {
        assertTrue(timeslotSelectionInputData.getDuration() > 0, "Duration should be a positive number");
    }

    @Test
    void testTimeNotNull() {
        assertNotNull(timeslotSelectionInputData.getTime(), "Time should not be null");
    }

    @Test
    void testEventAddedToGroup() {
        // Check if the event is correctly added to the group's calendar
        assertTrue(mockGroup.getGroupCalendar().getEvents().contains(mockEvent), "Event should be added to the group calendar.");
    }

    @Test
    void testGroupUserCount() {
        // Check if the group has exactly 1 user
        assertEquals(1, mockGroup.getUsers().size(), "The group should contain exactly one user.");
    }

    @Test
    void testUserCalendarHasEvent() {
        // Verify that the user's calendar has the event
        assertTrue(mockUser.getUserCalendar().getEvents().contains(mockEvent), "User's calendar should contain the event.");
    }

    @Test
    void testAddEventToGroup() {
        // Create a new event and add it to the group's calendar
        Event newEvent = new CommonGroupEvent(
                "New Event",
                LocalDateTime.of(2024, 11, 16, 9, 0),
                LocalDateTime.of(2024, 11, 16, 10, 0)
        );
        mockGroup.addGroupEvent(newEvent);

        // Assert that the new event is added to the group's calendar
        assertTrue(mockGroup.getGroupCalendar().getEvents().contains(newEvent), "The new event should be added to the group calendar.");
    }

    @Test
    void testRemoveEventFromGroup() {
        // Create a new event and add it to the group's calendar
        Event newEvent = new CommonGroupEvent(
                "New Event",
                LocalDateTime.of(2024, 11, 16, 9, 0),
                LocalDateTime.of(2024, 11, 16, 10, 0)
        );
        mockGroup.addGroupEvent(newEvent);

        // Remove the event and verify it is removed from the group's calendar
        mockGroup.removeGroupEvent(newEvent);
        assertFalse(mockGroup.getGroupCalendar().getEvents().contains(newEvent), "The event should be removed from the group calendar.");
    }
}
