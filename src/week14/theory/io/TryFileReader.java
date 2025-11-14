package week14.theory.io;
import java.io.*;
import java.util.Arrays;

public class TryFileReader {
    public static void main(String[] args) {

        try(FileReader reader = new FileReader("notes4.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        try(FileReader reader2 = new FileReader("notes3.txt"))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader2.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c); //Также мы можем считывать в промежуточный буфер из массива символов
                }
                System.out.print(buf);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}