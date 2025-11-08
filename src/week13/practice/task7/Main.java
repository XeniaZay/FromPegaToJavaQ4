package week13.practice.task7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int CAPACITY = 3;
    private static volatile boolean producingFinished = false;
    private static final AtomicInteger totalSum = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException{
        int N = 10;

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Продюсер
        executor.execute(() -> {
            try {
                for (int i = 1; i <= N; i++) {
                    queue.put(i);
                    System.out.println("produced=" + i);
                    Thread.sleep(100); // Задержка 100 мс
                }
                producingFinished = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Консьюмер
        executor.execute(() -> {
            try {
                int localSum = 0;
                while (!producingFinished || !queue.isEmpty()) {
                    Integer value = queue.poll(100, TimeUnit.MILLISECONDS);
                    if (value != null) {
                        localSum += value;
                        totalSum.set(localSum);
                        System.out.println("consumed=" + value);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Завершение работы
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Итоговая сумма: " + totalSum.get());
    }
}
