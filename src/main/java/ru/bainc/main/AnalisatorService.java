package ru.bainc.main;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalisatorService {

    public static List<String> readFromFile(Path path) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                bufferedReader.close();
            }
        return words;
    }

    public static Map<String, Integer> uniqueWords(List<String> stringList) {
        return stringList.stream()
                .flatMap(line -> Stream.of(line.split("\\s+"))) // разбиваем строку на слова
                .map(word -> word.replaceAll("[^A-Za-zА-Яа-яЁё]+", "") // выкидываем небуквенные символы
                .toLowerCase()) //   делаем все буквы маленькие
                .filter(word -> word.length() > 3) // оставляем слова длиной 3 и больше букв
                .collect(Collectors.toMap(word-> word,value->1,(numberRepet1,numberRepet2)->Integer.sum(numberRepet1,numberRepet2)));
    }

    public static void numberOfWords  (Map<String, Integer> mapWords, String word){
        if(mapWords.containsKey(word)){
            System.out.println(mapWords.get(word));
        } else System.out.println("Такого слова нет");
    }
}
