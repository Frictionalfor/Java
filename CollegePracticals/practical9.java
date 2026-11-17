final class practical9 {
    final int x = 10;
    final void show() {
        System.out.println("Final method");
    }
    public static void main(String args[]) {
        practical9 p = new practical9();
        System.out.println(p.x);
        p.show();
    }
}
