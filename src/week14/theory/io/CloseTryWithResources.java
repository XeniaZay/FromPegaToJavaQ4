package week14.theory.io;
import java.io.*;

public class CloseTryWithResources {
    public static void main(String[] args) {

        try(FileInputStream fin = new FileInputStream("notes.txt"))
        {
            int i=-1;
            while((i = fin.read()) != -1){

                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage()); //(Не удается найти указанный файл)
        }


    }
}