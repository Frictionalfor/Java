import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        // ArrayList
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Orange");
        System.out.println("ArrayList: " + arrayList);

        // LinkedList
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Cat");
        linkedList.add("Dog");
        linkedList.add("Bird");
        System.out.println("LinkedList: " + linkedList);

        // Iterate
        System.out.println("\nIterating ArrayList:");
        for (String fruit : arrayList) {
            System.out.println(fruit);
        }
    }
}
