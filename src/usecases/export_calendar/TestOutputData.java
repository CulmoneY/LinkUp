package usecases.export_calendar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestOutputData {

    @Test
    public void testOutputData() {
        String filePath = "/Downloads/test.ics";
        ExportCalendarOutputData outputData = new
                ExportCalendarOutputData(true, filePath, "Export successful.");

        assertNotNull(outputData, "ExportCalendarOutputData instance should not be null.");
        assertEquals(filePath, outputData.getFilePath(), "File path should match the one provided during construction.");
    }

}
