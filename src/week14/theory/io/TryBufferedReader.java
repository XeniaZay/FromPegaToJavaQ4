package week14.theory.io;
import java.io.*;

public class TryBufferedReader {

    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader (new FileReader("notes4.txt"))) {
        // чтение посимвольно
            int c;
            while((c=br.read())!=-1){
                System.out.print((char)c);
            }
        } catch(IOException ex){System.out.println(ex.getMessage());}

        try(BufferedReader br = new BufferedReader(new FileReader("notes5.txt"))) {
        //чтение построчно
            String s;
            while((s=br.readLine())!=null){

            System.out.println(s);
            }
        } catch(IOException ex){System.out.println(ex.getMessage());}
    }
}
