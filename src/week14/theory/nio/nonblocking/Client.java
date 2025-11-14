package week14.theory.nio.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) {
        try {
            nio_client_non_blockable();
            //nio_client_blockable();
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void nio_client_non_blockable() throws IOException {
        try (SocketChannel sc = SocketChannel.open()) {
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("localhost", 9999));

            while (!sc.finishConnect()) {
                System.out.println("waiting to finish connection");
            }

            ByteBuffer buffer = ByteBuffer.wrap(("Привет от клиента! " + LocalDateTime.now()).getBytes());
            System.out.println("привет серверу отправлен");
            sc.write(buffer);
            Thread.sleep(1_000);

            var receivedBuffer = ByteBuffer.allocate(100);
            int receiveReadCount = sc.read(receivedBuffer);

            var resultBytes = new byte[receiveReadCount];
            receivedBuffer.get(0, resultBytes);
            var responseMessage = new String(resultBytes, StandardCharsets.UTF_8);

            //Консоль выведет ответ
            System.out.println(responseMessage);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    static void nio_client_blockable() throws IOException {
        try (SocketChannel sc = SocketChannel.open()) {
            sc.configureBlocking(true);
            sc.connect(new InetSocketAddress("localhost", 9999));

            var requestMessage = "Привет от клиента! " + LocalDateTime.now();
            ByteBuffer buffer = ByteBuffer.wrap(requestMessage.getBytes());
            sc.write(buffer);

            var receivedBuffer = ByteBuffer.allocate(100);
            //Приложение останавливается в ожидании ответа
            sc.read(receivedBuffer);
            var responseMessage = new String(receivedBuffer.array());
            System.out.println(responseMessage);
        }
    }



}
