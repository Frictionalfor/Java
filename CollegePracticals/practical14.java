import java.util.Date;
import java.util.Scanner;
class practical14 {
    public static void main(String args[]) {
        Date d = new Date();
        System.out.println(d);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Hello " + name);
    }
}
