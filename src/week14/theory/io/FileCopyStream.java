package week14.theory.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyStream {
    public static void main(String args[]) throws IOException {

        // Добавьте эти строки для отладки
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        System.out.println("File exists: " + new File("file.txt").exists());
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;

        try {
            fileIn = new FileInputStream("file.txt");
            fileOut = new FileOutputStream("copied_file.txt");

            int a;
            // Копирование содержимого файла file.txt
            while ((a = fileIn.read()) != -1) {
                fileOut.write(a); // Чтение содержимого файла file.txt и запись в файл copied_file.txt
            }
        }finally {
            if (fileIn != null) {
                fileIn.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }
}
