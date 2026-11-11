package fileNavigator;

import java.util.*;

public class FileNavigator {
    private Map<String, List<FileData>> files = new HashMap<>();

    //метод add
    public void add(String path, FileData fileData) {
        if(!path.equals(fileData.getPath())){
            System.out.println("Помилка!!! Шлях файлу не відповідає ключу(шлях-ключ): " + path);
            return;
        }
        if(files.containsKey(path)){
            files.get(path).add(fileData);
        }
        else{
        List<FileData> fileList = new ArrayList<>();
        fileList.add(fileData);
            files.put(path, fileList);
        }
    }
    // Реалізувати метод find
    public List<FileData> find(String path){
        return files.get(path);
    }

    //Реалізувати метод filterBySize
    public List<FileData> filterBySize(int maxSize){
        List<FileData> fileListRezult = new ArrayList<>();
        for(List<FileData> fileList : files.values()){
            for(FileData file : fileList){
                if(file.getSize() <= maxSize) {
                    fileListRezult.add(file);
                }
            }
        }
        return fileListRezult;
    }

    //Реалізувати метод remove
    public void remove(String path){
       files.remove(path);
    }

    //Реалізувати метод sortBySize
    public List<FileData> sortBySize(){
        List<FileData> result = new ArrayList<>();
        for (List<FileData> fileList : files.values()){
            result.addAll(fileList);
        }
        Collections.sort(result, new Comparator<FileData>(){
        @Override
        public int compare(FileData file1, FileData file2){
            return Integer.compare(file1.getSize(), file2.getSize());
        }
        });
        return result;
    }
}
