package usecase.export_calendar;

import org.junit.jupiter.api.Test;
import usecases.export_calendar.ExportCalendarOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class TestOutputData {

    @Test
    public void testOutputDataWithValidFilePath() {
        String filePath = "/Downloads/test.ics";
        ExportCalendarOutputData outputData = new
                ExportCalendarOutputData(true, filePath, "Export successful.");

        assertNotNull(outputData, "ExportCalendarOutputData instance should not be null.");
        assertEquals(filePath, outputData.getFilePath(), "File path should match.");
        assertTrue(outputData.getSuccess(), "Export should be successful.");
        assertEquals("Export successful.", outputData.getMessage(), "Message should match.");
    }

    @Test
    public void testConstructorWithNullFilePath() {
        ExportCalendarOutputData outputData = new
                ExportCalendarOutputData(false, null, "Export fail.");

        assertNotNull(outputData, "ExportCalendarOutputData instance should not be null.");
        assertNull(outputData.getFilePath(),
                "The file path should be null if null was provided during construction.");
        assertFalse(outputData.getSuccess(), "Export should fail.");
        assertEquals("Export fail.", outputData.getMessage(), "Message should match.");
    }
}
