package week16.theory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static week16.theory.MyClass.printData;
import static week16.theory.MyClass.printString;

public class Main {
    public static void main(String[] args) {
        // без конструктора
//        MyClass myClass = new MyClass();
//        int number = myClass.getNumber();
//        String name = null; //no getter =(
//        System.out.println(number + name);//output 0null
//        try {
//            Field field = myClass.getClass().getDeclaredField("name");
//            field.setAccessible(true);
//            field.set(myClass, (String) "new value");
//            name = (String) field.get(myClass);
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(number + name);//output 0default
//        printData(myClass);
//        System.out.println(MyClass.class.getName());

        //Создадим экземпляр класса с помощью рефлексии
//        MyClass myClass2 = null;
//        try {
//            Class clazz = Class.forName(MyClass.class.getName());
//            myClass2 = (MyClass) clazz.newInstance();
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.println(myClass2);//output created object reflection.MyClass@60e53b93


        // с конструктором
        MyClass myClass = null;
        //получение параметров конструктора
        try {
            Class clazz = Class.forName(MyClass.class.getName());
            Constructor[] constructors = clazz.getConstructors();
            for (Constructor constructor : constructors) {
                Class[] paramTypes = constructor.getParameterTypes();
                for (Class paramType : paramTypes) {
                    System.out.print(paramType.getName() + " ");
                }
                System.out.println();
            }
            Class[] params = {int.class, String.class};
            myClass = (MyClass) clazz.getConstructor(params).newInstance(1, "default2");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(myClass);//output created object reflection.MyClass@60e53b93

        printString(myClass);
    }
}
