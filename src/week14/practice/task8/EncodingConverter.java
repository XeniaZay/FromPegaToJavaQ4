package week14.practice.task8;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;


public class EncodingConverter {
    public static void recode(String in, String out, Charset src, Charset dst){
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(in), src);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(out), dst)){
            char[] buffer = new char[8192]; // 8KB буфер
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charsRead);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    private static void createTestFile(String filename, String text, Charset charset) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filename), charset)) {
            writer.write(text);
        }
        System.out.println("Создан тестовый файл: " + filename);
    }
    private static void checkResults(String originalFile, String convertedFile) throws IOException {
        // Получаем информацию о файлах
        File origFile = new File(originalFile);
        File convFile = new File(convertedFile);

        // Читаем содержимое файлов для проверки
        String originalContent = readFile(originalFile, Charset.forName("Windows-1251"));
        String convertedContent = readFile(convertedFile, Charset.forName("UTF-8"));

        // Выводим сравнение
        System.out.println("\n=== РЕЗУЛЬТАТЫ КОНВЕРТАЦИИ ===");
        System.out.println("Исходный файл (" + originalFile + "):");
        System.out.println("  Размер: " + origFile.length() + " байт");
        System.out.println("  Содержимое: " + originalContent);

        System.out.println("\nКонвертированный файл (" + convertedFile + "):");
        System.out.println("  Размер: " + convFile.length() + " байт");
        System.out.println("  Содержимое: " + convertedContent);

        System.out.println("\n=== СРАВНЕНИЕ ===");
        System.out.println("Разница в размере: " + (convFile.length() - origFile.length()) + " байт");
        System.out.println("Содержимое идентично: " + originalContent.equals(convertedContent));

        // Дополнительная информация о кодировках
        System.out.println("\n=== ИНФОРМАЦИЯ О КОДИРОВКАХ ===");
        byte[] origBytes = Files.readAllBytes(Path.of(originalFile));
        byte[] convBytes = Files.readAllBytes(Path.of(convertedFile));

        System.out.println("Windows-1251 байты: " + bytesToHex(origBytes));
        System.out.println("UTF-8 байты: " + bytesToHex(convBytes));
    }

    // Вспомогательный метод для чтения файла
    private static String readFile(String filename, Charset charset) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename), charset)) {
            StringBuilder content = new StringBuilder();
            char[] buffer = new char[1024];
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, charsRead);
            }
            return content.toString();
        }
    }

    // Вспомогательный метод для отображения байтов в HEX
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X ", b));
        }
        return hex.toString().trim();
    }


    public static void main(String[] args) {
        try {
            // Создаем тестовый файл в кодировке Windows-1251
            String testText = "Привет, мир! Как дела?";
            createTestFile("cp1251.txt", testText, Charset.forName("Windows-1251"));

            // Конвертируем из Windows-1251 в UTF-8
            recode("cp1251.txt", "utf8.txt",
                    Charset.forName("Windows-1251"),
                    Charset.forName("UTF-8"));

            // Проверяем результаты
            checkResults("cp1251.txt", "utf8.txt");

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
        //String sourceFile = "C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task7/video_2025-11-21_11-42-54.mp4";
        //String dest = "C:/Users/kseni/IdeaProjects/FromPegaToJavaQ4/src/week14/practice/task7/unbuffered_copy.txt";

        //recode(sourceFile,dest,);

}
