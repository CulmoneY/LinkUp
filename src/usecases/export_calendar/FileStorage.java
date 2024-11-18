package usecases.export_calendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileStorage {
    public static String saveToFile(String content, String fileName) throws IOException {
        String filePath = "exports/" + fileName;
        Files.write(Paths.get(filePath), content.getBytes());
        return filePath;
    }
}
