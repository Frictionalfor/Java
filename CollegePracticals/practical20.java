import java.awt.*;
import java.awt.event.*;
class practical20 {
    Frame f;
    Label l;
    TextField tf;
    Button b;
    practical20() {
        f = new Frame("GUI");
        l = new Label("Enter name:");
        tf = new TextField(20);
        b = new Button("Click");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                l.setText("Hello " + tf.getText());
            }
        });
        f.add(l);
        f.add(tf);
        f.add(b);
        f.setLayout(new FlowLayout());
        f.setSize(300, 200);
        f.setVisible(true);
    }
    public static void main(String args[]) {
        new practical20();
    }
}
