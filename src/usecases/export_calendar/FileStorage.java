package usecases.export_calendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorage {
    public static String saveToFile(String calendar, String name) throws IOException {

        String downloadsPath = System.getProperty("user.home") + "/Downloads/";
        Path path = Paths.get(downloadsPath);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String filePath = path + name;
        Files.write(Paths.get(filePath), calendar.getBytes());
        return filePath;
    }
}
