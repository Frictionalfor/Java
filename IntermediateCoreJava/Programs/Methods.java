public class Methods {
    public static void main(String[] args) {
        Methods obj = new Methods();

        // Method with parameters and return type
        int sum = obj.add(10, 20);
        System.out.println("Sum: " + sum);

        // Method overloading
        System.out.println("Add with int: " + obj.add(10, 20));
        System.out.println("Add with double: " + obj.add(10.5, 20.5));
        System.out.println("Add with three numbers: " + obj.add(10, 20, 30));
    }

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
