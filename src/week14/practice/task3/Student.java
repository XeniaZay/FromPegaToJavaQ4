package week14.practice.task3;

import java.io.*;
import java.time.LocalDateTime;

public class Student implements Serializable{
    private String name;
    private int age;
    private transient String password;


    Student(String n, int a, String p){
        name = n;
        age = a;
        password = p;
    }
    public String getName() {return name;}
    public int getAge(){ return age;}
    public String getPassword(){ return password;}
    @Override
    public String toString(){
        return "(name = " + name+ ", age = " + age + ", password = " + password + ")";
        //System.out.printf("Person{name=%s, age=%d}", name, age);
    }
}