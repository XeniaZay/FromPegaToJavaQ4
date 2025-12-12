package week16.practice.task1to6;

import week16.theory.Cat;

import java.lang.reflect.*;
import java.util.Arrays;

//@Deprecated
public class ClassInspector {

    @TaskNumber(2)
    static void setField(Object target, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class cl = target.getClass();
        Field fl = cl.getDeclaredField(fieldName);
        fl.setAccessible(true);
        fl.set(target,value);
    }

    @TaskNumber(3)
    static void invoke(Object target, String methodName, Object... args){
        Class cl = target.getClass();
        Class<?>[] types = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = (args[i] != null) ? args[i].getClass() : Object.class;
        }
        try {
            Method mth = cl.getDeclaredMethod(methodName,types);
            mth.setAccessible(true);
            mth.invoke(target,args);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @TaskNumber(4)
    static void newInstance(String fqcn) {
        try {
            Class clazz = Class.forName(fqcn);
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Cat cat = (Cat) constructor.newInstance();
            System.out.println(cat);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            System.out.println("No default constructor");
        }

    }

    @TaskNumber(5)
    static void printAnnotations(String className){
        try {
            Class clazz = Class.forName(className);
            java.lang.annotation.Annotation[] annotations = clazz.getAnnotationsByType(Deprecated.class);
            System.out.println(Arrays.toString(annotations));
        } catch (ClassNotFoundException | NullPointerException | ClassCastException e){
            e.printStackTrace();
        }

    }

    @TaskNumber(6)
    static void printMethodsByParams(String className){
        try {
            Class clazz = Class.forName(className);
            Method[] merthods = clazz.getDeclaredMethods();
            for (Method m : merthods){
               if (m.getModifiers() == 9 && m.getParameterCount() == 0) // модификатор = 9 (public static) и без параметров
                System.out.println(m);
            }
        } catch (ClassNotFoundException | NullPointerException | ClassCastException e){
            e.printStackTrace();
        }

    }

    @Deprecated
    static void testTask1 (String className) throws ClassNotFoundException {
        Class<?> myClassClass = Class.forName(className);
        Field[] fields = myClassClass.getDeclaredFields();
        Method[] merthods = myClassClass.getDeclaredMethods();
        Constructor[] constructors = myClassClass.getConstructors();
        System.out.print("Fields: ");
        for (Field f : fields) {
            System.out.print(f + " ");
        }
        System.out.println();
        System.out.print("Methods: ");
        for (Method m : merthods) {
            System.out.print(m + " ");
        }
        System.out.println();
        System.out.print("Constructors: ");
        for (Constructor c : constructors) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    static void testTask2 (String className) throws NoSuchFieldException, IllegalAccessException {
        Cat cat = createCatWithParams(className);
        System.out.println(cat);
        setField(cat, "name", "Brsk");
        System.out.println(cat);
    }

    static void testTask3 (String className) throws NoSuchFieldException, IllegalAccessException {
        Cat cat = createCatWithParams(className);
        System.out.println(cat);
        invoke(cat, "setName", "Anna");
        System.out.println(cat);
    }

    static void testTask4 (String className) throws NoSuchFieldException, IllegalAccessException {
        newInstance(className);
    }

    static void testTask5 (String className) throws NoSuchFieldException, IllegalAccessException {
        printAnnotations(className);
    }
    static void testTask6 (String className) throws NoSuchFieldException, IllegalAccessException {
        printMethodsByParams(className);
    }


    public static Cat createCatWithParams (String className){

        Class clazz = null;
        Cat cat = null;

        try {
            clazz = Class.forName(className);
            Class[] catClassParams = {String.class, int.class};
            cat = (Cat) clazz.getConstructor(catClassParams).newInstance("Barsik", 6);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        return cat;
    }

    public static void main (String[]args) throws
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        String className = "week16.theory.Cat";
        //testTask1("week16.practice.task1to6.ClassInspector");
        //testTask2(className);
        //testTask3(className);
        //testTask4(className);
        //testTask5("week16.practice.task1to6.ClassInspector");
        testTask6(className);


    }

}
