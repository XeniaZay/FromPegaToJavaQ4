package week14.practice.task4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FilesDemo {
    public static void main(String[] args) {
        Path dirPath = Paths.get("testDir");
        try {
            // Проверяем, существует ли директория
            if (!Files.exists(dirPath)) {
                // Создаем директорию, если она не существует
                Files.createDirectory(dirPath);
                System.out.println("Директория 'testDir' создана.");
            } else {
                System.out.println("Директория 'testDir' уже существует.");
            }
        } catch (IOException ex) {
            System.out.println("Ошибка при создании директории: " + ex.getMessage());
            ex.printStackTrace();
        }
        Path filePath = Paths.get("testDir/hello.txt");
        String content = "Hello, NIO!";
        try{
            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));
        }catch (IOException ex) {
            System.out.println("Ошибка при записи" + ex.getMessage());
            ex.printStackTrace();
        }

        try{
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            System.out.println("Содержимое файла:");
            for (String line : lines) {
                System.out.println(line);
            }
        }catch (IOException ex) {
            System.out.println("Ошибка при чтении: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}