package week14.theory.nio.asynch;


import week14.theory.nio.blocking.TryReadableByteChannel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TryAsynchronousFileChannel {
    private static final String testFilePath = "/week14/theory/resources/test.txt";

    public static void main(String[] args) {

//        try {
//            nio_async_readFile();
//        } catch (IOException | URISyntaxException e) {
//            System.err.println("Ошибка при чтении файла: " + e.getMessage());
//            e.printStackTrace();
//        }

        try {
            nio_async_readFileWithCallback();
        } catch (IOException | URISyntaxException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void nio_async_readFile() throws URISyntaxException, IOException {
        URL fileUrl = TryReadableByteChannel.class.getResource(testFilePath);

        if (fileUrl == null) {
            throw new IOException("Файл не найден: " + testFilePath);
        }

        var filepath = Path.of(fileUrl.toURI());

        try (var inputChannel = AsynchronousFileChannel.open(filepath)) {
            var buffer = ByteBuffer.allocate(300_000);
            Future<Integer> futureResult = inputChannel.read(buffer, 0);

            while (!futureResult.isDone()) {
                System.out.println("Файл еще не загружен в буффер");
            }
            int bytesRead = futureResult.get();
            var resultBytes = new byte[bytesRead];
            buffer.get(0, resultBytes);  // копируем только нужные данные
            var fileString = new String(resultBytes, StandardCharsets.UTF_8);
          //  var fileString = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println(fileString);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void nio_async_readFileWithCallback() throws URISyntaxException, IOException {
        URL fileUrl = TryReadableByteChannel.class.getResource(testFilePath);

        if (fileUrl == null) {
            throw new IOException("Файл не найден: " + testFilePath);
        }
        var path = Path.of(fileUrl.toURI());

        try (var inputChannel = AsynchronousFileChannel.open(path)) {
            var buffer = ByteBuffer.allocate(300_000);
            inputChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    var resultBytes = new byte[result];
                    attachment.get(0, resultBytes);  // копируем только нужные данные
                    var fileString = new String(resultBytes, StandardCharsets.UTF_8);

                    //var fileString = new String(buffer.array(), StandardCharsets.UTF_8);
                    System.out.println(fileString);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    //do nothing
                }
            });

            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
