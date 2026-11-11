
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        DuplicateFileFinder finder = new DuplicateFileFinder();

        try {
            // Директорії для пошуку
            Map<String, List<String>> duplicates =
                    finder.findDuplicates(
                            Paths.get("E:\\JAVA_HILLEL\\Test"),
                            Paths.get("E:\\JAVA_HILLEL\\Test 2")
                    );

            // Виведення результату
            if (duplicates.isEmpty()) {
                System.out.println("Дублікатів не знайдено.");
            } else {
                System.out.println("Знайдені дублікати:");

                duplicates.forEach((name, paths) -> {
                    System.out.println("\nFile: " + name);

                    paths.forEach(path ->
                            System.out.println("  " + path)
                    );
                });
            }

        } catch (IOException e) {
            System.err.println(
                    "Помилка при пошуку файлів: " + e.getMessage()
            );
        }
    }
}
