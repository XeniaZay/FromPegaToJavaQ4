package week13.practice.task1;

public class MyRunnable implements Runnable{
    @Override
    public void run(){
        for(int i = 0; i < 5; i++)
        {
            try{
                Thread.sleep(500);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

            System.out.println(Thread.currentThread().getName() + ": message " + i);
        }
    }
}
