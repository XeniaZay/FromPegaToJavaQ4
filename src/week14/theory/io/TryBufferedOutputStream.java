package week14.theory.io;
import java.io.*;

public class TryBufferedOutputStream {
    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileOutputStream out = new FileOutputStream("task6.txt");
            BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            System.out.print("Введите количество N, затем N целых оценок (0–100) и строку-комментарий ");
            String info = reader.readLine();
            // перевод строки в байты
            byte[] buffer = info.getBytes();
            bos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileOutputStream out = new FileOutputStream("task6.txt");
            BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            System.out.print("Введите количество N, затем N целых оценок (0–100) и строку-комментарий ");
            String info = reader.readLine();
            // перевод строки в байты
            byte[] buffer = info.getBytes();
            bos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}