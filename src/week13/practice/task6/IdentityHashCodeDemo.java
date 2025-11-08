package week13.practice.task6;

public class IdentityHashCodeDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = obj1; // та же ссылка

        System.out.println("obj1 identityHashCode: " + System.identityHashCode(obj1));
        System.out.println("obj2 identityHashCode: " + System.identityHashCode(obj2));
        System.out.println("obj3 identityHashCode: " + System.identityHashCode(obj3));
        System.out.println("obj1 == obj3: " + (obj1 == obj3));

        // Сравнение с обычным hashCode()
        System.out.println("\nobj1.hashCode(): " + obj1.hashCode());
        System.out.println("System.identityHashCode(obj1): " + System.identityHashCode(obj1));
    }
}
