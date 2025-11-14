package week14.theory.io;
import java.io.File;

public class TryFilePath {
    public static void main(String[] args) {
        //какой-то текущий файл
        File originalFile = new File("C:/Users/U_M1T5P/IntelliJ IDEA/FromPgToJvQ3/notes.txt");
        //System.out.println(originalFile.isDirectory());

//объект-директория
        File folder = originalFile.getParentFile();

//печать списка файлов на экран
        for (File file : folder.listFiles())
        {
            System.out.println(file.getName());
        }
    }
}
