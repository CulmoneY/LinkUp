package usecases.export_calendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorage {
    public static String saveToFile(String calendar, String name) throws IOException {

        String downloadsPath = "Downloads/";

        Path directoryPath = Paths.get(downloadsPath);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        String filePath = downloadsPath + name;
        Files.write(Paths.get(filePath), calendar.getBytes());

        return filePath;
    }
}
