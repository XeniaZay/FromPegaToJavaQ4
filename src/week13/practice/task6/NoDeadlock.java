package week13.practice.task6;

public class NoDeadlock {
    public static void lockInOrder(Object x, Object y) {
        Object first = x;
        Object second = y;

        // Сортируем по identityHashCode
        if (System.identityHashCode(x) > System.identityHashCode(y)) {
            first = y;
            second = x;
        }

        synchronized (first) {
            synchronized (second) {
                // Работа с ресурсами
            }
        }
    }

    public static void main(String[] args) {
        Object a = new Object(), b = new Object();

        Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                lockInOrder(a,b);
            System.out.println("T1 done");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            lockInOrder(b,a);
            System.out.println("T2 done");
        });

        t1.start();
        t2.start();

        try{
            t1.join(300); //приостанавливает выполнение main потока до тех пор, пока поток t1 не завершится
            t2.join(300);
        }catch(InterruptedException e){}

        // Проверяем, живые ли потоки
        if (t1.isAlive() || t2.isAlive()) {
            System.out.println("\n=== ДЕДЛОК ОБНАРУЖЕН! ===");
            System.out.println("T1 alive: " + t1.isAlive());
            System.out.println("T2 alive: " + t2.isAlive());
            System.out.println("Программа зависла в состоянии дедлока");
        } else {
            System.out.println("Оба потока завершились успешно");
        }
    }
}
