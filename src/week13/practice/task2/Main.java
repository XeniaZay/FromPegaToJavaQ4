package week13.practice.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++) {
//            final int taskNumber = i;
//            executor.execute(new CounterTask());
//        }
//
//
//        // Завершение работы пула потоков
//        executor.shutdown();
//        try {
//            // Ждем завершения всех задач (максимум 1 минуту)
//            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
//                System.out.println("Не все задачи завершились за отведенное время");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Создаем массив потоков
        Thread[] threads = new Thread[5];

        // Создаем и запускаем потоки
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new CounterTask());
            threads[i].start();
        }

        // Ожидаем завершения всех потоков
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Выводим результат
        System.out.println("Финальное значение счетчика: " + CounterTask.counter);
        System.out.println("Ожидаемое значение: " + (5 * 1000));
    }
}
