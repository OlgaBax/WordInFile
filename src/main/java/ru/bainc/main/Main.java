/* Написать программу, которая в аргумент командной строки получает
имя текстового файла и анализирует его содержимое по частоте употребляемых слов
(длинна слова д.б. не менее 3 символов). Написать 2 варианта: цикл и regex.
 Засекать время выполнения каждого варианта. Программа должа выполняться, пока не будет введено слово "end".
 Например: со сканера вводишь слово " любовь" и нужно вывести на экран сколько раз оно втречается в файле.
 */
package ru.bainc.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Введите слово");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String word = scanner.nextLine();
            AnalisatorService.numberOfWords(AnalisatorService.uniqueWords(AnalisatorService.readFromFile
                    (Paths.get (args[0]))), word);
            if (word.equals("/end"))
                break;
        }
        System.out.println("\nвремя  выполнения программы = "
                + (System.currentTimeMillis() - startTime)/1000 + " seconds ");
    }
}
