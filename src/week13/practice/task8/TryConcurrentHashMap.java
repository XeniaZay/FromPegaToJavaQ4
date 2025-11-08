package week13.practice.task8;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class TryConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException{

        String[] words = {"a", "b", "a", "c", "a", "b"};
        int N = 6;
        ConcurrentHashMap<String, Integer> freq = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(N);

        executor.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    freq.merge(words[i], 1, Integer::sum);
                }
        });

        executor.execute(() -> {
            for (int i = 3; i < words.length; i++) {
                freq.merge(words[i], 1, Integer::sum);
            }
        });

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        List<Map.Entry<String, Integer>> top3 = freq.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .limit(3)
                .toList();

        System.out.print("Топ-3: ");
        for (int i = 0; i < top3.size(); i++) {
            Map.Entry<String, Integer> entry = top3.get(i);
            System.out.print(entry.getKey() + "=" + entry.getValue());
            if (i < top3.size() - 1) System.out.print(", ");
        }
    }
}
