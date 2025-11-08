package week13.practice.task5;

public class Main {
    public static void main(String[] args) {
        Worker wker = new Worker();
        wker.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        wker.stop();
        try {
            wker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main finished");
    }
}
