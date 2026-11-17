import java.util.function.Predicate;

public class LambdaExpressions {
    public static void main(String[] args) {
        // Lambda expression
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;

        System.out.println("Addition: " + addition.operate(10, 5));
        System.out.println("Subtraction: " + subtraction.operate(10, 5));

        // Using Predicate
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("10 is even: " + isEven.test(10));
        System.out.println("7 is even: " + isEven.test(7));
    }
}

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}
