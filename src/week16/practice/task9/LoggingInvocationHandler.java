package week16.practice.task9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;


public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Логируем вызов метода с аргументами
        System.out.printf("[BEFORE] invoke %s with %s%n",
                method.getName(),
                args != null ? Arrays.toString(args) : "[]");

        try {
            // Вызываем оригинальный метод
            Object result = method.invoke(target, args);

            // Логируем результат
            System.out.printf("[AFTER]  invoke %s with %s -> %s%n",
                    method.getName(),
                    args != null ? Arrays.toString(args) : "[]",
                    result);
            return result;
        } catch (Exception e) {
            // Логируем исключение
            System.out.printf("[ERROR]  invoke %s with %s -> %s%n",
                    method.getName(),
                    args != null ? Arrays.toString(args) : "[]",
                    e.getCause());
            throw e.getCause();
        }
    }
}
