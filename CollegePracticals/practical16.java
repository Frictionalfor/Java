class practical16 {
    static void divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        System.out.println(a / b);
    }
    public static void main(String args[]) {
        try {
            divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println(e);
        } finally {
            System.out.println("Finally block");
        }
    }
}
