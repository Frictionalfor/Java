interface Show {
    void display();
}
class practical13 implements Show {
    public void display() {
        System.out.println("Interface implemented");
    }
    public static void main(String args[]) {
        practical13 p = new practical13();
        p.display();
    }
}
