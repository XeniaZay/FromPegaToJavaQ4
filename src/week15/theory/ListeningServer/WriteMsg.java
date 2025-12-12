package week15.theory.ListeningServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class WriteMsg extends Thread {
    private BufferedWriter out;
    private BufferedReader inputUser;
    WriteMsg(BufferedWriter out, BufferedReader inputUser){
        this.out = out;
        this.inputUser = inputUser;
    }
    @Override
    public void run() {
        while (true) {
            String userWord;
            try {
                userWord = inputUser.readLine(); // сообщения с консоли
                if (userWord.equals("stop")) {
                    out.write("stop" + "\n");
                    break; // выходим из цикла если пришло "stop"
                } else {
                    out.write(userWord + "\n"); // отправляем на сервер
                }
                out.flush(); // чистим
            } catch (IOException e) {

            }

        }
    }
}