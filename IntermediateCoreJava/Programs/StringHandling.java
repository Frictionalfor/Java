public class StringHandling {
    public static void main(String[] args) {
        // String (immutable)
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println("String comparison: " + (str1 == str2));

        // StringBuilder (mutable, not thread-safe)
        StringBuilder sb = new StringBuilder("Java");
        sb.append(" Programming");
        System.out.println("StringBuilder: " + sb);

        // StringBuffer (mutable, thread-safe)
        StringBuffer sbf = new StringBuffer("Learning");
        sbf.append(" Java");
        System.out.println("StringBuffer: " + sbf);

        // Common String methods
        String text = "Hello World";
        System.out.println("Length: " + text.length());
        System.out.println("Substring: " + text.substring(0, 5));
        System.out.println("Uppercase: " + text.toUpperCase());
    }
}
