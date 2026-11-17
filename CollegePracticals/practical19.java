import java.io.*;
class practical19 {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            String s = "Hello World";
            byte b[] = s.getBytes();
            fos.write(b);
            fos.close();
            FileInputStream fis = new FileInputStream("test.txt");
            int x;
            while ((x = fis.read()) != -1) {
                System.out.print((char) x);
            }
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
