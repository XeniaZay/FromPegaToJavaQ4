package week13.theory;

public class SynchronizationExample {
    private static int counter = 0;

    public static synchronized void incrementCounter() {
        counter++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementCounter();
                }
            });
            thread.start();
        }

        // Ждем завершения всех потоков
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter);
    }
}
