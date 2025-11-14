package week14.theory.nio.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) {
        try {
            nio_client();
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void nio_client() throws IOException, InterruptedException {
        // Создаем клиентский канал
        var clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 9999));

        // Подготавливаем сообщение для сервера
        String requestMessage = "Привет от клиента!";
        var requestBuffer = ByteBuffer.wrap(requestMessage.getBytes());

        // Отправляем сообщение серверу
        clientChannel.write(requestBuffer);
        System.out.println("📨 Отправлено серверу: " + requestMessage);

        // Читаем ответ от сервера
        var responseBuffer = ByteBuffer.allocate(256);
        int bytesRead = clientChannel.read(responseBuffer);

        if (bytesRead > 0) {
            responseBuffer.flip();
            byte[] responseBytes = new byte[responseBuffer.remaining()];
            responseBuffer.get(responseBytes);
            String response = new String(responseBytes);
            System.out.println("📥 Получено от сервера: " + response);
        }

        clientChannel.close();
        System.out.println("🔌 Соединение закрыто");
    }



}
