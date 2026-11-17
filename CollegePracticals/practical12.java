class practical12 {
    public static void main(String args[]) {
        Integer i = Integer.valueOf(10);
        int x = i.intValue();
        Double d = Double.valueOf(10.5);
        double y = d.doubleValue();
        Character c = Character.valueOf('A');
        char ch = c.charValue();
        Boolean b = Boolean.valueOf(true);
        boolean flag = b.booleanValue();
        System.out.println(x);
        System.out.println(y);
        System.out.println(ch);
        System.out.println(flag);
    }
}
