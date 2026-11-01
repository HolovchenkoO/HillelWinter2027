import java.util.Collection;
import java.util.Objects;

public class MyCollectionList implements CollectionList{
    private String[] strArray = new String[10];
    private int size = 0;

    // Додавання елемента в кінець колекції
    @Override
    public boolean add(String o) {
        if (size == strArray.length) {
            increaseArray();
        }

        strArray[size] = o;
        size++;

        return true;
    }

    // Додавання елемента за індексом
    @Override
    public boolean add(int index, String o) {
        if (index < 0 || index > size) {
            return false;
        }

        if (size == strArray.length) {
            increaseArray();
        }

        // Зсуваємо елементи вправо
        for (int i = size; i > index; i--) {
            strArray[i] = strArray[i - 1];
        }

        strArray[index] = o;
        size++;

        return true;
    }

    // Заміна елемента за індексом
    @Override
    public boolean set(int index, String o) {
        if (index < 0 || index >= size) {
            return false;
        }

        strArray[index] = o;

        return true;
    }

    // Видалення першого знайденого елемента
    @Override
    public boolean delete(String o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(strArray[i], o)) {

                // Зсуваємо всі наступні елементи вліво
                for (int j = i; j < size - 1; j++) {
                    strArray[j] = strArray[j + 1];
                }

                strArray[size - 1] = null;
                size--;

                return true;
            }
        }

        return false;
    }

    // Отримання елемента за індексом
    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return strArray[index];
    }

    // Перевірка наявності елемента
    @Override
    public boolean contain(String o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(strArray[i], o)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(CollectionList collection) {
        if (collection == null || size != collection.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(strArray[i], collection.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Очищення колекції
    @Override
    public boolean clear() {
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            strArray[i] = null;
        }
        size = 0;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseArray() {
        String[] newArray = new String[strArray.length * 2];

       for (int i = 0; i < size; i++) {
            newArray[i] = strArray[i];
        }

        strArray = newArray;
    }

    // Виведення колекції
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            result.append(strArray[i]);

            if (i < size - 1) {
                result.append(", ");
            }
        }

        result.append("]");

        return result.toString();
    }
}
