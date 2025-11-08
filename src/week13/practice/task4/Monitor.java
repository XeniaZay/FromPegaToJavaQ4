package week13.practice.task4;

public class Monitor {
    public int sharedData;
    private boolean dataReady = false; // флаг готовности данных

    public synchronized void putData(int data) {
        // Ждем, пока потребитель заберет предыдущие данные
        while (dataReady) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        this.sharedData = data;
        dataReady = true;
        System.out.println("Producer put: " + data);
        notify(); // будим потребителя
    }

    public synchronized void getData() {
        // Ждем, пока производитель положит данные
        while (!dataReady) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        System.out.println("Consumer got: " + this.sharedData);
        dataReady = false;
        notify(); // будим производителя
    }
}

