import java.util.Scanner;
import java.math.BigInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
// Валідні дані
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        // Помилка розміру
        String[][] invalidSizeArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };

        //Помилка даних
        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "trim", "8"},
                {"9", "10", "1w", "12"},
                {"13", "14", "!!!", "16"}
        };

        ArrayValueCalculator calculator = new ArrayValueCalculator();
        System.out.println("\nЗагальна сума масива validArray: ");
        calculator.testCalculator(validArray);
        System.out.println("\nЗагальна сума масива invalidSizeArray: ");
        calculator.testCalculator(invalidSizeArray);
        System.out.println("\nЗагальна сума масива invalidDataArray: ");
        calculator.testCalculator(invalidDataArray);

        //Перевірка колекцій
        CollectionList list = new MyCollectionList();

        // Додавання елементів
        list.add("BMW");
        list.add("AUDI");
        list.add("Opel");

        System.out.println("Початкова колекція: " + list);
        System.out.println("Розмір: " + list.size());

        // Додавання за індексом
        list.add(1, "Toyota");

        System.out.println("Після add(1, Toyota): " + list);

        // Заміна елемента
        list.set(0, "Mercedes");

        System.out.println("Після set(0, Mercedes): " + list);

        // Отримання елемента
        System.out.println("Елемент з індексом 2: " + list.get(2));

        // Перевірка наявності
        System.out.println("Є BMW: " + list.contain("BMW"));

        // Видалення
        list.delete("AUDI");

        System.out.println("Після видалення AUDI: " + list);

        // Перевірка розширення масиву
        for (int i = 1; i <= 10; i++) {
            list.add("Car" + i);
        }

        System.out.println("Після додавання 10 елементів: " + list);
        System.out.println("Розмір: " + list.size());

        // Порівняння колекцій
        CollectionList secondList = new MyCollectionList();

        secondList.add("BMW");
        secondList.add("AUDI");
        secondList.add("Opel");
        System.out.println("Колекції однакові: " + list.equals(secondList));

        // Очищення
        list.clear();

        System.out.println("Після очищення: " + list);
        System.out.println("Розмір: " + list.size());

        //Captcha
        ASCIIArtNumbers DIGITS = new ASCIIArtNumbers();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число: ");
        String input = scanner.nextLine().trim();

        if (ASCIIArtNumbers.isValid(input)) {

            String cleanNumber = new BigInteger(input).toString();
            System.out.println("Малюємо число: " + cleanNumber + "\n");

            // Звертаємось до методу малювання
            ASCIIArtNumbers.drawNumber(cleanNumber);

        } else {
            System.out.println("Помилка: Введений текст не є цілим додатним числом!");
        }

        scanner.close();
    }

}