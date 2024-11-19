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
            try {
                Files.createDirectories(path);
            } catch (IOException error) {
                throw new IOException("Failed to create Downloads directory: " + error.getMessage());
            }
        }

        String filePath = downloadsPath + name;
        try {
            Files.write(Paths.get(filePath), calendar.getBytes());
        } catch (IOException e) {
            throw new IOException("Failed to write file: " + e.getMessage());
        }
        return filePath;
    }
}
