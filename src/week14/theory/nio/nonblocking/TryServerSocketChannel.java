package week14.theory.nio.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TryServerSocketChannel {
    public static void main(String[] args) {
        try {
            nio_server_non_blockable();
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void nio_server_non_blockable() throws IOException {
        var ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(9999));
        //Включаем неблокирующий режим канала
        ssc.configureBlocking(false);
        var responseMessage = "Привет от сервера! : " + ssc.socket().getLocalSocketAddress();
        var sendBuffer = ByteBuffer.wrap(responseMessage.getBytes());
        System.out.println("🚀 Сервер запущен на порту 9999...");
        System.out.println("Ожидаем подключения клиентов...");

        while (true) {
            System.out.print(".");
            //Ловим соединения через вызов ssc.accept().
            //Т.к. стоит неблокирующий режим, метод accept немедленно вернет null, если нет ожидающих подключений
            try (SocketChannel sc = ssc.accept()) {
                if (sc != null) {
                    System.out.println();
                    System.out.println("Принято соединение от  " + sc.socket().getRemoteSocketAddress());
                    var receivedBuffer = ByteBuffer.allocate(100);
//                    sc.read(receivedBuffer);
//                    var requestMessage = new String(receivedBuffer.array());
//                    System.out.println(requestMessage);
                    int bytesRead = sc.read(receivedBuffer);
                    if (bytesRead > 0) {
                        receivedBuffer.flip(); // Переключаем буфер в режим чтения
                        byte[] requestBytes = new byte[receivedBuffer.remaining()];
                        receivedBuffer.get(requestBytes);
                        var requestMessage = new String(requestBytes);
                        System.out.println("📨 Получено от клиента: " + requestMessage);
                    }
                    sendBuffer.rewind();
                    sc.write(sendBuffer);
                    System.out.println("📤 Отправлен ответ клиенту");

                } else {
                    Thread.sleep(100);
                    //sc.close();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

