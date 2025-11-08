package week13.practice.task4;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        Monitor res = new Monitor();

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                res.putData(i);
            }
            System.out.println("Producer finished");
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                res.getData();
            }
            System.out.println("Consumer finished");
        });

        producer.start();
        consumer.start();
    }

}
