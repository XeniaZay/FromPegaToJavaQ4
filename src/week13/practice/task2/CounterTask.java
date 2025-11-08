package week13.practice.task2;

public class CounterTask implements Runnable{
    static int counter = 0;
    @Override
    public void run(){
        for(int i=0; i<1000; i++){
            synchronized(CounterTask.class) {
                counter++;
            }
        }
    }
}
