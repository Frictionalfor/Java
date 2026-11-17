import java.util.Vector;
class practical7 {
    public static void main(String args[]) {
        Vector v = new Vector();
        v.add(10);
        v.add(20);
        v.add(30);
        v.add(40);
        v.add(50);
        System.out.println(v);
        v.remove(2);
        System.out.println(v);
        System.out.println(v.get(1));
        System.out.println(v.size());
    }
}
