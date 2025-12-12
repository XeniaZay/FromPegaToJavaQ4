package week16.practice.task9;

public class Main {
    public static void main(String[] args) {
        // Создаем прокси
        Calculator calculator = ProxyFactory.createCalculator();

        // Тестируем методы
        System.out.println("=== Testing Calculator Methods ===");

        // Пример 1: add(2, 3)
        int sum = calculator.add(2, 3);
        System.out.println("Result: " + sum);
        System.out.println();

        // Пример 2: subtract(10, 4)
        int difference = calculator.subtract(10, 4);
        System.out.println("Result: " + difference);
        System.out.println();

        // Пример 3: multiply(5, 6)
        int product = calculator.multiply(5, 6);
        System.out.println("Result: " + product);
        System.out.println();

        // Пример 4: divide(10, 2)
        double quotient = calculator.divide(10, 2);
        System.out.println("Result: " + quotient);
        System.out.println();

        // Пример 5: Обработка исключения (деление на 0)
        try {
            calculator.divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
