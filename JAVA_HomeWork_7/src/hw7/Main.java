package hw7;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//завдання 2
 System.out.println("Кількість символів l: " +  findSymbolOccurance("HillelJava", 'l'));

 //завдання 3
    System.out.println("Позиція входження підрядка в рядок: " +
            findWordPosition("HillelJava", "Java"));
    System.out.println("Позиція входження підрядка в рядок: " +
                findWordPosition("HillelJava", "For"));

        //завдання 4
        System.out.println("Перевернута строка: " +
                stringReverse("JavaLesson"));

        //завдання 5
        System.out.println("Вираз паліндром: " +
                isPalindrome("A roza upala na lapu azora"));
        System.out.println("Вираз паліндром: " +
                isPalindrome("Tree"));

        //завдання 6 Гра Вгадай слово
        playWordGame();

    }

    /*2. Створити метод findSymbolOccurance. Метод приймає як параметр рядок і символ.
     Необхідно обчислити, скільки разів символ зустрічається в переданому рядку і
     повернути це числове значення.*/
    public static int findSymbolOccurance(String str, char symbol) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == symbol) {
                count++;
            }
        }
        return count;
    }

    /*3. Створити метод findWordPosition. Метод приймає як параметри два рядки
    (source, target). Необхідно з'ясувати, чи є target (підрядок) частиною рядка source.
     Якщо так, тоді повернути номер позиції (індекс) першого елемента підрядка у рядку,
     інакше повернути -1.*/
    public static int findWordPosition (String source, String target) {
        return source.indexOf(target);
    }

    /*4. Створити метод stringReverse. Метод приймає як параметр - рядок.
     Необхідно її розгорнути та повернути змінений варіант.*/
    public static String stringReverse(String str){
        StringBuilder sb = new StringBuilder(str);
                return sb.reverse().toString();
    }
    /*5. Створити метод isPalindrome. Метод приймає як параметр - рядок.
    Необхідно Перевірити, чи є переданий рядок паліндромом.
    Якщо так, тоді повернуть true, інакше false.*/
    public static boolean isPalindrome (String str) {
        String reversStr = str.replaceAll("\\s+", "").toLowerCase();
        String palindrome = new StringBuilder(reversStr).reverse().toString();
        return reversStr.equals(palindrome);
    }
    /*6. Гра вгадай слово*/
    public static void playWordGame() {
        String[] words = {
                "apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic",
                "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"
        };
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String secretWord = words[random.nextInt(words.length)];

        System.out.println("\nГра «Вгадай слово»!");
        System.out.println("Комп'ютер загадав слово (овоч або фрукт на англійській мові).");
        System.out.println("Спробуй його відгадати.");

        while (true) {
            System.out.print("Введи слово: ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals(secretWord)) {
                System.out.println("Вітаю! Ти вгадав слово: " + secretWord);
                break;
            }

            System.out.println("Неправильно. Спробуй ще раз.");
            System.out.println(showMatchingLetters(secretWord, answer));
        }
    }

    // Показуємо літери, які збігаються на тих самих позиціях
    public static String showMatchingLetters(String secretWord, String answer) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            if (i < secretWord.length()
                    && i < answer.length()
                    && secretWord.charAt(i) == answer.charAt(i)) {
                result.append(secretWord.charAt(i));
            } else {
                result.append('#');
            }
        }

        return result.toString();
    }
}