package phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory {
    private final List<Record> records = new ArrayList<>();

    public void add (Record record){
        records.add(record);
    }
    public Record findName (String name) {
        for (Record record : records){
            if (record.getName().equalsIgnoreCase(name)){
                return record;
            }
        }
        return null;
    }

    public List<Record> findAll(String name) {
        List<Record> result = new ArrayList<>();
        for (Record record : records) {
            if (record.getName().equalsIgnoreCase(name)) {
                result.add(record);
            }
        }
        return result.isEmpty() ? null : result;
    }
    public void printAll(String name) {
        List<Record> allRecords = findAll(name);

        System.out.println("\nУсі записи для " + name + ":");

        if (allRecords != null) {
            for (Record item : allRecords) {
                System.out.println(item);
            }
        } else {
            System.out.println("Записи не знайдені");
        }
    }
}
