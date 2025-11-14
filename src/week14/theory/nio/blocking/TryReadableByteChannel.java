package week14.theory.nio.blocking;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class TryReadableByteChannel {
    private static final String testFilePath = "/week14/theory/resources/test.txt";

    public static void main(String[] args) {
//        try {
//            nio_blocking_readFile();
//        } catch (IOException | URISyntaxException e) {
//            System.err.println("Ошибка при чтении файла: " + e.getMessage());
//            e.printStackTrace();
//        }

        try {
            nio_readFile_files();
        } catch (IOException | URISyntaxException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void nio_blocking_readFile() throws IOException, URISyntaxException {
        URL fileUrl = TryReadableByteChannel.class.getResource(testFilePath);

        if (fileUrl == null) {
            throw new IOException("Файл не найден: " + testFilePath);
        }

        var filepath = Path.of(fileUrl.toURI());

        try (ReadableByteChannel inputChannel = FileChannel.open(filepath)) {
            var buffer = ByteBuffer.allocate(300_000);
            int readByteCount = inputChannel.read(buffer);
            var resultBytes = new byte[readByteCount];
            // Записываем считанные данные в resultBytes
            // Если просто вызвать buffer.array(), то если массив больше считываемого файла,
            // в конце он будет заполнен нулями
            buffer.get(0, resultBytes);
            var fileString = new String(resultBytes, StandardCharsets.UTF_8);

            System.out.println("=== СОДЕРЖИМОЕ ФАЙЛА ===");
            System.out.println(fileString);
            System.out.println("=== ПРОЧИТАНО БАЙТ: " + readByteCount + " ===");
        }
    }

    static void nio_readFile_files() throws IOException, URISyntaxException {
        URL fileUrl = TryReadableByteChannel.class.getResource(testFilePath);
        var filePath = Path.of(fileUrl.toURI());
        var fileString = Files.readString(filePath);
        System.out.println(fileString);
    }
}