class practical5 {
    public static void main(String args[]) {
        String s = new String("Hello");
        System.out.println(s.length());
        System.out.println(s.charAt(0));
        System.out.println(s.toUpperCase());
        System.out.println(s.toLowerCase());
        System.out.println(s.substring(1, 3));
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World");
        sb.reverse();
        sb.insert(0, "Java ");
        System.out.println(sb);
        System.out.println(sb.capacity());
    }
}
