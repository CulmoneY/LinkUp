package usecase.export_calendar;

import org.junit.jupiter.api.Test;
import usecases.export_calendar.FileStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ExportCalendarFileStorageTest {

    @Test
    public void testSaveToFileCreatesFile() throws IOException {
        String calendar = "Test Calendar";
        String name = "testFile.ics";

        String filePath = FileStorage.saveToFile(calendar, name);

        Path path = Path.of(filePath);
        assertTrue(Files.exists(path), "The file should exist.");
        assertEquals(calendar, Files.readString(path), "The file content should match.");
        Files.delete(path);
    }

    @Test
    public void testSaveToFileCreatesDirectory() throws IOException {
        String calendar = "Test Calendar";
        String name = "testDirectoryFile.ics";

        String filePath = FileStorage.saveToFile(calendar, name);
        Path directoryPath = Path.of(System.getProperty("user.home") + "/Downloads/");

        assertTrue(Files.exists(directoryPath), "The Downloads directory should exist.");
        Files.delete(Path.of(filePath));
    }
}
