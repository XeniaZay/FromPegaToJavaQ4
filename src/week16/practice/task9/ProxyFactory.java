package week16.practice.task9;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Calculator createCalculator() {
        Calculator calculator = new CalculatorImpl();

        return (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class<?>[] { Calculator.class },
                new LoggingInvocationHandler(calculator)
        );
    }
}
