package week14.theory.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyReaderWriter {
    public static void main(String args[]) throws IOException {
        FileReader fileIn = null;
        FileWriter fileOut = null;

        try {
            fileIn = new FileReader("file.txt");
            fileOut = new FileWriter("copied_file2.txt");

            int a;
            while ((a = fileIn.read()) != -1) {
                fileOut.write(a);
            }
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }
}
