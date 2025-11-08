package week13.practice.task1;

public class ThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new MyThread("Thr-A");

        Thread t2 = new Thread(new MyRunnable());
        t2.setName("Thr-B");

        Thread t3 = new Thread(() ->
        {
            for(int i = 0; i < 5; i++)
            {
            try{
                Thread.sleep(500);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

                System.out.println(Thread.currentThread().getName() + ": message " + i);
            }
        });
        t3.setName("Thr-C");

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join(); //приостанавливает выполнение main потока до тех пор, пока поток t1 не завершится
            t2.join();
            t3.join();
        }catch(InterruptedException e){}

    }
}
