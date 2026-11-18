package filesLoggers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class FileLogger implements Logger{
    private final FileLoggerConfiguration config;
    private File currentFile;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
        this.currentFile = new File(config.getFilePath());
    }

    @Override
    public void info(String message) {
        // INFO записується завжди, незалежно від того, INFO чи DEBUG встановлено в конфігу
        log("INFO", message);
    }

    @Override
    public void debug(String message) {
        // DEBUG записується ТІЛЬКИ якщо в конфігурації вказано DEBUG
        if (config.getLevel() == LoggingLevel.DEBUG) {
            log("DEBUG", message);
        }
    }

    private void log(String level, String message) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Форматування рядка згідно з конфігурацією
        String logLine = config.getFormat()
                .replace("%TIME%", currentTime)
                .replace("%LEVEL%", level)
                .replace("%MESSAGE%", message) + "\n";

        byte[] bytes = logLine.getBytes();

        checkFileSizeAndRotate(bytes.length);

        try (FileOutputStream fos = new FileOutputStream(currentFile, true)) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFileSizeAndRotate(int newBytesLength) {
        if (!currentFile.exists()) return;

        if (currentFile.length() + newBytesLength > config.getMaxSize()) {


            // Виконання п.6: Ротація файлу
            String timeSuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss"))
                    .replace(":", "-"); // Заміна двокрапок для сумісності з іменами файлів Windows

            String originalPath = config.getFilePath();
            int dotIndex = originalPath.lastIndexOf('.');
            String newPath = (dotIndex != -1)
                    ? originalPath.substring(0, dotIndex) + "_" + timeSuffix + originalPath.substring(dotIndex)
                    : originalPath + "_" + timeSuffix + ".txt";

            currentFile = new File(newPath);
        }
    }
}
