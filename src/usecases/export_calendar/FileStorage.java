package usecases.export_calendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileStorage {
    public static String saveToFile(String calendar, String name) throws IOException {

        String downloadsPath = "download/";

        String filePath = downloadsPath + name;
        Files.write(Paths.get(filePath), calendar.getBytes());

        return filePath;
    }
}
