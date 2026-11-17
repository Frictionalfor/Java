import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        // Predicate
        Predicate<Integer> isPositive = num -> num > 0;
        System.out.println("5 is positive: " + isPositive.test(5));

        // Consumer
        Consumer<String> printMessage = msg -> System.out.println("Message: " + msg);
        printMessage.accept("Hello World");

        // Supplier
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value: " + randomValue.get());

        // Function
        Function<Integer, String> intToString = num -> "Number: " + num;
        System.out.println(intToString.apply(42));
    }
}
