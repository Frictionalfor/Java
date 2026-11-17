abstract class Shape {
    abstract void draw();

    void fill() {
        System.out.println("Filling shape with color");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a rectangle");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape rectangle = new Rectangle();

        circle.draw();
        circle.fill();

        rectangle.draw();
        rectangle.fill();
    }
}
