package week13.theory.hashMap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExercise {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Create and start the writer threads
        Thread writerThread1 = new Thread(new Writer(map, "Thread-1", 1));
        Thread writerThread2 = new Thread(new Writer(map, "Thread-2", 2));
        writerThread1.start();
        writerThread2.start();

        // Create and start the reader threads
        Thread readerThread1 = new Thread(new Reader(map, "Thread-1"));
        Thread readerThread2 = new Thread(new Reader(map, "Thread-2"));
        readerThread1.start();
        readerThread2.start();
    }
}
