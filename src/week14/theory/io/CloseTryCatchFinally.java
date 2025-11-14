package week14.theory.io;
import java.io.FileInputStream;
import java.io.IOException;

public class CloseTryCatchFinally {
    public static void main(String[] args) {

        FileInputStream fin=null;
        try
        {
            fin = new FileInputStream("notes.txt");

            int i=-1;
            while((i=fin.read())!=-1){

                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage()); //(Не удается найти указанный файл)
        }
        finally{

            try{

                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}