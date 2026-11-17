import java.awt.*;
class practical21 {
    Frame f;
    MenuBar mb;
    Menu file, edit;
    MenuItem newItem, open, save;
    MenuItem cut, copy, paste;
    practical21() {
        f = new Frame("Menu");
        mb = new MenuBar();
        file = new Menu("File");
        edit = new Menu("Edit");
        newItem = new MenuItem("New");
        open = new MenuItem("Open");
        save = new MenuItem("Save");
        cut = new MenuItem("Cut");
        copy = new MenuItem("Copy");
        paste = new MenuItem("Paste");
        file.add(newItem);
        file.add(open);
        file.add(save);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        mb.add(file);
        mb.add(edit);
        f.setMenuBar(mb);
        f.setSize(400, 300);
        f.setVisible(true);
    }
    public static void main(String args[]) {
        new practical21();
    }
}
