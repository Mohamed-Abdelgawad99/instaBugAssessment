package runners;

import java.io.IOException;
import java.nio.file.*;

public class AllureHistoryHelper {

    public static void preserveHistory() throws IOException {
        Path currentHistory = Paths.get("allure-report/history");
        Path tempHistory = Paths.get("temp-allure-history");

        if (Files.exists(currentHistory)) {
            Files.createDirectories(tempHistory);
            copyDirectory(currentHistory, tempHistory);
        }
    }

    public static void restoreHistory() throws IOException {
        Path savedHistory = Paths.get("temp-allure-history");
        Path targetHistory = Paths.get("allure-results/history");

        if (Files.exists(savedHistory)) {
            Files.createDirectories(targetHistory);
            copyDirectory(savedHistory, targetHistory);
        }
    }

    private static void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source).forEach(path -> {
            try {
                Path targetPath = target.resolve(source.relativize(path));
                if (Files.isDirectory(path)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
