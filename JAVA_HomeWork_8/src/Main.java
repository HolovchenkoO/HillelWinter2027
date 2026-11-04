import java.util.*;

import phonebook.PhoneDirectory;
import phonebook.Record;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //1. Створити метод countOccurance
    public static int countOccurance(List<String> list, String cntWord){
        int count = 0;
        for(String s: list){
            if (s.equals(cntWord)){
                count++;
            }
        }
        return count;
    }
    //2. Створити метод toList
    public static List<String> toList (String[] array){
        List<String> result = new ArrayList<>();
        for (String item : array){
            result.add(item);
        }
        return result;
    }
    //3. Створити метод findUnique
    public static List<Integer> findUnique(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new LinkedHashSet<>(numbers);

        return new ArrayList<>(uniqueNumbers);
    }
    //4. ** Створити метод calcOccurance
    public static void calcOccurance(List<String>words){
        Map<String, Integer> occurrences = new LinkedHashMap<>();
        for (String word : words) {
            occurrences.put(word, occurrences.getOrDefault(word, 0) + 1);
        }
            for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
           }
         }

    //5. *** Створити метод findOccurance
    public static List<WordOccurrence> findOccurance(List<String>words){
        Map<String, Integer> occurrences = new LinkedHashMap<>();
        for (String word : words) {
            occurrences.put(word, occurrences.getOrDefault(word, 0) + 1);
        }
        List<WordOccurrence> resultWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            resultWords.add(new WordOccurrence(entry.getKey(), entry.getValue()));
        }
        return resultWords;
    }




    public static void main(String[] args) {

        //Перевірка countOccurance
        List <String> testWords = Arrays.asList("red", "blue", "green", "green");
        System.out.println("Кількість слова 'green' в списку testWords: "
                +  countOccurance(testWords, "green"));

        //Перевірка toList
        String [] arrayTest = {"1", "one", "test"};
        List<String> newList = toList(arrayTest);
        System.out.println(newList);

        //Перевірка findUnique
        List<Integer> numbers = Arrays.asList(1,5,2,4,4,2,3,5,1);
        System.out.println("Унікальний числовий список: " + findUnique(numbers));

        //Перевірка calcOccurance
        List<String> words = Arrays.asList("bird", "fox", "cat", "bird", "dog", "horse",
                "bird", "cat", "rat");
        System.out.println("Кількість слов у списку: ");
        calcOccurance(words);

        //Перевірка findOccurance
        List<String> resultWords = Arrays.asList("bird", "fox", "cat", "bird", "dog", "horse",
                "bird", "cat", "rat");
        List<WordOccurrence> result = findOccurance(resultWords);
        System.out.println("Кількість слов у списку метод findOccurance: ");
        System.out.println("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print("    " + result.get(i));

            if (i < result.size() - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }
        System.out.println("]");

        //Телефонний довідник
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        phoneDirectory.add(new Record("Степурко", "0501112233"));
        phoneDirectory.add(new Record("Головченко", "0672223344"));
        phoneDirectory.add(new Record("Поцелуйко", "0933334455"));
        phoneDirectory.add(new Record("Вернигора", "0954445566"));
        phoneDirectory.add(new Record("Трамп", "0995556677"));
        phoneDirectory.add(new Record("Степурко", "0638882233"));

        Record record = phoneDirectory.findName("Степурко");
        System.out.println("Перший знайдений запис:");
        System.out.println(record);

        // Пошук усіх записів
        List<Record> allRecords = phoneDirectory.findAll("Степурко");

        System.out.println("\nУсі записи:");
        phoneDirectory.printAll("Степурко");

        // Пошук неіснуючого запису
        System.out.println("\nПошук відсутнього запису:");
        System.out.println(phoneDirectory.findName("Коваленко"));
    }
}