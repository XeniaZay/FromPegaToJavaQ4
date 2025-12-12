package week16.theory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CodeAnalyzer {

    public static Cat createCat() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String className = reader.readLine();

        Class clazz = Class.forName(className);
        Cat cat = (Cat) clazz.newInstance();

        return cat;
    }
    public static Cat createCatWithParams()  {

        Class clazz = null;
        Cat cat = null;

        try {
            clazz = Class.forName("week16.theory.Cat");
            Class[] catClassParams = {String.class, int.class};
            cat = (Cat) clazz.getConstructor(catClassParams).newInstance("Barsik", 6);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return cat;
    }
    public static Cat createCatWithSetter()  {

        Class clazz = null;
        Cat cat = null;
        try {
            clazz = Class.forName("week16.theory.Cat");
            cat = (Cat) clazz.newInstance();

            //с полем name нам повезло - для него в классе есть setter
            cat.setName("Barsik");

            Field age = clazz.getDeclaredField("age");

            age.setAccessible(true);

            age.set(cat, 6);

        } catch (IllegalAccessException | ClassNotFoundException | NoSuchFieldException | InstantiationException e) {
            e.printStackTrace();
        }

        return cat;
    }



    public static Object createObject() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String className = reader.readLine();

        Class clazz = Class.forName(className);
        Object result = clazz.newInstance();

        return result;
    }
    public static void invokeSayMeowMethod()  {

        Class clazz = null;
        Cat cat = null;
        try {

            cat = new Cat("Barsik", 6);

            clazz = Class.forName(Cat.class.getName());

            Method sayMeow = clazz.getDeclaredMethod("sayMeow");

            sayMeow.setAccessible(true);

            sayMeow.invoke(cat);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void analyzeClass(Object o) {

        Class clazz = o.getClass();
        System.out.println("Имя класса: " + clazz);
        System.out.println("Поля класса: " + Arrays.toString(clazz.getDeclaredFields()));
        System.out.println("Родительский класс: " + clazz.getSuperclass());
        System.out.println("Методы класса: " +  Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println("Конструкторы класса: " + Arrays.toString(clazz.getConstructors()));
    }


    public static void main(String[] args) throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //analyzeClass(new Cat("Barsik", 6));
        System.out.println(createCatWithSetter());
        //invokeSayMeowMethod();
    }
}
