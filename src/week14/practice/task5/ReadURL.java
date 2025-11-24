package week14.practice.task5;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadURL {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://example.com").openStream()));
             FileWriter writer = new FileWriter("example.txt", false)) {
            String s;
            while((s=br.readLine())!=null){
                writer.write(s);
                writer.flush();
            }
        } catch(IOException ex){System.out.println(ex.getMessage());}
    }
}
