package usecase.add_group_event;

import entity.CommonGroup;
import entity.Group;
import org.junit.jupiter.api.Test;
import usecases.add_group_event.AddGroupEventInputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddGroupEventInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Input data
        AddGroupEventInputData inputData = new AddGroupEventInputData(eventName, startTime, endTime, group.getName());

        // Check constructor correctly sets the parameters
        assertEquals(eventName, inputData.getEventName(), "Event name should match the one passed in the constructor.");
        assertEquals(startTime, inputData.getStartTime(), "Start time should match the one passed in the constructor.");
        assertEquals(endTime, inputData.getEndTime(), "End time should match the one passed in the constructor.");
        assertEquals(group.getName(), inputData.getGroupName(), "Group name should match the one passed in the constructor.");
    }

    @Test
    public void testGetEventName() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Input data
        AddGroupEventInputData inputData = new AddGroupEventInputData(eventName, startTime, endTime, group.getName());

        // Check getEventName returns the correct event name
        assertEquals(eventName, inputData.getEventName(), "getEventName should return the correct event name.");
    }

    @Test
    public void testGetStartTime() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Input data
        AddGroupEventInputData inputData = new AddGroupEventInputData(eventName, startTime, endTime, group.getName());

        // Check getStartTime returns the correct start time
        assertEquals(startTime, inputData.getStartTime(), "getStartTime should return the correct start time.");
    }

    @Test
    public void testGetEndTime() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Input data
        AddGroupEventInputData inputData = new AddGroupEventInputData(eventName, startTime, endTime, group.getName());

        // Check getEndTime returns the correct end time
        assertEquals(endTime, inputData.getEndTime(), "getEndTime should return the correct end time.");
    }

    @Test
    public void testGetUser() {
        // Test data
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Input data
        AddGroupEventInputData inputData = new AddGroupEventInputData(eventName, startTime, endTime, group.getName());

        // Check getUser returns the correct groupName
        assertEquals(group.getName(), inputData.getGroupName(), "getUser should return the correct user.");
    }
}
