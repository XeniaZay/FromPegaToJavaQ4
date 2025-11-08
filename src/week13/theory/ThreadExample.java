package week13.theory;

public class ThreadExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            Thread thread = new Thread(() -> {
                System.out.println("Thread " + taskNumber + " is running.");
            });
            thread.start();
        }
    }
}
