package filesLoggers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerConfigurationLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void loadReadsAllConfigurationValues() throws IOException {
        Path configFile = tempDir.resolve("logger-config.txt");
        Files.writeString(configFile,
                "FILE: logs/Log.txt\n" +
                "LEVEL: DEBUG\n" +
                "MAX-SIZE: 500\n" +
                "FORMAT: [%TIME%][%LEVEL%] Повідомлення: %MESSAGE%\n");

        FileLoggerConfiguration config =
                FileLoggerConfigurationLoader.load(configFile.toString());

        assertEquals("logs/Log.txt", config.getFilePath());
        assertEquals(LoggingLevel.DEBUG, config.getLevel());
        assertEquals(500L, config.getMaxSize());
        assertEquals("[%TIME%][%LEVEL%] Повідомлення: %MESSAGE%", config.getFormat());
    }

    @Test
    void loadUsesDefaultValuesWhenKeysAreMissing() throws IOException {
        Path configFile = tempDir.resolve("empty-config.txt");
        Files.writeString(configFile, "");

        FileLoggerConfiguration config =
                FileLoggerConfigurationLoader.load(configFile.toString());

        assertEquals("log.txt", config.getFilePath());
        assertEquals(LoggingLevel.INFO, config.getLevel());
        assertEquals(1024L, config.getMaxSize());
        assertEquals("[%TIME%][%LEVEL%] Повідомлення: %MESSAGE%", config.getFormat());
    }

    @Test
    void loadThrowsIOExceptionWhenFileDoesNotExist() {
        Path missingFile = tempDir.resolve("missing-config.txt");

        assertThrows(IOException.class,
                () -> FileLoggerConfigurationLoader.load(missingFile.toString()));
    }
}
