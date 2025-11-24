package week14.practice.task2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileStats {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите путь: ");
        String path = reader.readLine();
// C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task2/test.txt
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            //System.out.println(lines.size());
            for (String line : lines) {
                int counter = 0;
                String[] words = line.split("[^a-zA-Zа-яА-ЯёЁ0-9_]+");
                for (String word : words) {
                    counter++;
                    //System.out.println(word);
                }
                System.out.println(counter);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } finally {
            reader.close();
        }
    }
}


