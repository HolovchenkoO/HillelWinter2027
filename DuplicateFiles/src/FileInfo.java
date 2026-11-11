
public class FileInfo {

    private final String name;
    private final String path;
    private final long size;
    private long crc32;

    public FileInfo(String name, String path, long size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getSize() {
        return size;
    }

    public long getCrc32() {
        return crc32;
    }

    public void setCrc32(long crc32) {
        this.crc32 = crc32;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", crc32=" + crc32 +
                '}';
    }
}
