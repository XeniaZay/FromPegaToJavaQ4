package week13.theory.hashMap;

import java.util.concurrent.ConcurrentHashMap;

public class Reader implements Runnable {
    private ConcurrentHashMap< String, Integer > map;
    private String threadName;

    public Reader(ConcurrentHashMap < String, Integer > map, String threadName) {
        this.map = map;
        this.threadName = threadName;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            Integer value = map.get(threadName);
            System.out.println("Thread " + threadName + " read value: " + value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
