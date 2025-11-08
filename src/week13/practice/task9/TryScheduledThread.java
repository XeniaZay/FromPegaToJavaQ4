package week13.practice.task9;
import java.time.LocalTime;
import java.util.concurrent.*;

public class TryScheduledThread {
    public static void main(String[] args) throws InterruptedException{

        ScheduledExecutorService sch = Executors.newScheduledThreadPool(1);

        sch.scheduleAtFixedRate(() -> {
            System.out.println("RateStart: " + LocalTime.now());
            try { Thread.sleep(1500); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, 0, 1, TimeUnit.SECONDS);

        sch.scheduleWithFixedDelay(() -> {
            try {Thread.sleep(1500); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
            System.out.println("DelayEnd:  " + LocalTime.now());
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(8000);
        sch.shutdownNow();
    }
}
