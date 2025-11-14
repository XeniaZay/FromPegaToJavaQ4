package week14.theory.serialization;

import java.io.*;

public class Person implements Serializable {
    private String name;
    private int age;
    private double height;
    private boolean married;

    Person(String n, int a, double h, boolean m){

        name=n;
        age=a;
        height=h;
        married=m;
    }
    String getName() {return name;}
    int getAge(){ return age;}
    double getHeight(){return height;}
    boolean getMarried(){return married;}

    // Исключение данных из сериализации
//        private String name;
//        private int age;
//        private transient double height;
//        private transient boolean married;
//
//        Person(String n, int a, double h, boolean m){
//
//            name=n;
//            age=a;
//            height=h;
//            married=m;
//        }
//        String getName() {return name;}
//        int getAge(){ return age;}
//        double getHeight(){return height;}
//        boolean getMarried(){return married;}
}


