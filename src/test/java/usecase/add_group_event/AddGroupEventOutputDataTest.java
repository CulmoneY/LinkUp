package usecase.add_group_event;

import entity.CommonGroup;
import entity.Group;
import org.junit.jupiter.api.Test;
import usecases.add_group_event.AddGroupEventOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddGroupEventOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Output data
        AddGroupEventOutputData outputData = new AddGroupEventOutputData(eventName, startTime, endTime);

        // Check constructor correctly sets the fields
        assertEquals(eventName, outputData.getEventName(), "Event name should match the one passed in the constructor.");
        assertEquals(startTime, outputData.getStartTime(), "Start time should match the one passed in the constructor.");
        assertEquals(endTime, outputData.getEndTime(), "End time should match the one passed in the constructor.");
    }

    @Test
    public void testGetEventName() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Output data
        AddGroupEventOutputData outputData = new AddGroupEventOutputData(eventName, startTime, endTime);

        // Check getEventName returns the correct event name
        assertEquals(eventName, outputData.getEventName(), "getEventName should return the correct event name.");
    }

    @Test
    public void testGetStartTime() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Output data
        AddGroupEventOutputData outputData = new AddGroupEventOutputData(eventName, startTime, endTime);

        // Check getStartTime returns the correct start time
        assertEquals(startTime, outputData.getStartTime(), "getStartTime should return the correct start time.");
    }

    @Test
    public void testGetEndTime() {
        String eventName = "Meeting";
        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Output data
        AddGroupEventOutputData outputData = new AddGroupEventOutputData(eventName, startTime, endTime);

        // Check getEndTime returns the correct end time
        assertEquals(endTime, outputData.getEndTime(), "getEndTime should return the correct end time.");
    }

    @Test
    public void testGetSuccess() {
        String eventName = "Meeting";

        String startTime = "2024-11-30 20:00";
        String endTime = "2024-11-30 21:00";
        Group group = new CommonGroup("the_boys", new ArrayList<>()); // Initializing a CommonGroup

        // Output data
        AddGroupEventOutputData outputData = new AddGroupEventOutputData(eventName, startTime, endTime);

        // Since success isn't set in the constructor, assume a default state of false
        assertFalse(outputData.getSuccess(), "getSuccess should return the default value of false.");
    }
}
