package week13.theory.hashMap;

import java.util.concurrent.ConcurrentHashMap;

public class Writer implements Runnable {
    private ConcurrentHashMap< String, Integer > map;
    private String threadName;
    private int value;

    public Writer(ConcurrentHashMap < String, Integer > map, String threadName, int value) {
        this.map = map;
        this.threadName = threadName;
        this.value = value;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            map.put(threadName, value);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

