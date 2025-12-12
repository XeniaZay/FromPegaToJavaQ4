package week16.theory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClass {
    private int number;
    private String name = "default";

    public MyClass(int number, String name) {
        this.number = number;
        this.name = name;
    }
    public MyClass(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    private void printData(){
        System.out.println(number + name);
    }
    public static void printData(Object myClass){
        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void printString(String string){
        System.out.println(string);
    }
    public static void printString(Object myClass){
        try {

            Method[] allMethods = myClass.getClass().getDeclaredMethods();
            for (Method meth : allMethods) {
                Class[] paramTypes = meth.getParameterTypes();
                for (Class paramType : paramTypes) {
                    System.out.print(paramType.getName() + " ");
                }
                System.out.println();
            }

            Method method = myClass.getClass().getDeclaredMethod("printString", String.class);
            method.setAccessible(true);
            method.invoke(myClass,"ss");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
