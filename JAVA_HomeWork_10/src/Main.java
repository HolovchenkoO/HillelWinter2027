import java.util.*;
import fileNavigator.FileData;
import fileNavigator.FileNavigator;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        FileNavigator navigator = new FileNavigator();

        // Додавання файлів
        navigator.add("/path/to/file",
                new FileData("files.txt", 1000, "/path/to/file"));

        navigator.add("/path/to/file",
                new FileData("firstApp.java", 2500, "/path/to/file"));

        navigator.add("/path/to/file",
                new FileData("secondApp.java", 1500, "/path/to/file"));

        navigator.add("/path2/path",
                new FileData("document.pdf", 5000, "/path2/path"));

        navigator.add("/path2/path",
                new FileData("image.jpg", 3000, "/path2/path"));

        // Перевірка консистентності — файл не буде доданий
        navigator.add("/path2/to/file",
                new FileData("wrong.txt", 700, "/path2/path"));

        // Пошук файлів за шляхом
        System.out.println("\nПошук файлів:");

        List<FileData> foundFiles = navigator.find("/path/to/file");

        if (foundFiles != null) {
            for (FileData file : foundFiles) {
                System.out.println(file);
            }
        }

        // Фільтрація за розміром
        System.out.println("\nФайли розміром до 2000 байт:");

        List<FileData> filteredFiles = navigator.filterBySize(2000);

        for (FileData file : filteredFiles) {
            System.out.println(file);
        }

        // Сортування за розміром
        System.out.println("\nУсі файли за розміром:");

        List<FileData> sortedFiles = navigator.sortBySize();

        for (FileData file : sortedFiles) {
            System.out.println(file);
        }

        // Видалення шляху
        navigator.remove("/path2/path");

        System.out.println("\nПісля видалення /path2/path:");

        sortedFiles = navigator.sortBySize();

        for (FileData file : sortedFiles) {
            System.out.println(file);
        }
        //Видалення чисел кратних 3
          List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

        System.out.println("Исходный список: " + numbers);

        Iterator<Integer> iterator = numbers.iterator();

            while (iterator.hasNext()) {
            Integer number = iterator.next();
            // Якщо число ділиться на 3 без залишку, видаляємо його
            if (number % 3 == 0) {
                iterator.remove();
            }
        }

        System.out.println("Список после удаления: " + numbers);
    }
}
