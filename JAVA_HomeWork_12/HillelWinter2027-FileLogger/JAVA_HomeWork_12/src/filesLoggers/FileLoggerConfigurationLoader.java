package filesLoggers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class FileLoggerConfigurationLoader {

    public static FileLoggerConfiguration load(String configFilePath) throws IOException {
        String file = "log.txt";
        LoggingLevel level = LoggingLevel.INFO;
        long maxSize = 1024;
        // Базовий формат, змінні будуть замінені у логері
        String format = "[%TIME%][%LEVEL%] Повідомлення: %MESSAGE%";

        try (BufferedReader reader = new BufferedReader(new FileReader(configFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":\\s+", 2);
                if (parts.length < 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "FILE": file = value; break;
                    case "LEVEL": level = LoggingLevel.valueOf(value); break;
                    case "MAX-SIZE": maxSize = Long.parseLong(value); break;
                    case "FORMAT": format = value; break;
                }
            }
        }
        return new FileLoggerConfiguration(file, level, maxSize, format);
    }
}
