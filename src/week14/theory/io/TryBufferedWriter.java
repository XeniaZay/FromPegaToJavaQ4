package week14.theory.io;
import java.io.*;

public class TryBufferedWriter {
    public static void main(String[] args) {

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("notes4.txt")))
        {
            String text = "Hello  World!\nHey! Teachers! Leave the kids alone.";
            bw.write(text);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
