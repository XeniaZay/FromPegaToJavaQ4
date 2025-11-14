package week14.theory.io;
import java.io.*;

public class TestByteStream {
    public static void main(String args[])throws IOException {
        ByteArrayOutputStream outputByte = new ByteArrayOutputStream(12);

        while(outputByte.size()!= 5) {
            outputByte.write("hello".getBytes());
        }
        byte a [] = outputByte.toByteArray();
        System.out.println("Вывод содержимого:");

        for(int i = 0 ; i < a.length; i++) {
            // Вывод символов
            System.out.print((char)a[i] + " ");
        }
        System.out.println();

        int b;
        ByteArrayInputStream inputByte = new ByteArrayInputStream(a);
        System.out.println("Преобразование символов в верхний регистр:" );

        for(int j = 0 ; j < 1; j++) {
            while(( b = inputByte.read())!= -1) {
                System.out.println(Character.toUpperCase((char)b));
            }
            inputByte.reset();
        }
    }
}
