package usecases.timeslot_selection;

import entity.Event;
import entity.CommonGroupEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.timeslot_selection.TimeslotSelectionOutputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeslotSelectionOutputDataTest {

    private Event event;
    private TimeslotSelectionOutputData outputData;

    @BeforeEach
    void setUp() {
        // Create a sample event for testing
        event = new CommonGroupEvent("Sample Event", LocalDateTime.of(2024, 10, 1, 14, 0), LocalDateTime.of(2024, 10, 1, 16, 0));

        // Create TimeslotSelectionOutputData instance with the event
        outputData = new TimeslotSelectionOutputData(event);
    }

    @Test
    void testConstructorAndGetEvent() {
        // Assert that the event passed in the constructor is the one retrieved by getEvent()
        assertNotNull(outputData.getEvent(), "The event should not be null");
        assertEquals(event, outputData.getEvent(), "The event returned by getEvent() should match the event passed to the constructor");
    }
}
