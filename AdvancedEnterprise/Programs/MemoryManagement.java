public class MemoryManagement {
    public static void main(String[] args) {
        System.out.println("Understanding Java Memory Management\n");

        // Stack memory example
        int num = 10; // Stored in stack
        String str = "Hello"; // Reference in stack, object in heap

        System.out.println("Stack variables: num, str reference");
        System.out.println("Heap contains: String object");

        // Demonstrate object creation and garbage collection
        for (int i = 0; i < 5; i++) {
            createObject();
        }

        // Suggest garbage collection
        System.gc();
        System.out.println("\nGarbage collection suggested");
    }

    public static void createObject() {
        // Object created on heap
        String temp = new String("Temporary");
        System.out.println("Created: " + temp);
    }
}
