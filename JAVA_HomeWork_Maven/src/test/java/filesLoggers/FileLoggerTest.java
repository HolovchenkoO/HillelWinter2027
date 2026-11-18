package filesLoggers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerTest {

    @TempDir
    Path tempDir;

    @Test
    void infoIsWrittenAndDebugIsSkippedAtInfoLevel() throws IOException {
        Path logFile = tempDir.resolve("Log.txt");
        FileLoggerConfiguration config = new FileLoggerConfiguration(
                logFile.toString(), LoggingLevel.INFO, 10_000,
                "[%LEVEL%] %MESSAGE%"
        );

        Logger logger = new FileLogger(config);
        logger.info("Information message");
        logger.debug("Debug message");

        String content = Files.readString(logFile);
        assertTrue(content.contains("[INFO] Information message"));
        assertFalse(content.contains("Debug message"));
    }

    @Test
    void debugIsWrittenAtDebugLevel() throws IOException {
        Path logFile = tempDir.resolve("Debug.txt");
        FileLoggerConfiguration config = new FileLoggerConfiguration(
                logFile.toString(), LoggingLevel.DEBUG, 10_000,
                "[%LEVEL%] %MESSAGE%"
        );

        Logger logger = new FileLogger(config);
        logger.debug("Debug message");

        String content = Files.readString(logFile);
        assertTrue(content.contains("[DEBUG] Debug message"));
    }

    @Test
    void rotatesFileWhenMaxSizeWouldBeExceeded() throws IOException {
        Path logFile = tempDir.resolve("Rotate.txt");
        FileLoggerConfiguration config = new FileLoggerConfiguration(
                logFile.toString(), LoggingLevel.INFO, 1,
                "%MESSAGE%"
        );

        Logger logger = new FileLogger(config);
        logger.info("first");
        logger.info("second");

        assertTrue(Files.exists(logFile));

        List<Path> files;
        try (var paths = Files.list(tempDir)) {
            files = paths.toList();
        }

        assertEquals(2, files.size(), "Очікується початковий та новий файл після ротації");
        assertTrue(files.stream().anyMatch(path -> path.getFileName().toString().startsWith("Rotate_")));
        assertTrue(files.stream().anyMatch(path -> path.getFileName().toString().equals("Rotate.txt")));
    }
}
