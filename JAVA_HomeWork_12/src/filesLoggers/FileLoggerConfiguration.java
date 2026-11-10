package filesLoggers;


public class FileLoggerConfiguration {
    private String filePath;
    private LoggingLevel level;
    private long maxSize;
    private String format;

    public FileLoggerConfiguration(String filePath, LoggingLevel level, long maxSize, String format) {
        this.filePath = filePath;
        this.level = level;
        this.maxSize = maxSize;
        this.format = format;
    }

    public String getFilePath() { return filePath; }
    public LoggingLevel getLevel() { return level; }
    public long getMaxSize() { return maxSize; }
    public String getFormat() { return format; }
}
