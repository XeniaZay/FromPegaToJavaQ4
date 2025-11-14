package week14.theory.nio.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TryServerWithSelector {
        public static void main(String[] args) {
            try {
                nio_non_blockable_selector_server_callback();
                //nio_non_blockable_selector_server();
            } catch (IOException e) {
                System.err.println("Ошибка сервера: " + e.getMessage());
                e.printStackTrace();
            }
        }

        static void nio_non_blockable_selector_server() throws IOException {
            try (ServerSocketChannel channel = ServerSocketChannel.open();
                 //Открытие селектора. Под капотом вызывается SelectorProvider, реализация которого является платформозависимой
                 Selector selector = Selector.open()) {
                channel.socket().bind(new InetSocketAddress(9999));
                channel.configureBlocking(false);
                //Регистрируем серверный канал в селекторе с интересующим типом операции - принятие подключения
                SelectionKey registeredKey = channel.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {
                    //Получаем количество готовых к обработке каналов.
                    int numReadyChannels = selector.select();
                    if (numReadyChannels == 0) {
                        continue;
                    }
                    //Получаем готовые к обработке каналы
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                    //Обрабатываем каналы в соответствии с типом доступной каналу операции
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();

                        if (key.isAcceptable()) {
                            //Принятие подключения серверным сокетом
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel client = server.accept();
                            if (client == null) {
                                continue;
                            }
                            client.configureBlocking(false);
                            //Регистрируем принятое подключение в селекторе с интересующим типом операции - чтение
                            client.register(selector, SelectionKey.OP_READ);
                        }

                        if (key.isReadable()) {
                            //Тут происходит обработка принятых подключений
                            SocketChannel client = (SocketChannel) key.channel();
                            ByteBuffer requestBuffer = ByteBuffer.allocate(100);
                            int r = client.read(requestBuffer);
                            if (r == -1) {
                                client.close();
                            } else {
                                //В этом блоке происходит обработка запроса
                                System.out.println(new String(requestBuffer.array()));
                                String responseMessage = "Привет от сервера! : " + client.socket().getLocalSocketAddress();
                                System.out.println("привет клиенту отправлен");
                                //Несмотря на то, что интересующая операция, переданная в селектор - чтение, мы все равно можем писать в сокет
                                client.write(ByteBuffer.wrap(responseMessage.getBytes()));
                            }
                        }
                        //Удаляем ключ после обработки. Если канал снова будет доступным, его ключ снова появится в selectedKeys
                        keyIterator.remove();
                    }
                }
            }
        }

        static void nio_non_blockable_selector_server_callback() throws IOException {
            try (ServerSocketChannel channel = ServerSocketChannel.open();
                 Selector selector = Selector.open()) {
                channel.socket().bind(new InetSocketAddress(9999));
                channel.configureBlocking(false);
                SelectionKey registeredKey = channel.register(selector, SelectionKey.OP_ACCEPT);

                while (true) {
                    //Обрабатываем доступные к ожиданию подключения с использованием каллбэка
                    selector.select(key -> {

                        if (key.isAcceptable()) {
                            try {
                                //Принятие подключения серверным сокетом
                                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                                SocketChannel client = server.accept();
                                client.configureBlocking(false);
                                //Регистрируем принятое подключение в селекторе с интересующим типом операции - чтение
                                client.register(selector, SelectionKey.OP_READ);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        if (key.isReadable()) {
                            try {
                                //Тут происходит обработка принятых подключений
                                SocketChannel client = (SocketChannel) key.channel();
                                ByteBuffer requestBuffer = ByteBuffer.allocate(100);
                                int r = client.read(requestBuffer);
                                if (r == -1) {
                                    client.close();
                                } else {
                                    //В этом блоке происходит обработка запроса
                                    System.out.println(new String(requestBuffer.array()));
                                    String responseMessage = "Привет от сервера! : " + client.socket().getLocalSocketAddress();
                                    System.out.println("Привет клиенту отправлен");
                                    //Несмотря на то, что интересующая операция, переданная в селектор - чтение, мы все равно можем писать в сокет
                                    client.write(ByteBuffer.wrap(responseMessage.getBytes()));
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        }

}
