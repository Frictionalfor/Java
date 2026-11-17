class A {
    void show() {
        System.out.println("Class A");
    }
}
class B extends A {
    void show() {
        System.out.println("Class B");
    }
}
class practical10 {
    public static void main(String args[]) {
        A a;
        B b = new B();
        a = b;
        a.show();
    }
}
