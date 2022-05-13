package ru.bainc.main;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalisatorService {
    public static Map<String, Integer> analizator (Path path) throws IOException {
        List<String> words = new ArrayList<>();
   //     words = Collections.singletonList(FileUtils.readFileToString(new File(path.toString()), StandardCharsets.UTF_8.toString()));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader((path.toString())))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words.stream().parallel()
                .flatMap(line -> Stream.of(line.split("\\s+"))) // разбиваем строку на слова по пробелу 1 и более
                .map(word -> word.replaceAll("[^А-Яа-яЁё]+", "") // выкидываем небуквенные символы
                        .toLowerCase()) //   делаем все буквы маленькие
                .filter(word -> word.length() > 2) // оставляем слова длиной 3 и больше букв
                .collect(Collectors.toMap(word-> word,value->1,(numberRepet1,numberRepet2)->Integer.sum(numberRepet1,numberRepet2)));
    }


}

