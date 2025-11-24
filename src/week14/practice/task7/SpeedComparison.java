package week14.practice.task7;

import java.io.*;

public class SpeedComparison {

    public static long copyUnbuffered(String src, String dst){
        long startTime = System.nanoTime();

        try(InputStream input = new FileInputStream(src);
            FileOutputStream output = new FileOutputStream(dst)){
            int c;
            while((c = input.read())!=-1){
                output.write(c);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    public static long copyBuffered(String src, String dst){
        long startTime = System.nanoTime();

        try(BufferedInputStream input = new BufferedInputStream(new FileInputStream(src));
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(dst))){
            int c;
            while((c = input.read())!=-1){
                output.write(c);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;

    }

    public static void main(String[] args) {
        String sourceFile = "C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task7/video_2025-11-21_11-42-54.mp4";
        String unbufferedDest = "C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task7/unbuffered_copy.txt";
        String bufferedDest = "C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task7/buffered_copy.txt";

        // Копирование без буферизации (чтение по 1 байту)
        System.out.println("Копирование без буферизации...");
        long unbufferedTime = copyUnbuffered(sourceFile, unbufferedDest);
        System.out.println(unbufferedTime);

        System.out.println("Копирование с буферизацией...%n");
        long bufferedTime = copyBuffered(sourceFile, bufferedDest);
        System.out.println(bufferedTime);

        //Сравнение производительности
        double speedup = (double) unbufferedTime / bufferedTime;
        System.out.printf("Ускорение в %.1f раз%n%n", speedup);
    }
}
