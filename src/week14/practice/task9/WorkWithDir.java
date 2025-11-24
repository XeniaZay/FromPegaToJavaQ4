package week14.practice.task9;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkWithDir {
    public static void scan(Path root, String glob, long minBytes){
        //PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        try {
            Files.walk(root)
                    .filter(Files::isRegularFile)
                    .filter(file -> {
                        String filename = file.getFileName().toString();
                        return filename.endsWith(glob.startsWith("*.") ? glob.substring(1) : glob);
                    })
                    .filter(file -> {
                        try {
                            return Files.size(file) >= minBytes;
                        } catch (IOException e) {
                            System.out.printf("Невозможно получить размер файла %s%n%s%n", file, e.getMessage());
                            return false;
                        }
                    })
                    .sorted((p1, p2) -> {
                        try {
                            return Long.compare(Files.size(p2), Files.size(p1)); // по убыванию
                        } catch (IOException e) {
                            return 0;
                        }
                    })
                    .limit(5)
                    .forEach(System.out::println);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Path root = Paths.get(".").toAbsolutePath().normalize();
        System.out.println("Поиск в директории: " + root);
        scan(root,"*.txt",1024);

    }
}
