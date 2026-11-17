class practical8 {
    void show(int a) {
        System.out.println("Int: " + a);
    }
    void show(double a) {
        System.out.println("Double: " + a);
    }
    void show(String a) {
        System.out.println("String: " + a);
    }
    public static void main(String args[]) {
        practical8 p = new practical8();
        p.show(10);
        p.show(10.5);
        p.show("Hello");
    }
}
