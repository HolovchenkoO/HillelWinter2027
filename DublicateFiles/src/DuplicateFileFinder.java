
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.CRC32;

public class DuplicateFileFinder {

    // Пошук файлів у заданих директоріях
    private List<FileInfo> findFiles(Path... directories)
            throws IOException {

        Set<Path> paths = new LinkedHashSet<>();

        for (Path directory : directories) {

            try (var stream = Files.walk(directory)) {

                stream.filter(Files::isRegularFile)
                        .map(path -> path.toAbsolutePath().normalize())
                        .forEach(paths::add);
            }
        }

        return paths.stream()
                .map(path -> {
                    try {
                        return new FileInfo(
                                path.getFileName().toString(),
                                path.toString(),
                                Files.size(path)
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    // Групування файлів за заданою ознакою
    private <T> Map<T, List<FileInfo>> groupFiles(
            List<FileInfo> files,
            Function<FileInfo, T> classifier) {

        return files.stream()
                .collect(Collectors.groupingBy(classifier));
    }

    // Обчислення CRC32
    private long calculateCRC32(FileInfo fileInfo)
            throws IOException {

        CRC32 crc32 = new CRC32();

        try (InputStream input = Files.newInputStream(
                java.nio.file.Paths.get(fileInfo.getPath()))) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                crc32.update(buffer, 0, bytesRead);
            }
        }

        return crc32.getValue();
    }

    // Основний метод пошуку дублікатів
    public Map<String, List<String>> findDuplicates(
            Path... directories) throws IOException {

        // Отримуємо всі файли
        List<FileInfo> files = findFiles(directories);

        // Результат
        Map<String, List<String>> result = new LinkedHashMap<>();

        // STEP 1. Групування за назвою
        Map<String, List<FileInfo>> filesByName =
                groupFiles(files, FileInfo::getName);

        for (Map.Entry<String, List<FileInfo>> nameEntry
                : filesByName.entrySet()) {

            List<FileInfo> sameNameFiles = nameEntry.getValue();

            // Залишаємо групи, де є мінімум 2 файли
            if (sameNameFiles.size() < 2) {
                continue;
            }

            // STEP 2. Групування за розміром
            Map<Long, List<FileInfo>> filesBySize =
                    groupFiles(sameNameFiles, FileInfo::getSize);

            for (List<FileInfo> sameSizeFiles
                    : filesBySize.values()) {

                if (sameSizeFiles.size() < 2) {
                    continue;
                }

                // STEP 3. Обчислення CRC32
                for (FileInfo file : sameSizeFiles) {
                    file.setCrc32(calculateCRC32(file));
                }

                // Групування за CRC32
                Map<Long, List<FileInfo>> filesByCRC32 =
                        groupFiles(sameSizeFiles, FileInfo::getCrc32);

                for (List<FileInfo> sameCRCFiles
                        : filesByCRC32.values()) {

                    // Залишаємо тільки справжні дублікати
                    if (sameCRCFiles.size() < 2) {
                        continue;
                    }

                    List<String> duplicatePaths =
                            sameCRCFiles.stream()
                                    .map(FileInfo::getPath)
                                    .collect(Collectors.toList());

                    result.put(
                            nameEntry.getKey(),
                            duplicatePaths
                    );
                }
            }
        }

        return result;
    }
}
