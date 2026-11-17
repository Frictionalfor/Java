import java.awt.*;
class practical22 {
    Frame f;
    Button left, right, top, bottom;
    practical22() {
        f = new Frame("Border Layout");
        left = new Button("Left");
        right = new Button("Right");
        top = new Button("Top");
        bottom = new Button("Bottom");
        f.add(left, BorderLayout.WEST);
        f.add(right, BorderLayout.EAST);
        f.add(top, BorderLayout.NORTH);
        f.add(bottom, BorderLayout.SOUTH);
        f.setSize(400, 300);
        f.setVisible(true);
    }
    public static void main(String args[]) {
        new practical22();
    }
}
