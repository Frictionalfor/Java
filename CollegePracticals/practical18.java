import java.util.ArrayList;
import java.util.List;
class practical18 {
    public static void main(String args[]) {
        List<Integer> l = new ArrayList<>();
        l.add(10);
        l.add(20);
        l.add(30);
        System.out.println(l);
        l.remove(1);
        System.out.println(l);
        System.out.println(l.size());
    }
}
