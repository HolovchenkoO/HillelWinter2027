package fileNavigator;

public class FileData {
    private String fileName;
    private int size;
    private String path;

    public FileData(String fileName, int size, String path) {
        this.fileName = fileName;
        this.size = size;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "{fileName='" + fileName +
                ", size=" + size +
                ", path='" + path + '}';
    }
}
