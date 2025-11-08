package week13.practice.task5;

public class Worker{
    private volatile boolean running;
    public Thread workerThread;

    public void start(){
        running = true;
        workerThread = new Thread(() -> {
            while (running){
                System.out.println("tick");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Worker interrupted, stopping...");
                    break;
                }
            }
        });
        workerThread.start();
        System.out.println("Worker started");
    }

    public void stop(){
            running = false;

    }

    public void join() throws InterruptedException {
        if (workerThread != null) {
            workerThread.join();
        }
    }
}



