public class ArrayValueCalculator {
    public int doCalc(String[][] array)
            throws ArraySizeException, ArrayDataException {

        // Перевірка розміру масиву
        if (array == null || array.length != 4) {
            throw new ArraySizeException(
                    "Масив повинен мати розмір 4х4!"
            );
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new ArraySizeException(
                        "Рядок " + (i + 1) + " повинен містити 4 елементи!");
            }
        }

        int sum = 0;

        StringBuilder errors = new StringBuilder();
        NumberFormatException firstException = null;

        // Перевірка всіх елементів масиву
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                try {
                    sum += Integer.parseInt(array[i][j]);

                } catch (NumberFormatException e) {

                    if (firstException == null) {
                        firstException = e;
                    }

                    errors.append("Комірка [")
                            .append(i)
                            .append("][")
                            .append(j)
                            .append("]: ")
                            .append(array[i][j])
                            .append("\n");
                }
            }
        }

        if (firstException != null) {
            throw new ArrayDataException(
                    "Знайдено некоректні дані:\n" + errors,
                    firstException
            );
        }

        return sum;
    }
    // Тестування калькулятора
    public void testCalculator(String[][] array) {
        try {
            int result = doCalc(array);
            System.out.println("Результат обчислення: " + result);
        } catch (ArraySizeException | ArrayDataException e) {
            System.out.println("Спіймано виняток: " + e.getMessage());
            // Демонстрація виконання пунктів 7 та 8 (виведення першопричини)
            if (e.getCause() != null) {
                System.out.println("Оригінальна причина: " + e.getCause().toString());
            }
        }
    }
}
