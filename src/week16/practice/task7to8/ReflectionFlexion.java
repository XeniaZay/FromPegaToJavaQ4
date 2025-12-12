package week16.practice.task7to8;

import week16.practice.task1to6.TaskNumber;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionFlexion {

    @TaskNumber(7)
    static Object createArrayByParams(Class<?> componentType, int n){
       // Class<?> cl = componentType.arrayType();
        Object array = Array.newInstance(componentType, n);
        return array;
    }

    static void testTask7(){
        Integer[] arr = (Integer[]) createArrayByParams(Integer.class, 5);
        System.out.println(java.util.Arrays.toString(arr));
    }

    @TaskNumber(8)
    static void getGenericsInfo(Class<?> componentType) {
        //Class clazz = Class.forName(clName);
        System.out.println("Superclass" + " " + componentType.getGenericSuperclass());
        System.out.println("Interfaces" + " " + Arrays.toString(componentType.getGenericInterfaces()));

    }

    static void testTask8(){
        getGenericsInfo(Integer.class);
    }




    public static void main(String[] args) {
        //testTask7();
        testTask8();

    }
}
