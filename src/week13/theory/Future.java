package week13.theory;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class Future {
    public static void main(String []args) throws Exception {
        Callable task = () -> {
            return "Hello, World!";
        };
        FutureTask<String> future = new FutureTask<>(task);
        new Thread(future).start();
        System.out.println(future.get());
    }
}
