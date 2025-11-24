package week14.practice.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileStatsUsingStreamAPI {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите путь: ");
        String path = reader.readLine();
// C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task2/test.txt
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            for (String line : lines){
                Long counter = Arrays.stream(line.split("[^a-zA-Zа-яА-ЯёЁ0-9_]+")).filter(word -> !word.isEmpty()).count();
                System.out.println(counter);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            reader.close();
        }
    }
}