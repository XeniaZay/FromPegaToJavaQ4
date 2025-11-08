package week13.practice.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {
    public static void main(String[] args){

        // Засекаем время начала
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<Integer>> tasks = new ArrayList<>();

        for(int i=1;i<=5;i++){
            int number = i;
            tasks.add(() -> {
                // имитируем длительную работу
                Thread.sleep(1000);
                return number * number;
            });
        }

        try {
            List<Future<Integer>> results = executor.invokeAll(tasks);
            for (Future<Integer> future : results) {
                try {
                    Integer result = future.get();
                    System.out.println("Полученный результат: " + result);
                } catch (ExecutionException e) {
                    System.out.println("Ошибка при выполнении задачи: " + e.getMessage());
                    e.printStackTrace();
                } catch (CancellationException e) {
                    System.out.println("Задача была отменена: " + e.getMessage());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        // Засекаем время окончания и выводим разницу
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Время выполнения программы: " + duration + " мс");
    }
}
