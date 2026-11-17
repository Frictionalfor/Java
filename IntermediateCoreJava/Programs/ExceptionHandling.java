import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter two numbers for division: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int result = divide(a, b);
            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed");
            scanner.close();
        }
    }

    public static int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
