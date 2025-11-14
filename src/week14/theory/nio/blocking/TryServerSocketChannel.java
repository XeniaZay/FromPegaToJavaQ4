package week14.theory.nio.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TryServerSocketChannel {
    public static void main(String[] args) {
        try {
            nio_server_blockable();
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void nio_server_blockable() throws IOException {
        //Открытие канала. Под капотом вызывается SelectorProvider, реализация которого является платформозависимой
        var ssc = ServerSocketChannel.open();
        //Созданный канал является открытым, но не привязан к конкретному сокету. Что бы связать его с сокетом, необходимо вызвать код из следующей строки
        ssc.socket().bind(new InetSocketAddress(9999));
        //По дефолту канал является блокирующим. Что бы перевести его в неблокирующий режим, нужно в следующей строке передать false
        ssc.configureBlocking(true);
        var responseMessage = "Привет от сервера! : " + ssc.socket().getLocalSocketAddress();
        var sendBuffer = ByteBuffer.wrap(responseMessage.getBytes());
        System.out.println("🚀 Сервер запущен на порту 9999...");
        System.out.println("Ожидаем подключения клиентов...");

        while (true) {
            //Ловим соединения через вызов ssc.accept()
            //Поток блокируется до момента принятия соединения
            try (SocketChannel sc = ssc.accept()) {
                System.out.println("Принято соединение от  " + sc.socket().getRemoteSocketAddress());
                var receivedBuffer = ByteBuffer.allocate(100);
                int bytesRead = sc.read(receivedBuffer);
                if (bytesRead > 0) {
                    receivedBuffer.flip(); // Переключаем буфер в режим чтения
                    byte[] requestBytes = new byte[receivedBuffer.remaining()];
                    receivedBuffer.get(requestBytes);
                    var requestMessage = new String(requestBytes);
                    System.out.println("📨 Получено от клиента: " + requestMessage);
                }

            // Отправляем ответ клиенту
                sendBuffer.rewind();
                sc.write(sendBuffer);
                System.out.println("📤 Отправлен ответ клиенту");

            } catch (IOException e) {
                System.err.println("❌ Ошибка при обработке соединения: " + e.getMessage());
            }
        }
    }
}
