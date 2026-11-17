import java.awt.*;
import java.awt.event.*;
class practical23 {
    Frame f;
    TextField tf;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button add, sub, mul, div, eq, clr;
    practical23() {
        f = new Frame("Calculator");
        tf = new TextField();
        b0 = new Button("0");
        b1 = new Button("1");
        b2 = new Button("2");
        b3 = new Button("3");
        b4 = new Button("4");
        b5 = new Button("5");
        b6 = new Button("6");
        b7 = new Button("7");
        b8 = new Button("8");
        b9 = new Button("9");
        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        eq = new Button("=");
        clr = new Button("C");
        f.add(tf);
        f.add(b7);
        f.add(b8);
        f.add(b9);
        f.add(add);
        f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(sub);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(mul);
        f.add(b0);
        f.add(clr);
        f.add(eq);
        f.add(div);
        f.setLayout(new GridLayout(5, 4));
        f.setSize(300, 300);
        f.setVisible(true);
    }
    public static void main(String args[]) {
        new practical23();
    }
}
