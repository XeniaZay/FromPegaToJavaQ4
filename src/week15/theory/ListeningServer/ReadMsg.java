package week15.theory.ListeningServer;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadMsg extends Thread {
    private BufferedReader in;
    ReadMsg(BufferedReader in){
        this.in = in;
    }
    @Override
    public void run() {

        String str;
        try {
            while (true) {
                str = in.readLine(); // ждем сообщения с сервера
                if (str.equals("stop")) {

                    break; // выходим из цикла если пришло "stop"
                }
            }
        } catch (IOException e) {

        }
    }
}
