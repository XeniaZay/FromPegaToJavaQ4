package week14.practice.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new FileReader("C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task1/input.txt"));
            FileWriter writer = new FileWriter("C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task1/output.txt", false)) {
            //чтение построчно
            String s;
            while((s=br.readLine())!=null){
                writer.write(s);
                writer.append('\n');
            }
        } catch(IOException ex){System.out.println(ex.getMessage());}

    }
}
