package week14.practice.task6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataInputOutputDemo {
    public int n;
    public List<Integer> grades;
    public String comment;

    DataInputOutputDemo(int n, List<Integer> grades, String comment){
        this.n = n;
        this.grades = grades;
        this.comment = comment;
    }

    public int getN(){return n;}
    public List<Integer> getGrades(){return grades;}
    public String getComment(){return comment;}

    public String toString(){
        return "N = " + n + "\n" + "grades = " + grades + "\n" + "comment = " + comment;
    }

    public static void writeGrades(DataInputOutputDemo obj, String fileName){
        try (FileOutputStream out = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {

            byte[] buffer = obj.toString().getBytes();
            bos.write(buffer, 0, buffer.length);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readGrades(String fileName) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            int c;
            while((c=bis.read())!=-1){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        DataInputOutputDemo example = new DataInputOutputDemo(5, Arrays.asList(70, 85, 90, 60, 100), "Midterm");
        //System.out.println(example);
        //System.out.println();
        writeGrades(example,"grades.bin");
        System.out.println("Файл записан");
        System.out.println();
        readGrades("grades.bin");
    }
}
