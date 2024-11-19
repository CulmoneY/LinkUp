package usecases.export_calendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileStorage {
    public static String saveToFile(String icsCalendar, String name) throws IOException {
        String filePath = "exports/" + name;
        Files.write(Paths.get(filePath), icsCalendar.getBytes());
        return filePath;
    }
}
