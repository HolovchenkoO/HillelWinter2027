public class HomeWorkApp  {

    public static void main(String[] args) {

        System.out.println("HomeWork 3");
        printThreeWords();
        System.out.println();

        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(checkSum(10, 10));
        printPositiveOrNegativeNumber(-9);
        System.out.println(isNegativeNumber (1));
        printLines("line", 7);
        System.out.println(isLeapYear(1900));
    }

   /* 2. Створіть метод printThreeWords(), який під час виклику повинен надрукувати
  в стовпець у три слова: Orange, Banana, Apple.
  */
 public static void printThreeWords() {
     System.out.println("Orange");
     System.out.println("Banana");
     System.out.println("Apple");
 }

    /* 3. Створіть метод checkSumSign(), у тілі якого оголосіть дві int змінні a і b,
     та ініціалізуйте їх будь-якими значеннями, якими захочете. Далі метод повинен
     підсумувати ці змінні, і якщо їх сума більша або дорівнює 0, то вивести в консоль
      повідомлення "Сума позитивна", інакше - "Сума анегативн";
      */

    public static void  checkSumSign() {
        int a = 10;
        int b = -9;
        if (a + b >=0) {
            System.out.println("Сума позитивна");
        }
        else {
            System.out.println("Сума негативн");
        }
    }

    /* 4. Створіть метод printColor() у тілі якого задайте int змінну value та ініціалізуйте
    її будь-яким значенням. Якщо значення менше 0 (0 включно), то в консоль метод повинен
     вивести повідомлення "Червоний", якщо лежить в межах від 0 (0 виключно) до 100
     (100 включно), то "Жовтий", якщо більше 100 (100 виключно) - "Зелений";
     */

    public static  void printColor() {
        int value = 123;

        if (value <= 0){
            System.out.println("Червоний");
        }
        else if (value <= 100) {
            System.out.println("Жовтий");
        }
            else {
                System.out.println("Зелений");
            }
        }

    /* 5. Порівнює два числа
    Створіть метод compareNumbers(), у тілі якого оголосіть дві int змінні a і b,
     та ініціалізуйте їх будь-якими значеннями, якими захочете. Якщо a більше або одно b,
      необхідно вивести в консоль повідомлення “a >= b”, інакше “a < b”;
     */

    public static void compareNumbers() {
        int a =7;
        int b =7;
        if (a >= b){
            System.out.println("a >= b");
        }
        else {
            System.out.println("a < b");
        }
}

     /* 6. Написати метод, який приймає на вхід два цілих числа і перевіряє,
     що їхня сума лежить в межах від 10 до 20 (включно), якщо так – повернути true,
      інакше – false.
     */
    public static boolean checkSum (int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

   /* 7. Написати метод, якому як параметр передається ціле число, метод повинен надрукувати
     в консоль, чи додатнє число передали або від’ємне. Примітка: нуль вважаємо
     позитивним числом.
     */

    public static void printPositiveOrNegativeNumber(int num){
        if (num >= 0) {
            System.out.println("Число додатне");
        }
            else {
                System.out.println("Число від'ємне");
                }
    }

    /* 8. Написати метод, якому як параметр передається ціле число. Метод повинен повернути
    true, якщо число є негативним, і повернути false якщо позитивне.
     */
    public static boolean isNegativeNumber (int num) {
        return num < 0;
    }

    /* 9. Написати метод, якому як аргументи передається рядок і число, метод повинен
    надрукувати в консоль зазначений рядок, вказану кількість разів;
    */
    public static void printLines(String line, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(line);
        }
    }

    /* 10. Написати метод, який визначає, чи є рік високосним, і повертає
     boolean (високосний – true, не високосний – false).
     Кожен 4-й рік є високосним, крім кожного 100-го, причому кожен 400-й – високосний.
         */
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

}
